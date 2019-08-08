/*
 * Copyright (c) 2019 - Present.
 * Created by VenomVendor on 07-Aug'19.
 */

package com.venomvendor.guestlogix.core.factory;

/**
 * Request params for any given request.
 *
 * @param <T> Type of response
 */
public interface Request<T> {

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
    T getData(String response);

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
