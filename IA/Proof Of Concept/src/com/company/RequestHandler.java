package com.company;

import java.net.http.HttpRequest;
import java.net.http.HttpClient;
import java.net.http.HttpResponse;
import java.net.URI;
import java.util.Arrays;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;


public class RequestHandler {

    public RequestHandler(){
    }

    // Used JsonArray to meke all use the same type, cause the api sometimes returns JsonObject and sometimes JsonArray
    // So this, converts the JsonObject to JsonArray
    public JsonArray jsonify(String data){
        try {
            JsonArray jsondata = new Gson().fromJson(data, JsonArray.class);
            return jsondata;

        } catch (Exception e){
            data = "["+data+"]";
            JsonArray jsondata = new Gson().fromJson(data, JsonArray.class);
            return jsondata;
        }
    }

    public String getString(String uri) throws Exception {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(uri))
                .build();

        HttpResponse<String> response =
                client.send(request, HttpResponse.BodyHandlers.ofString());

        String data = response.body();
        return data;
    }

    public JsonArray get(String uri) throws Exception {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(uri))
                .build();

        HttpResponse<String> response =
                client.send(request, HttpResponse.BodyHandlers.ofString());

        String data = response.body();
        return jsonify(data);
    }

    // Accepts cookies for alpaca specifically for now, make it cookie dynamic
    public JsonArray get(String uri, String header_name_1, String header_value_1, String header_name_2, String header_value_2) throws Exception {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(uri))
                .header(header_name_1, header_value_1)
                .header(header_name_2, header_value_2)
                .build();

        HttpResponse<String> response =
                client.send(request, HttpResponse.BodyHandlers.ofString());

        String data = response.body();
        return jsonify(data);
    }




    public void post(String uri, String data) throws Exception {
        HttpClient client = HttpClient.newBuilder().build();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(uri))
                .POST(HttpRequest.BodyPublishers.ofString(data))
                .build();

        HttpResponse<?> response = client.send(request, HttpResponse.BodyHandlers.discarding());
        System.out.println(response.statusCode());
    }

}