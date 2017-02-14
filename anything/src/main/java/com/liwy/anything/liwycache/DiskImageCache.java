package com.liwy.anything.liwycache;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.liwy.anything.utils.CloseUtil;
import com.liwy.anything.utils.FileUtil;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 * 图片的磁盘缓存
 * Created by liwy on 16/7/10.
 */
public class DiskImageCache extends ALiwyCache implements ILiwyCache<Bitmap> {

    @Override
    public Bitmap getValueForKey(String key) {
        //从本地磁盘读取文件
        BitmapFactory.decodeFile(ConfigCache.imageCaheDir + key);
        return null;
    }

    @Override
    public void setValueToCache(String key, Bitmap bmp) {
        //将文件存储到本地磁盘
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(ConfigCache.imageCaheDir + key);
            bmp.compress(Bitmap.CompressFormat.PNG,100,fileOutputStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            CloseUtil.close(fileOutputStream);//关闭输出流
        }

    }


    @Override
    public void deleteForKey(String key) {
        FileUtil.delFile(ConfigCache.imageCaheDir + key);
    }

    @Override
    public void deleteAll() {
        FileUtil.deleteDir(ConfigCache.imageCaheDir);
    }
}
