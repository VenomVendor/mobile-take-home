/*
 * Copyright (c) 2019 - Present.
 * Created by VenomVendor on 07-Aug'19.
 */

package com.venomvendor.guestlogix.core.ex;

/**
 * Custom Implementation for future usage
 */
public class GLException extends RuntimeException {

    public GLException(String message) {
        super(message);
    }

    public GLException(String message, Throwable cause) {
        super(message, cause);
    }
}
