package network;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net;
import com.badlogic.gdx.net.HttpRequestBuilder;
import com.badlogic.gdx.net.NetJavaImpl;

/**
 * Created by esso on 12.03.15.
 */
public class NetworkHelper {
    private static String host = "https://fathomless-waters-2425.herokuapp.com";

    /**
     * sendGetRequest
     * @param route
     * @param jsonData
     * @param listener
     * @description Takes a route and some jsondata and sends it to the server as POST. Uses listener for the response
     */
    public static void sendPostRequest(String route, String jsonData, Net.HttpResponseListener listener){
        HttpRequestBuilder requestBuilder = new HttpRequestBuilder();
        String url = host + "/api" + route;

        Net.HttpRequest httpRequest = requestBuilder.newRequest().method(Net.HttpMethods.POST).url(url).build();
        httpRequest.setHeader("Content-Type", "application/json");
        httpRequest.setContent(jsonData);

        NetJavaImpl net = new NetJavaImpl();
        net.sendHttpRequest(httpRequest, listener);
    }

    /**
     * sendGetRequest
     * @param route
     * @param listener
     * @description Takes a route and some jsondata and sends it to the server as GET. Uses listener for the response
     */
    public static void sendGetRequest(String route, Net.HttpResponseListener listener){
        HttpRequestBuilder requestBuilder = new HttpRequestBuilder();
        String url = host + "/api" + route;
        Net.HttpRequest httpRequest = requestBuilder.newRequest().method(Net.HttpMethods.GET).url(url).build();
        httpRequest.setHeader("Content-Type", "application/json");

        NetJavaImpl net = new NetJavaImpl();
        net.sendHttpRequest(httpRequest, listener);

    }

    private static void printRequest(Net.HttpRequest httpRequest) {
        System.out.println("URL: " + httpRequest.getUrl());
        System.out.println("Method: " + httpRequest.getMethod());
        System.out.println("Headers: " + httpRequest.getHeaders());
        System.out.println("Content: " + httpRequest.getContent());
    }
}
