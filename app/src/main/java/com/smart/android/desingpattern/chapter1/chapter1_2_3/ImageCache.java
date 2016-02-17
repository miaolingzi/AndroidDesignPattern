package com.smart.android.desingpattern.chapter1.chapter1_2_3;

import android.graphics.Bitmap;

/**
 * Copyright © 2016 Phoenix New Media Limited All Rights Reserved.
 * Created by fengjh on 16/2/17.
 */
public interface ImageCache {

    void put(String url, Bitmap bitmap);

    Bitmap get(String url);
}
