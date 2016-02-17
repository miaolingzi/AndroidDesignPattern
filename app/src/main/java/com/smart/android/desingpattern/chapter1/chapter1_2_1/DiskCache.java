package com.smart.android.desingpattern.chapter1.chapter1_2_1;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Copyright © 2016 Phoenix New Media Limited All Rights Reserved.
 * Created by fengjh on 16/2/16.
 */
public class DiskCache {

    private static final String cacheDir = "sdcard/cache/";

    //从缓存中获取图片
    public Bitmap get(String url) {
        return BitmapFactory.decodeFile(cacheDir + url);
    }

    //将图片缓存到内存中
    public void put(String url, Bitmap bitmap) {
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(cacheDir + url);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
        } catch (FileNotFoundException e) {
            Log.e("DiskCache", "FileNotFoundException", e);
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    Log.e("DiskCache", "IOException", e);
                }
            }
        }
    }
}
