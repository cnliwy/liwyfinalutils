package com.liwy.anything.liwycache;

/**
 * 定义通用方法抽象类
 * Created by liwy on 16/7/10.
 */
public abstract class ALiwyCache {
    public abstract void deleteForKey(String key);//删除单个缓存
    public abstract void deleteAll();//删除所有缓存
}
