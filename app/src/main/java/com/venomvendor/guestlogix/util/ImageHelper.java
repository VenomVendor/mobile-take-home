/*
 * Copyright (c) 2019 - Present.
 * Created by VenomVendor on 08-Aug'19.
 */

package com.venomvendor.guestlogix.util;

import android.graphics.Bitmap;

import com.venomvendor.guestlogix.core.NetworkManager;
import com.venomvendor.guestlogix.core.factory.AsyncListener;

public final class ImageHelper {

    private ImageHelper() {
        throw new UnsupportedOperationException("No Instance");
    }

    public static void getImage(String url, AsyncListener<Bitmap> listener) {
        NetworkManager.getInstance()
                .getBitmapFromURL(url, listener);
    }
}
