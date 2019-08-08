/*
 * Copyright (c) 2019 - Present.
 * Created by VenomVendor on 08-Aug'19.
 */

package com.venomvendor.guestlogix.core.network;

import com.venomvendor.guestlogix.core.factory.Request;

import java.util.Map;

public abstract class BaseRequest<T> implements Request<T> {

    @Override
    public int getTimeoutInMs() {
        return TIMEOUT;
    }

    @Override
    public Map<String, String> getQuery() {
        return null;
    }

    @Override
    public byte[] getBody() {
        return new byte[0];
    }
}
