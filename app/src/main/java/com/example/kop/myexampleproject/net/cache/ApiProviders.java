package com.example.kop.myexampleproject.net.cache;

import com.example.kop.myexampleproject.bean.BaseResponse;
import com.example.kop.myexampleproject.bean.Token;
import io.reactivex.Observable;
import io.rx_cache2.DynamicKey;
import io.rx_cache2.EvictDynamicKey;

/**
 * 功    能: //TODO
 * 创 建 人: KOP
 * 创建日期: 2018/11/2 10:56
 */
public interface ApiProviders {

    /**
     * LifeCache设置缓存过期时间. 如果没有设置@LifeCache , 数据将被永久缓存除非你使用了 EvictProvider,EvictDynamicKey or EvictDynamicKeyGroup .
     *
     * @param userName 驱逐与一个特定的键使用EvictDynamicKey相关的数据。比如分页，排序或筛选要求
     * @param evictDynamicKey 可以明确地清理指定的数据 DynamicKey.
     */
    //@LifeCache(duration = 1, timeUnit = TimeUnit.MINUTES)
    Observable<BaseResponse<Token>> getToken(
            Observable<BaseResponse<Token>> token,
            DynamicKey userName,
            EvictDynamicKey evictDynamicKey);
}
