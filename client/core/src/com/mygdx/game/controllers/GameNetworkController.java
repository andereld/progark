package com.mygdx.game.controllers;

import com.badlogic.gdx.Net;
import com.badlogic.gdx.utils.JsonValue;
import com.mygdx.game.models.*;

import json.JsonHelper;
import network.NetworkHelper;

/**
 * Created by esso on 13.03.15.
 */
public class GameNetworkController {
    private PlayerNetworkController playerController;
    private OpponentNetworkController opponentController;

    public Board startGame(String username){
        JsonValue json = new JsonValue(username);
        json.setName("username");
        NetworkHelper.sendPostRequest("/play", json, new Net.HttpResponseListener() {
            @Override
            public void handleHttpResponse(Net.HttpResponse httpResponse) {
                JsonValue jsonResponse = JsonHelper.getJSonObject(httpResponse.getResultAsString());
                if (jsonResponse.get("message") != null){ // There is a board response
                    Board board = new Board();
                    board.createFromJson(jsonResponse.toString());

                    playerController.getPlayer().setBoard(board);
                } else {
                    opponentController.waitForOpponent(playerController.getPlayer());
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

}
