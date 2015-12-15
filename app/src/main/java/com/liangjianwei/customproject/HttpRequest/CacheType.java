package com.liangjianwei.customproject.HttpRequest;

/**
 * 缓存类型  cache  or network
 */
public enum CacheType {
    /**
     * 仅使用网络请求数据
     */
    ONLY_NETWORK,
    /**
     * 仅使用缓存数据
     */
    ONLY_CACHED,
    /**
     * 先找本地是否有缓存数据，有则加载，无则加载网络数据
     */
    CACHED_ELSE_NETWORK,

    /**
     * 先加载网络数据，如果没有数据或者返回错误则加载缓存数据
     */
    NETWORK_ELSE_CACHED
}
