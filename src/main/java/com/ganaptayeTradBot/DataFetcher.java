package com.ganaptayeTradBot;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import java.io.IOException;

public class DataFetcher {
    private final OkHttpClient client = new OkHttpClient();
    private final String apiUrl = "https://api.example.com/market-data"; // Replace with actual URL

    public String fetchMarketData() throws IOException {
        Request request = new Request.Builder()
                .url(apiUrl)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response);
            }
            return response.body().string(); // Return the data as a String
        }
    }
}
