package network;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net;
import com.badlogic.gdx.net.HttpRequestBuilder;
import com.badlogic.gdx.net.NetJavaImpl;

/**
 * Created by root on 12.03.15.
 */
public class NetworkHelper {
    private static String host = "http://localhost";
    private static int port = 5000;

    public static void sendPostRequest(String route, String data, Net.HttpResponseListener listener){
        HttpRequestBuilder requestBuilder = new HttpRequestBuilder();
        String url = host + ":" + port + "/api" + route;
        Net.HttpRequest httpRequest = requestBuilder.newRequest().method(Net.HttpMethods.POST).url(url).build();
        httpRequest.setHeader("Content-Type", "application/json");
        httpRequest.setContent(data);

        NetworkHelper.printRequest(httpRequest);

        NetJavaImpl net = new NetJavaImpl();
        net.sendHttpRequest(httpRequest, listener);
    }

    public static void sendGetRequest(String route, Net.HttpResponseListener listener){
        HttpRequestBuilder requestBuilder = new HttpRequestBuilder();
        String url = host + ":" + port + "/api" + route;
        Net.HttpRequest httpRequest = requestBuilder.newRequest().method(Net.HttpMethods.GET).url(url).build();
        httpRequest.setHeader("Content-Type", "application/json");

        printRequest(httpRequest);
        NetJavaImpl net = new NetJavaImpl();
        net.sendHttpRequest(httpRequest, listener);

    }

    private static void printRequest(Net.HttpRequest httpRequest){
        System.out.println("URL: " + httpRequest.getUrl());
        System.out.println("Method: " + httpRequest.getMethod());
        System.out.println("Headers: " + httpRequest.getHeaders());
        System.out.println("Content: " + httpRequest.getContent());
    }
}
