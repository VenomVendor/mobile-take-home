/*
 * Copyright (c) 2019 - Present.
 * Created by VenomVendor on 07-Aug'19.
 */

package com.venomvendor.guestlogix.core.internal;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Pair;

import com.venomvendor.guestlogix.core.ex.GLException;
import com.venomvendor.guestlogix.core.factory.AsyncListener;
import com.venomvendor.guestlogix.core.util.CoreHelper;

import java.util.Objects;

/**
 * Custom Handler to dispatch results to main thread from background thread.
 *
 * @param <T> Type of data.
 */
public class NetworkHandler<T> extends Handler {

    private final AsyncListener<T> mListener;

    public NetworkHandler(Looper looper, AsyncListener<T> listener) {
        super(looper);
        mListener = listener;
    }

    @Override
    public void handleMessage(Message msg) {
        super.handleMessage(msg);
        Pair data = (Pair) msg.obj;
        switch (msg.what) {
            case CoreHelper.NETWORK_OK:
                mListener.onResponse(
                        Objects.requireNonNull((T) data.first, "Data cannot be null")
                );
                break;
            case CoreHelper.NETWORK_ERROR:
                mListener.onError(
                        Objects.requireNonNull((GLException) data.second,
                                "Exception cannot be null")
                );
                break;
            default:
                mListener.onError(new GLException("Unknown Response Received"));
        }
    }
}
