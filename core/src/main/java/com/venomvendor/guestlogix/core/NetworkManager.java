/*
 * Copyright (c) 2019 - Present.
 * Created by VenomVendor on 07-Aug'19.
 */

package com.venomvendor.guestlogix.core.util;

import android.util.Pair;

import com.venomvendor.guestlogix.core.ex.GLException;
import com.venomvendor.guestlogix.core.factory.AsyncListener;
import com.venomvendor.guestlogix.core.factory.Request;
import com.venomvendor.guestlogix.core.internal.ThreadPoolManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Objects;

@SuppressWarnings("ALL")
public final class NetworkManager {

    private static final NetworkManager INSTANCE = new NetworkManager();

    private NetworkManager() {
        // Required Hidden
    }

    public static NetworkManager getInstance() {
        return INSTANCE;
    }

    public <T> void execute(Request<T> request, AsyncListener<T> listener) {
        Objects.requireNonNull(request, "Request cannot be null");
        Objects.requireNonNull(listener, "Listener cannot be null");

        ThreadPoolManager.dispatch(() -> {
            T data = null;
            GLException glException = null;
            try {
                String response = getResponse(request);
                data = (T) request.getData(response);
            } catch (IOException ex) {
                glException = CoreHelper.getException(ex);
            }

            Pair<T, GLException> result = Pair.create(data, glException);
            CoreHelper.dispatchMessage(listener, result);
        });
    }

    /**
     * Returns the output from the given URL.
     *
     * @param request Request to be executed.
     * @return response in form of string.
     */
    private String getResponse(Request request) throws IOException {
        String encodeUrl = request.getUrl();
        URL url = new URL(encodeUrl);

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
}
