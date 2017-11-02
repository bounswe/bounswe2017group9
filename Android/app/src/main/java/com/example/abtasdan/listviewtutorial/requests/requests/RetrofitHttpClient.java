package com.example.abtasdan.listviewtutorial.requests.requests;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.OkUrlFactory;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import retrofit.client.Request;
import retrofit.client.UrlConnectionClient;

public class RetrofitHttpClient extends UrlConnectionClient {

    private static final int CONNECT_TIMEOUT_MILLIS = 60 * 1000; // 30s
    private static final int READ_TIMEOUT_MILLIS = 85 * 1000; // 45s
    private final OkUrlFactory factory;

    public RetrofitHttpClient() {
        factory = generateDefaultOkUrlFactory();
    }

    private static OkUrlFactory generateDefaultOkUrlFactory() {
        OkHttpClient client = new OkHttpClient();
        client.setConnectTimeout(CONNECT_TIMEOUT_MILLIS, TimeUnit.MILLISECONDS);
        client.setReadTimeout(READ_TIMEOUT_MILLIS, TimeUnit.MILLISECONDS);
        return new OkUrlFactory(client);
    }

    @Override
    protected HttpURLConnection openConnection(Request request) throws IOException {
        return factory.open(new URL(request.getUrl()));
    }
}
