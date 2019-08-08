/*
 * Copyright (c) 2019 - Present.
 * Created by VenomVendor on 08-Aug'19.
 */

package com.venomvendor.guestlogix.util;

import android.graphics.Bitmap;

import com.venomvendor.guestlogix.core.NetworkManager;
import com.venomvendor.guestlogix.core.factory.AsyncListener;

/**
 * Helper to download image
 */
public final class ImageHelper {

    private ImageHelper() {
        throw new UnsupportedOperationException("No Instance");
    }

    /**
     * Get image from the given url
     *
     * @param url      Url of the image
     * @param listener Callback in which data is sent back.
     */
    public static void getImage(String url, AsyncListener<Bitmap> listener) {
        NetworkManager.getInstance()
                .getBitmapFromURL(url, listener);
    }
}
