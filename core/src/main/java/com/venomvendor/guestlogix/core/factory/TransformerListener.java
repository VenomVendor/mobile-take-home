/*
 * Copyright (c) 2019 - Present.
 * Created by VenomVendor on 08-Aug'19.
 */

package com.venomvendor.guestlogix.core.factory;

import com.venomvendor.guestlogix.core.ex.GLException;

import java.util.Objects;

public class TransformerListener<T, R> implements AsyncListener<T> {

    private AsyncListener<R> listener;
    private Transformer<T, R> transformer;

    public TransformerListener(AsyncListener<R> listener,
                               Transformer<T, R> transformer) {
        this.listener = Objects.requireNonNull(listener, "Listener cannot be null");
        this.transformer = Objects.requireNonNull(transformer, "Transformer cannot be null");
    }

    @Override
    public void onResponse(T response) {
        listener.onResponse(transformer.transform(response));
    }

    @Override
    public void onError(GLException ex) {
        listener.onError(ex);
    }
}
