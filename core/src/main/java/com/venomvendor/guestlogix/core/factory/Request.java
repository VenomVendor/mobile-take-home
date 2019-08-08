/*
 * Copyright (c) 2019 - Present.
 * Created by VenomVendor on 07-Aug'19.
 */

package com.venomvendor.guestlogix.core.factory;

import org.json.JSONException;

import java.util.Map;

/**
 * Request params for any given request.
 *
 * @param <T> Type of response
 */
public interface Request<T> {

    int TIMEOUT = 10_000;

    /**
     * Generates full URL for the request
     *
     * @return full url
     */
    String getUrl();

    /**
     * Set the method for the URL request
     *
     * @return one of {@link Method}
     */
    Method getMethod();

    /**
     * @return an {@code int} that specifies the timeout value to be used in milliseconds
     */
    int getTimeoutInMs();

    /**
     * Parses data from given response
     *
     * @return T required data
     */
    T getData(String response) throws JSONException;

    /**
     * Get Query for Get Requests
     *
     * @return Map of query params
     */
    Map<String, String> getQuery();

    /**
     * Get Body for
     *
     * @return body if request is not GET
     */
    byte[] getBody();

    /**
     * List of supported Request types.
     */
    enum Method {
        GET,
        POST,
        HEAD,
        OPTIONS,
        PUT,
        DELETE,
        TRACE,
    }
}
