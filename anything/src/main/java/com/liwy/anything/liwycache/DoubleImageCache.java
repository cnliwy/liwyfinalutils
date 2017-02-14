package com.liwy.anything.liwycache;

import android.graphics.Bitmap;

/**
 * 图片的双缓存
 * Created by liwy on 16/7/10.
 */
public class DoubleImageCache extends ALiwyCache implements ILiwyCache<Bitmap> {
    private MemoryImageCache memoryImageCache = new MemoryImageCache();
    private DiskImageCache diskImageCache = new DiskImageCache();
    @Override
    public Bitmap getValueForKey(String key) {
        Bitmap bitmap = memoryImageCache.getValueForKey(key);
        if (bitmap == null){
            bitmap = diskImageCache.getValueForKey(key);
        }
        return bitmap;
    }

    @Override
    public void setValueToCache(String key, Bitmap obj) {
        memoryImageCache.setValueToCache(key,obj);
        diskImageCache.setValueToCache(key,obj);
    }

    @Override
    public void deleteForKey(String key) {
        memoryImageCache.deleteForKey(key);
        diskImageCache.deleteForKey(ConfigCache.imageCaheDir + key);
    }

    @Override
    public void deleteAll() {
        memoryImageCache.deleteAll();
        diskImageCache.deleteAll();
    }
}
