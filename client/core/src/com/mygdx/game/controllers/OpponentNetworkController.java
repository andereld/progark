package com.mygdx.game.controllers;

import com.badlogic.gdx.Net;
import com.mygdx.game.models.Player;

import java.util.Timer;
import java.util.TimerTask;

import network.NetworkHelper;

/**
 * Created by esso on 16.03.15.
 */
public class OpponentNetworkController {
    private Player opponent;

    public OpponentNetworkController(Player opponent){
        setOpponent(opponent);
    }

    public void waitForOpponent(final Player thisPlayer) {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                NetworkHelper.sendGetRequest("/fire"+thisPlayer.getUsername(), new Net.HttpResponseListener() {
                    @Override
                    public void handleHttpResponse(Net.HttpResponse httpResponse) {
                        httpResponse.getResultAsString()
                    }

                    @Override
                    public void failed(Throwable t) {

                    }

                    @Override
                    public void cancelled() {

                    }
                });
            }
        }, 10000);
    }


    public Player getOpponent() {
        return opponent;
    }

    public void setOpponent(Player opponent) {
        this.opponent = opponent;
    }
}
