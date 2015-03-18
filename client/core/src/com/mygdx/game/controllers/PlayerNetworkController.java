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
 * Created by esso on 16.03.15.
 */
public class PlayerNetworkController {
    private Player player, opponent;
    private boolean playersTurn;



    public PlayerNetworkController(){
        opponent = null;
        player = null;
        playersTurn = false;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player){
        player = player;
    }

    public boolean isPlayersTurn() {
        return playersTurn;
    }

    public void setPlayersTurn(boolean b){playersTurn = b; }

    /**
     * waitForTurn
     * @description Checks regulary if it's this players turn or not
     */
    public void waitForTurn() {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                NetworkHelper.sendGetRequest("/turn/" + getPlayer().getUsername(), new Net.HttpResponseListener() {
                    @Override
                    public void handleHttpResponse(Net.HttpResponse httpResponse) {
                        JsonValue jsonResponse = JsonHelper.parseJson(httpResponse.getResultAsString());
                        if (jsonResponse.get("username").equals(getPlayer().getUsername())){
                            setPlayersTurn(true);
                            cancel();
                        } else{
                            setPlayersTurn(false);
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


    public void fireAtLocation(int x, int y){
        class JsonData {
            private int x, y;
            private String username;
            public void setX(int x) { this.x = x; }
            public void setY(int y) {this.y = y;}
            public void setUsername(String username) {this.username = username;}
        }

        JsonData jsonData = new JsonData();
        jsonData.setX(x);
        jsonData.setY(y);
        jsonData.setUsername(getPlayer().getUsername());

        NetworkHelper.sendPostRequest("/fire", JsonHelper.buildJson(jsonData), new Net.HttpResponseListener() {
            @Override
            public void handleHttpResponse(Net.HttpResponse httpResponse) {
                // Res: {shipWasHit: BOOLEAN, message: "No game was found" OR "Ongoing game" OR "You lost" OR "You won"}
                // @todo handle this response 
            }

            @Override
            public void failed(Throwable t) {

            }

            @Override
            public void cancelled() {

            }
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
