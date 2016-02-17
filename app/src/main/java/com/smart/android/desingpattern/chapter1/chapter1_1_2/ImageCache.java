package com.smart.android.desingpattern.chapter1.chapter1_1_2;

import android.graphics.Bitmap;
import android.support.v4.util.LruCache;

/**
 * Copyright © 2016 Phoenix New Media Limited All Rights Reserved.
 * Created by fengjh on 16/2/16.
 */
public class ImageCache {

    //图片LRU缓存
    LruCache<String, Bitmap> mImageCache;

    public ImageCache() {
        initImageCache();
    }

    private void initImageCache() {
        //计算可使用的最大内存
        final int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);
        //取1/4的可用内存作为缓存
        final int cacheSize = maxMemory / 4;
        mImageCache = new LruCache<String, Bitmap>(cacheSize) {
            @Override
            protected int sizeOf(String key, Bitmap bitmap) {
                return bitmap.getRowBytes() * bitmap.getHeight() / 1024;
            }
        };
    }

    //将图片放入缓存中
    public void put(String url, Bitmap bitmap) {
        mImageCache.put(url, bitmap);
    }

    //从缓存中取图片
    public Bitmap get(String url) {
        return mImageCache.get(url);
    }
}
