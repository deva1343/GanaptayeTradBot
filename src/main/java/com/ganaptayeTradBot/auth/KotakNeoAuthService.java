package com.ganaptayeTradBot.auth;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import java.io.IOException;

@Service
public class KotakNeoAuthService {

    @Value("${kotakneo.api.token-url}")
    private String tokenUrl;

    @Value("${kotakneo.api.revoke-url}")
    private String revokeUrl;

    @Value("${kotakneo.consumer.key}")
    private String consumerKey;

    @Value("${kotakneo.consumer.secret}")
    private String consumerSecret;

    @Value("${kotakneo.access.token}")
    private String accessToken;

    private final OkHttpClient client = new OkHttpClient();

    public String getSessionToken(String otp) throws IOException {
        // Example implementation for viewing a session token
        Request request = new Request.Builder()
            .url(tokenUrl)
            .header("Authorization", "Bearer " + accessToken)
            .header("Content-Type", "application/json")
            .post(RequestBody.create("{\"otp\":\"" + otp + "\"}", null))
            .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response);
            }
            return response.body().string();
        }
    }

    public String generateOtp() throws IOException {
        // Implementation for generating OTP
        // Send request to Kotak Neo's OTP generation endpoint
        // Example placeholder implementation
        Request request = new Request.Builder()
            .url("https://gw-napi.kotaksecurities.com/login/1.0/login/v2/validate")
            .header("Authorization", "Bearer " + accessToken)
            .header("Content-Type", "application/json")
            .post(RequestBody.create("{}", null)) // Adjust body as required by API
            .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response);
            }
            return response.body().string(); // Handle OTP response as needed
        }
    }

    public String viewToken() throws IOException {
        // Implementation for viewing the token
        // Typically involves sending a request to the token endpoint
        // Example placeholder implementation
        Request request = new Request.Builder()
            .url(tokenUrl)
            .header("Authorization", "Bearer " + accessToken)
            .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response);
            }
            return response.body().string();
        }
    }

    public void revokeToken(String token) throws IOException {
        // Implementation for revoking the token
        Request request = new Request.Builder()
            .url(revokeUrl)
            .header("Authorization", "Bearer " + token)
            .post(RequestBody.create("", null))
            .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response);
            }
        }
    }
}
