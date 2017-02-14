package com.liwy.anything.liwycache;

import android.graphics.Bitmap;
import android.util.LruCache;

/**
 * 图片的内存缓存实现类
 * Created by liwy on 16/7/10.
 */
public class MemoryImageCache extends ALiwyCache implements ILiwyCache<Bitmap> {
    private LruCache<String, Bitmap> memoryCache;
    private static MemoryImageCache singleInstance = null;

    //保证缓存实例只有一个
    public static MemoryImageCache getSingleInstance(){
        if (singleInstance == null){
            synchronized (MemoryImageCache.class){
                singleInstance = new MemoryImageCache();
            }
        }
        return singleInstance;
    }

    public MemoryImageCache() {
        initCache();
    }

    //初始化缓存空间
    private void initCache(){
        //计算可用的最大内存
        final int maxMemory = (int)(Runtime.getRuntime().maxMemory()/1024);
        //取1/4的可用内存作为缓存
        final int cacheSize = maxMemory / 4;
        memoryCache = new LruCache<String, Bitmap>(cacheSize){
            @Override
            protected int sizeOf(String key, Bitmap bitmap) {
                return bitmap.getRowBytes() * bitmap.getHeight()/1024;
            }
        };
    }
    @Override
    public Bitmap getValueForKey(String key) {
        return memoryCache.get(key);
    }

    @Override
    public void setValueToCache(String key, Bitmap obj) {
        memoryCache.put(key, obj);
    }

    @Override
    public void deleteForKey(String key) {
        memoryCache.remove(key);
    }

    @Override
    public void deleteAll() {
        memoryCache.evictAll();
    }
}
