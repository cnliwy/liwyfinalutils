package com.liwy.anything.liwycache;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.util.Log;
import android.widget.ImageView;

import com.liwy.anything.R;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 图片缓存
 * Created by liwy on 16/7/10.
 */
public class LiwyImageCache {
    //缓存类
    public ILiwyCache<Bitmap> liwyCache = MemoryImageCache.getSingleInstance();
    //注入缓存实现,可以注入内存缓存,磁盘缓存和双缓存,共三个缓存实现方式
    public void setILiwyCache(ILiwyCache cache){
        liwyCache = cache;
    }

    //线程池,线程数量为CPU的数量
    ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    public void displayImage(String imageUrl, ImageView imageView){
        Bitmap bitmap = liwyCache.getValueForKey(imageUrl);
        if (bitmap != null){
            Log.e("TAG", "displayImage: 已存在");
            imageView.setImageBitmap(bitmap);
            return;
        }else{
            Log.e("TAG", "displayImage: 不存在,马上加载!");
        }
        //图片没缓存,提交到线程池中下载图片
        submitLoadRequest(imageUrl,imageView);
    }
    //下载线程池
    private void submitLoadRequest(final String imageUrl,final ImageView imageView){
        imageView.setTag(imageUrl);
        executorService.submit(new Runnable() {
            @Override
            public void run() {
                Bitmap bitmap = downloadImage(imageUrl);
                if (bitmap == null){
                    Log.e("TAG", "submitLoadRequest: 下载图片为空!");
                    return;
                }
                liwyCache.setValueToCache(imageUrl,bitmap);
                if (imageView.getTag().equals(imageUrl)){
                    Log.e("TAG", "submitLoadRequest: 加载完成!");
                    imageView.setBackgroundColor(Color.parseColor("#3366cc"));
                   imageView.setImageBitmap(bitmap);
                }
            }
        });
    }

    //下载图片
    public Bitmap downloadImage(String imageUrl){
        Bitmap bitmap = null;
        try {
            URL url = new URL(imageUrl);
            final HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            bitmap = BitmapFactory.decodeStream(conn.getInputStream());
            conn.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bitmap;
    }


}
