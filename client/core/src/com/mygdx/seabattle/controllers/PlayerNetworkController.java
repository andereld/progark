package com.mygdx.seabattle.controllers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net;
import com.badlogic.gdx.utils.JsonValue;
import com.mygdx.seabattle.SeaBattle;
import com.mygdx.seabattle.Constants;
import com.mygdx.seabattle.models.*;

import java.util.Arrays;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import com.mygdx.seabattle.json.JsonHelper;
import com.mygdx.seabattle.network.NetworkHelper;

/**
 * Created by esso on 16.03.15.
 */
public class PlayerNetworkController {
    private Player player, opponent;
    private boolean playersTurn;
    private SeaBattle seaBattleGame;

    public PlayerNetworkController(SeaBattle seaBattleGame){
        opponent = null;
        player = null;
        playersTurn = false;
        this.seaBattleGame = seaBattleGame;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player){
        this.player = player;
    }

    public boolean isPlayersTurn() {
        return playersTurn;
    }

    public void setPlayersTurn(boolean b){playersTurn = b; }

    /**
     * waitForTurn
     * @description Checks regularly if it's this players turn or not, and if so,
     * retrieves the coordinates for the last shot from the opponent
     */
    public void waitForTurn() {
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                NetworkHelper.sendGetRequest("/turn/" + getPlayer().getUsername(), new Net.HttpResponseListener() {
                    @Override
                    public void handleHttpResponse(Net.HttpResponse httpResponse) {
                        JsonValue jsonResponse = JsonHelper.parseJson(httpResponse.getResultAsString());

                        if (jsonResponse.get("username").asString().equals(getPlayer().getUsername())) {
                            // Handle the response when the opponent fires at your board
                            String str = jsonResponse.get("lastMove").asString();
                            List<String> coordinates = Arrays.asList(str.split(","));
                            int x = Integer.valueOf(coordinates.get(0));
                            int y = Integer.valueOf(coordinates.get(1));
                            fireAtThisBoard(x, y);
                            Gdx.app.postRunnable(new Runnable() {
                                @Override
                                public void run() {
                                    seaBattleGame.getGameScreen().myTurn();}
                            });
                            setPlayersTurn(true);
                            cancel();
                        } else {
                            setPlayersTurn(false);
                        }
                    }

                    @Override
                    public void failed(Throwable t) {}

                    @Override
                    public void cancelled() {}
                });
            }
        }, 0, Constants.REGULAR_REQUEST_TIME);
    }

    private void fireAtThisBoard(int x, int y) {
        // Coordinates are -1, -1 at the start of the game, when no shots have been fired.
        if (x == -1 || y == -1) {
            return;
        }
        player.getBoard().getCell(x,y).setHit(true);
        Gdx.app.postRunnable(new Runnable() {
            @Override
            public void run() {
                seaBattleGame.getGameScreen().getMainBoard().setBoard(player.getBoard());
            }
        });
    }

    private void fireAtOpponentBoard(int x, int y) {
        opponent.getBoard().getCell(x,y).setHit(true);
        Gdx.app.postRunnable(new Runnable() {
            @Override
            public void run() {
                seaBattleGame.getGameScreen().getOpponentBoard().setBoard(opponent.getBoard());
                seaBattleGame.getGameScreen().waitForTurn();
            }
        });
    }

    /**
     * fireAtLocation
     * @param x
     * @param y
     * @description Takes coordinates and calls to the server that he has fired on that location
     */
    public void fireAtLocation(int x, int y){
        class JsonData {
            private int x, y;
            private String username;
            public void setX(int x) { this.x = x; }
            public void setY(int y) {this.y = y;}
            public int getX() {return x;}
            public int getY() {return y;}
            public void setUsername(String username) {this.username = username;}
        }

        final JsonData jsonData = new JsonData();
        jsonData.setX(x);
        jsonData.setY(y);
        jsonData.setUsername(getPlayer().getUsername());

        NetworkHelper.sendPostRequest("/fire", JsonHelper.buildJson(jsonData), new Net.HttpResponseListener() {
            @Override
            public void handleHttpResponse(Net.HttpResponse httpResponse) {
                // Res: {shipWasHit: BOOLEAN, message: "No game was found" OR "Ongoing game" OR "You lost" OR "You won"}

                JsonValue jsonResponse = JsonHelper.parseJson(httpResponse.getResultAsString());
                if (jsonResponse.get("message").equals("No game was found")){
                    // @todo What are we supposed to do with this message?
                    // nothing for now
                } else if (jsonResponse.get("message").asString().equals("Ongoing game")){
                    fireAtOpponentBoard(jsonData.getX(), jsonData.getY());
                } else if (jsonResponse.get("message").asString().equals("You lost")) {
                    Gdx.app.postRunnable(new Runnable() {
                        @Override
                        public void run() {
                            seaBattleGame.setGameOver(false);
                        }
                    });
                } else if (jsonResponse.get("message").asString().equals("You won")) {
                    fireAtOpponentBoard(jsonData.getX(), jsonData.getY());
                    Gdx.app.postRunnable(new Runnable() {
                        @Override
                        public void run() {
                            seaBattleGame.setGameOver(true);
                        }
                    });
                }
            }

            @Override
            public void failed(Throwable t) {}

            @Override
            public void cancelled() {}
        });
    }

    public Player getOpponent() {
        return opponent;
    }

    public void setOpponent(Player opponent) {
        if (this.opponent  == null){
            this.opponent = opponent;
        }
    }
}
