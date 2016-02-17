package com.smart.android.desingpattern.chapter1.chapter1_2_3;

import android.graphics.Bitmap;
import android.support.v4.util.LruCache;

/**
 * Copyright © 2016 Phoenix New Media Limited All Rights Reserved.
 * Created by fengjh on 16/2/17.
 */
public class MemoryCache implements ImageCache {

    //图片LRC缓存
    private LruCache<String, Bitmap> mLruCache;

    public MemoryCache() {
        initMemoryCache();
    }

    private void initMemoryCache() {
        //计算可使用到最大内存
        final int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);
        //取1/4可用内存作为缓存
        int cacheSize = maxMemory / 4;
        mLruCache = new LruCache<String, Bitmap>(cacheSize) {
            @Override
            protected int sizeOf(String key, Bitmap bitmap) {
                return bitmap.getRowBytes() * bitmap.getHeight() / 1024;
            }
        };
    }

    @Override
    public void put(String url, Bitmap bitmap) {
        if (mLruCache != null) {
            mLruCache.put(url, bitmap);
        }
    }

    @Override
    public Bitmap get(String url) {
        return mLruCache.get(url);
    }
}
