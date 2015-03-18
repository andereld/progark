package com.mygdx.game.controllers;

import com.badlogic.gdx.Net;
import com.badlogic.gdx.utils.JsonValue;
import com.mygdx.game.Constants;
import com.mygdx.game.models.*;

import java.util.Timer;
import java.util.TimerTask;

import json.JsonHelper;
import network.NetworkHelper;

/**
 * Created by esso on 13.03.15.
 */
public class GameNetworkController {
    private PlayerNetworkController playerController;

    /**
     * startGame
     * @param username
     * @description Requests for a game. If game, get data and parse it.
     */
    public void startGame(String username){
        playerController = new PlayerNetworkController();


        playerController.setPlayer(new Player(username, null));

        JsonValue json = new JsonValue(username);
        json.setName("username");
        NetworkHelper.sendPostRequest("/play", json.toString(), new Net.HttpResponseListener() {
            @Override
            public void handleHttpResponse(Net.HttpResponse httpResponse) {
                JsonValue jsonResponse = JsonHelper.parseJson(httpResponse.getResultAsString());
                if (jsonResponse.get("game").isNull()){
                    waitForOpponent();
                }else{
                    JsonValue game = jsonResponse.get("game");

                    if (game.get("next").asString().equals(playerController.getPlayer().getUsername())){
                        playerController.setPlayersTurn(true);
                    }

                    playerController.setPlayersTurn(false);

                    // Setup boards
                    Board playerBoard = new Board();
                    Board opponentBoard = new Board();
                    playerBoard.createFromJson(game.get("board1").toString());
                    opponentBoard.createFromJson(game.get("board2").toString());

                    // Add values to models
                    playerController.getPlayer().setBoard(playerBoard);
                    playerController.setOpponent(new Player(jsonResponse.get("player2").toString(), opponentBoard));
                }
            }

            @Override
            public void failed(Throwable t) {
                //@todo
            }

            @Override
            public void cancelled() {
                // @todo
            }
        });
    }


    /**
     * waitForOpponent
     * @description Pulls the play-API regulary until the game object is not null, which means there is a game ready to play
     */
    public void waitForOpponent() {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                JsonValue json = new JsonValue(playerController.getPlayer().getUsername());
                json.setName("username");
                NetworkHelper.sendPostRequest("/play", json.toString(), new Net.HttpResponseListener() {
                    @Override
                    public void handleHttpResponse(Net.HttpResponse httpResponse) {
                        JsonValue jsonResponse = new JsonValue(httpResponse.getResultAsString());
                        if (!jsonResponse.get("game").isNull()){ // It's not null
                            startGame(playerController.getPlayer().getUsername());
                            cancel(); // Stop timer task
                        }
                    }

                    @Override
                    public void failed(Throwable t) {

                    }

                    @Override
                    public void cancelled() {

                    }
                });
            }
        }, Constants.REGULAR_REQUEST_TIME);
    }

}
