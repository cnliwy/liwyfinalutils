package com.liwy.anything.liwycache;

/**
 * 定义缓存实现接口,且通过泛型灵活存储对象
 * Created by liwy on 16/7/10.
 */
public interface ILiwyCache<T> {
    public T getValueForKey(String key);
    public void setValueToCache(String key,T obj);
}
