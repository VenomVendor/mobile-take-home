/*
 * Copyright (c) 2019 - Present.
 * Created by VenomVendor on 07-Aug'19.
 */

package com.venomvendor.guestlogix.core;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Pair;

import com.venomvendor.guestlogix.core.ex.GLException;
import com.venomvendor.guestlogix.core.factory.AsyncListener;
import com.venomvendor.guestlogix.core.factory.Request;
import com.venomvendor.guestlogix.core.internal.ThreadPoolManager;
import com.venomvendor.guestlogix.core.util.CoreHelper;

import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * Network Manager to handle all newtork operations.
 */
@SuppressWarnings("ALL")
public final class NetworkManager {

    private static final NetworkManager INSTANCE = new NetworkManager();

    private NetworkManager() {
        // Required Hidden
    }

    public static NetworkManager getInstance() {
        return INSTANCE;
    }

    /**
     * Returns the output from the given URL.
     *
     * @param request Request to be executed.
     * @return response in form of string.
     */
    private static String getResponse(Request request) throws IOException {
        // Get Url
        String reqUrl = request.getUrl();

        // Get query params
        Map<String, String> query = request.getQuery();

        // Create full Url
        String fullUrl = getFormattedUrl(reqUrl, query);

        // Create url
        URL url = new URL(reqUrl);
        BufferedReader reader = null;

        try {
            // Http(s)URLConnection
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod(request.getMethod().toString());
            connection.setReadTimeout(request.getTimeoutInMs());
            connection.setConnectTimeout(request.getTimeoutInMs());
            connection.setInstanceFollowRedirects(true);

            connection.connect();

            int responseCode = connection.getResponseCode();

            if (responseCode >= HttpURLConnection.HTTP_OK && responseCode < HttpURLConnection.HTTP_MULT_CHOICE) {
                // read the output from the server
                reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder response = new StringBuilder();

                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line).append("\n");
                }
                return response.toString();
            }

            // This can be improved & updated in lot many ways [304, 401, 403, ...]
            throw new IOException("Invalid Status code recieved " + responseCode);
        } finally {
            // Close the reader; this can also throw exception
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException ignore) {

                }
            }
        }
    }

    private static String getFormattedUrl(String reqUrl, Map<String, String> query)
            throws UnsupportedEncodingException {
        if (query == null || query.isEmpty()) {
            return reqUrl;
        }

        Set<Map.Entry<String, String>> entries = query.entrySet();
        StringBuilder encodedParams = new StringBuilder(reqUrl).append('?');
        for (Map.Entry<String, String> entry : entries) {
            String key = entry.getKey();
            Object value = entry.getValue();
            encodedParams.append(URLEncoder.encode(key, StandardCharsets.UTF_8.toString()))
                    // Appending char is faster than String
                    .append('=')
                    .append(URLEncoder.encode(value.toString(), StandardCharsets.UTF_8.toString()))
                    // Appending char is faster than String
                    .append('&');
        }
        encodedParams.deleteCharAt(encodedParams.length() - 1);

        return encodedParams.toString();
    }

    /**
     * Execute network requests in background.
     *
     * @param request  Request to be executed.
     * @param listener Callback to be used to dispatch result.
     * @param <T>      Type of data.
     */
    public <T> void execute(Request<T> request, AsyncListener<T> listener) {
        Objects.requireNonNull(request, "Request cannot be null");
        Objects.requireNonNull(listener, "Listener cannot be null");

        ThreadPoolManager.dispatch(() -> {
            T data = null;
            GLException glException = null;
            try {
                String response = getResponse(request);
                Objects.requireNonNull(response, "Null Response Recieved");
                data = (T) request.getData(response);
            } catch (IOException | NullPointerException | JSONException ex) {
                glException = CoreHelper.getException(ex);
            }

            Pair<T, GLException> result = Pair.create(data, glException);
            CoreHelper.dispatchMessage(listener, result);
        });
    }

    private Bitmap getBitmap(String imageUrl) throws IOException {
        URL url = new URL(imageUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setDoInput(true);
        connection.setFollowRedirects(true);
        connection.connect();
        InputStream input = connection.getInputStream();
        return BitmapFactory.decodeStream(input);
    }

    public void getBitmapFromURL(String imageUrl, AsyncListener<Bitmap> listener) {
        Objects.requireNonNull(imageUrl, "Image Url cannot be null");
        ThreadPoolManager.dispatch(() -> {
            Bitmap data = null;
            GLException glException = null;
            try {
                data = getBitmap(imageUrl);
                Objects.requireNonNull(data, "Null Response Recieved");
            } catch (IOException | NullPointerException ex) {
                glException = CoreHelper.getException(ex);
            }

            Pair<Bitmap, GLException> result = Pair.create(data, glException);
            CoreHelper.dispatchMessage(listener, result);
        });
    }
}
