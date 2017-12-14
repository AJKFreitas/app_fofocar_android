package br.com.ajkfreitas.apps.fofocar.util;

import java.util.List;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Arrays;
import java.util.List;

import static java.util.Objects.requireNonNull;
/**
 * Created by Angelo Freitas on 14/12/2017.
 */

public class RestClient {
    private final HTTPClient http;
    private final Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
            .create();

    public RestClient() {
        http = new HTTPClient();
    }

    public <T> List<T> getAll(String uri, Class<T[]> type) {
        requireNonNull(uri);
        requireNonNull(type);

        String json = http.get(uri);

        return Arrays.asList(gson.fromJson(json, type));
    }

    public <T> T post(String uri, T object, Class<? extends T> type) {
        requireNonNull(object);

        String json = http.post(uri, gson.toJson(object));

        return gson.fromJson(json, type);
    }
}
