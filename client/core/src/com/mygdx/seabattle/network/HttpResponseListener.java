package com.mygdx.seabattle.network;

import com.badlogic.gdx.Net;

/**
 * Created by mats on 20.04.15.
 */
public class HttpResponseListener implements Net.HttpResponseListener {

    @Override
    public void handleHttpResponse(Net.HttpResponse httpResponse) {
        // This method must be overwritten.
    }

    @Override
    public void failed(Throwable t) {
        t.printStackTrace();
    }

    @Override
    public void cancelled() {
    }
}
