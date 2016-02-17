package com.smart.android.desingpattern.chapter1.chapter1_2_2;

import android.graphics.Bitmap;

/**
 * 双缓存.获取图片时先从内存缓存中获取,如果内存中没有缓存该图片,再从sd卡中获取.
 * 缓存图片也是在内存喝sd卡中都缓存一份
 * Copyright © 2016 Phoenix New Media Limited All Rights Reserved.
 * Created by fengjh on 16/2/16.
 */
public class DoubleCache {

    private ImageCache mImageCache = new ImageCache();
    private DiskCache mDiskCache = new DiskCache();

    //先从内存缓存中获取图片,如果没有,再从sd卡中获取
    public Bitmap get(String url) {
        Bitmap bitmap = null;
        bitmap = mImageCache.get(url);
        if (bitmap == null) {
            bitmap = mDiskCache.get(url);
        }
        return bitmap;
    }

    //将图片缓存到内存和sd卡中
    public void put(String url, Bitmap bitmap) {
        mImageCache.put(url, bitmap);
        mDiskCache.put(url, bitmap);
    }
}
