package com.smart.android.desingpattern.chapter1.chapter1_2_1;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.widget.ImageView;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Copyright © 2016 Phoenix New Media Limited All Rights Reserved.
 * Created by fengjh on 16/2/16.
 */
public class ImageLoader {

    //内存缓存
    private ImageCache mImageCache = new ImageCache();
    //SD卡缓存
    private DiskCache mDiskCache = new DiskCache();
    //是否使用SD卡缓存
    private boolean isUseDiskCache = false;
    //线程池,线程数量为CPU可用线程的数量
    private ExecutorService mExecutorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    //展示图片
    public void displayImage(final String url, final ImageView imageView) {
        //判断使用哪种缓存
        Bitmap bitmap = isUseDiskCache ? mDiskCache.get(url) : mImageCache.get(url);
        if (bitmap != null) {
            imageView.setImageBitmap(bitmap);
            return;
        }
        //没有缓存,则提交给线程池进行下载
        imageView.setTag(url);
        mExecutorService.submit(new Runnable() {
            @Override
            public void run() {
                Bitmap bitmap = downloadImage(url);
                if (bitmap == null) {
                    return;
                }
                if (imageView.getTag().equals(url)) {
                    imageView.setImageBitmap(bitmap);
                }
                //将数据放入缓存
                if (isUseDiskCache) {
                    mDiskCache.put(url, bitmap);
                } else {
                    mImageCache.put(url, bitmap);
                }
            }
        });
    }

    //下载图片
    public Bitmap downloadImage(String imageUrl) {
        Bitmap bitmap = null;
        try {
            URL url = new URL(imageUrl);
            final HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            bitmap = BitmapFactory.decodeStream(conn.getInputStream());
            conn.disconnect();
        } catch (Exception e) {
            Log.e("ImageLoader", "Exception", e);
        }
        return bitmap;
    }

    public void useDiskCache(boolean isUseDiskCache) {
        this.isUseDiskCache = isUseDiskCache;
    }
}
