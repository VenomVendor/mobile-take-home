/*
 * Copyright (c) 2019 - Present.
 * Created by VenomVendor on 07-Aug'19.
 */

package com.venomvendor.guestlogix.core.factory;

import com.venomvendor.guestlogix.core.ex.GLException;

/**
 * Asynchronous Callback
 *
 * @param <T> Type of data
 */
public interface AsyncListener<T> {

    /**
     * When successful response is recieved
     *
     * @param response data dispatched, always non-null
     */
    void onResponse(T response);

    /**
     * When error is received
     *
     * @param ex exception
     */
    void onError(GLException ex);
}
