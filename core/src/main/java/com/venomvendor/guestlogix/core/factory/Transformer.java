/*
 * Copyright (c) 2019 - Present.
 * Created by VenomVendor on 08-Aug'19.
 */

package com.venomvendor.guestlogix.core.factory;

/**
 * Represents a function that accepts one argument and produces a result.
 *
 * @param <T> the type of the input to the function
 * @param <R> the type of the result of the function
 */
public interface Transformer<T, R> {

    /**
     * Applies this function to the given argument.
     *
     * @param in the function argument
     * @return the function result
     */
    R transform(T in);
}
