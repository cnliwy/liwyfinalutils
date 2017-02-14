package com.liwy.anything.utils;

import java.io.File;

/**
 * 文件管理类
 * Created by liwy on 16/7/10.
 */
public class FileUtil {
    /**
    * 判断文件是否存在,fileName为绝对路径
    * @author liwy
    * @created at 16/7/10 上午10:19
    */
    public static boolean isFileExist(String fileName) {
        File file = new File(fileName);
        file.isFile();
        return file.exists();
    }

    //删除单个文件
    public static void delFile(String fileName){
        File file = new File(fileName);
        if(file.isFile()){
            file.delete();
        }
    }

    //删除文件夹和文件夹里面的文件
    public static void deleteDir(String path) {
        File dir = new File(path);
        if (dir == null || !dir.exists() || !dir.isDirectory())
            return;

        for (File file : dir.listFiles()) {
            if (file.isFile())
                file.delete(); // 删除所有文件
            else if (file.isDirectory())
                deleteDir(file.getPath()); // 递规的方式删除文件夹
        }
        dir.delete();// 删除目录本身
    }

}
