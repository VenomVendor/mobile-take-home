/*
 * Copyright (c) 2019 - Present.
 * Created by VenomVendor on 07-Aug'19.
 */

package com.venomvendor.guestlogix.core.internal;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Manager Thread Pools for network requests
 */
public final class ThreadPoolManager {

    private static final int MAX_REQUEST = 5;
    private static final ExecutorService executor = Executors.newFixedThreadPool(MAX_REQUEST);

    public static void dispatch(Runnable command) {
        executor.execute(command);
    }
}
