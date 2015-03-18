package network;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net;
import com.badlogic.gdx.net.HttpRequestBuilder;
import com.badlogic.gdx.utils.JsonValue;

/**
 * Created by root on 12.03.15.
 */
public class NetworkHelper {
    private static String host = "localhost";
    private static int port = 5000;

    public static void sendPostRequest(String route, String data, Net.HttpResponseListener listener){
        HttpRequestBuilder requestBuilder = new HttpRequestBuilder();
        String url = host + ":" + port + "/api" + route;
        Net.HttpRequest httpRequest = requestBuilder.newRequest().method(Net.HttpMethods.POST).url(url).build();
        httpRequest.setHeader("Content-Type", "application/json");
        httpRequest.setContent(data);

        Gdx.net.sendHttpRequest(httpRequest, listener);
    }

    public static void sendGetRequest(String route, Net.HttpResponseListener listener){
        HttpRequestBuilder requestBuilder = new HttpRequestBuilder();
        String url = host + ":" + port + "/api" + route;
        Net.HttpRequest httpRequest = requestBuilder.newRequest().method(Net.HttpMethods.GET).url(url).build();
        httpRequest.setHeader("Content-Type", "application/json");

        Gdx.net.sendHttpRequest(httpRequest, listener);

    }
}
