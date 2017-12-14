package br.com.ajkfreitas.apps.fofocar.util;

import android.support.annotation.NonNull;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
/**
 * Created by Angelo Freitas on 14/12/2017.
 */

class HTTPClient {
    private final OkHttpClient client;

    public HTTPClient() {
        client = new OkHttpClient.Builder()
                .addInterceptor(new LoggingInterceptor())
                .build();
    }

    @NonNull
    public String get(String uri) {
        Response response = null;
        String body;
        try {
            Request request = new Request.Builder()
                    .url(uri)
                    .header("Content-Type", "application/json")
                    .build();
            response = client.newCall(request).execute();
            body = response.body().string();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (response != null) {
                response.body().close();
            }
        }
        return body;
    }


    public String post(String uri, String data) {
        Response response = null;
        String body;
        try {
            Request request = new Request.Builder()
                    .url(uri)
                    .post(RequestBody.create(MediaType.parse("application/json"), data))
                    .build();
            response = client.newCall(request).execute();
            body = response.body().string();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (response != null) {
                response.body().close();
            }
        }
        return body;
    }
}
