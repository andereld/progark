package network;

import com.badlogic.gdx.Net;

/**
 * Created by root on 12.03.15.
 */
public class TestMain {

    public static void main (String args[]){
        NetworkHelper.sendGetRequest("/fire", "{}jsdapsjd", new Net.HttpResponseListener() {
            @Override
            public void handleHttpResponse(Net.HttpResponse httpResponse) {
                httpResponse.getResultAsString();
            }

            @Override
            public void failed(Throwable t) {

            }

            @Override
            public void cancelled() {

            }
        });
    }
}
