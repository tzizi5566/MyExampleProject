package com.example.kop.myexampleproject.net.cache;

import com.example.kop.myexampleproject.base.MyApplication;
import io.rx_cache2.internal.RxCache;
import io.victoralbertos.jolyglot.GsonSpeaker;

/**
 * 功    能: //TODO
 * 创 建 人: KOP
 * 创建日期: 2018/11/2 11:00
 */
public class CacheProviders {

    private static ApiProviders sApiProviders;

    public synchronized static ApiProviders getApiCache() {
        if (sApiProviders == null) {
            sApiProviders = new RxCache.Builder()
                    .persistence(MyApplication.getApplication().getCacheDir(),
                            new GsonSpeaker())//缓存文件的配置、数据的解析配置
                    .using(ApiProviders.class);//这些配置对应的缓存接口
        }
        return sApiProviders;
    }
}
