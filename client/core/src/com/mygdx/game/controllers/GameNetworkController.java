package com.mygdx.game.controllers;

import com.badlogic.gdx.Net;
import com.badlogic.gdx.utils.JsonValue;
import com.mygdx.game.Battleship;
import com.mygdx.game.Constants;
import com.mygdx.game.models.*;
import com.badlogic.gdx.Gdx;

import java.util.Timer;
import java.util.TimerTask;

import json.JsonData;
import json.JsonHelper;
import network.HttpResponseListener;
import network.NetworkHelper;

/**
 * Created by esso on 13.03.15.
 */
public class GameNetworkController {
    private PlayerNetworkController playerController;
    private Battleship battleshipGame;
    private Timer waitForOpponentTimer;

    public GameNetworkController(Battleship battleshipGame) {
        this.battleshipGame = battleshipGame;
    }
    public PlayerNetworkController getPlayerController(){
        return playerController;
    }

    /**
     * startGame
     * @param username
     * @description Requests for a game. If game, get data and parse it.
     */
    public void startGame(String username){
        playerController = new PlayerNetworkController(battleshipGame);
        playerController.setPlayer(new Player(username, null));

        NetworkHelper.sendPostRequest("/play", JsonHelper.buildJson(new JsonData(username)), new HttpResponseListener() {
            @Override
            public void handleHttpResponse(Net.HttpResponse httpResponse) {
                JsonValue jsonResponse = JsonHelper.parseJson(httpResponse.getResultAsString());
                if (jsonResponse.get("game").isNull()){
                    waitForOpponent();
                } else{
                    JsonValue game = jsonResponse.get("game");
                    String username = playerController.getPlayer().getUsername();

                    if (game.get("next").asString().equals(username)){
                        playerController.setPlayersTurn(true);
                    }

                    playerController.setPlayersTurn(false);

                    // Setup boards
                    Board playerBoard = new Board();
                    Board opponentBoard = new Board();

                    int player = game.get("player1").asString().equals(username) ? 0: 1;
                    int opponent = player == 1 ?  0: 1;

                    playerBoard.createFromJson(JsonHelper.prettyPrint(game.get("Boards").get(player)));
                    opponentBoard.createFromJson(JsonHelper.prettyPrint(game.get("Boards").get(opponent)));

                    // Add values to models
                    opponent += 1;
                    playerController.getPlayer().setBoard(playerBoard);
                    playerController.setOpponent(new Player(game.get("player"+opponent).asString(), opponentBoard));

                    Gdx.app.postRunnable(new Runnable() {
                        @Override
                        public void run() {
                            battleshipGame.startGame();
                        }
                    });

                }
            }
        });
    }


    /**
     * waitForOpponent
     * @description Pulls the play-API regularly until the game object is not null, which means there is a game ready to play
     */
    private void waitForOpponent() {
        final JsonData jsonData = new JsonData(playerController.getPlayer().getUsername());
        waitForOpponentTimer = new Timer();
        waitForOpponentTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {

                NetworkHelper.sendPostRequest("/play", JsonHelper.buildJson(jsonData), new HttpResponseListener() {
                    @Override
                    public void handleHttpResponse(Net.HttpResponse httpResponse) {
                        JsonValue jsonResponse = JsonHelper.parseJson(httpResponse.getResultAsString());
                        if (!jsonResponse.get("game").isNull()) { // It's not null
                            startGame(playerController.getPlayer().getUsername());
                            cancel(); // Stop timer task
                        }
                    }
                });
            }
        }, 0, Constants.REGULAR_REQUEST_TIME);
    }

    /*
     * cancelWaitForOpponent
     * @description Cancels the network requests when the player presses cancel.
     */
    public void cancelWaitForOpponent() {

        // Kill recurring timer task
        waitForOpponentTimer.cancel();
        waitForOpponentTimer.purge();

        // Send a network request to remove this player from the player queue:
        JsonData jsonData = new JsonData();
        jsonData.setUsername(playerController.getPlayer().getUsername());

        NetworkHelper.sendPostRequest("/cancel", JsonHelper.buildJson(jsonData), new HttpResponseListener());
    }

}
