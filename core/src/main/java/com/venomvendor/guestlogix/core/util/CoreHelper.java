/*
 * Copyright (c) 2019 - Present.
 * Created by VenomVendor on 07-Aug'19.
 */

package com.venomvendor.guestlogix.core.util;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Pair;

import com.venomvendor.guestlogix.core.ex.GLException;
import com.venomvendor.guestlogix.core.factory.AsyncListener;
import com.venomvendor.guestlogix.core.internal.NetworkHandler;

import java.util.Objects;

public final class CoreHelper {

    public static final int NETWORK_OK = 0x200;
    public static final int NETWORK_ERROR = 0x500;

    private CoreHelper() {
        throw new UnsupportedOperationException("No Instance");
    }

    /**
     * Returns custom exception from Given Exception
     */
    public static GLException getException(Exception ex) {
        Objects.requireNonNull(ex, "Exception cannot be null");

        return new GLException(ex.getMessage(), ex.getCause());
    }

    public static <T> Handler getMainHandler(AsyncListener<T> listener) {
        return new NetworkHandler<>(Looper.getMainLooper(), listener);
    }

    public static <T> Message getNetworkMessage(Pair<T, GLException> result) {
        Message message = new Message();
        message.what = result.first != null ? NETWORK_OK : NETWORK_ERROR;
        message.obj = result;

        return message;
    }

    public static <T> void dispatchMessage(AsyncListener<T> listener, Pair<T, GLException> result) {
        getMainHandler(listener)
                .sendMessage(getNetworkMessage(result));
    }
}
