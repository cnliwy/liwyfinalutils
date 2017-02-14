package com.liwy.anything.utils;

import java.io.Closeable;
import java.io.IOException;

/**
 * Created by liwy on 16/7/10.
 */
public class CloseUtil {
    /**
    * 关闭Closealbe对象
    * @author liwy
    * @created at 16/7/10 上午9:41
    */
    public static void close(Closeable closeable){
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
