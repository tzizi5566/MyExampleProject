package com.example.kop.myexampleproject.net;

import com.example.kop.myexampleproject.base.UrlConstant;
import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * 功    能: Retrofit初始化
 * 创 建 人: KOP
 * 创建日期: 2018/6/17 21:37
 */
public class RetrofitFactory {

    private static RetrofitFactory INSTANCE;
    private Retrofit mRetrofit;

    private RetrofitFactory() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.HEADERS);

        OkHttpClient.Builder builder = new OkHttpClient().newBuilder();
        builder.readTimeout(UrlConstant.DEFAULT_TIMEOUT, TimeUnit.SECONDS);
        builder.writeTimeout(UrlConstant.DEFAULT_TIMEOUT, TimeUnit.SECONDS);
        builder.connectTimeout(UrlConstant.DEFAULT_TIMEOUT, TimeUnit.SECONDS);
        builder.addInterceptor(logging);

        OkHttpClient client = builder.build();

        mRetrofit = new Retrofit.Builder()
                .baseUrl(UrlConstant.BASE_URL)
                .client(client)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(MyGsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    public static RetrofitFactory getInstance() {
        if (INSTANCE != null) return INSTANCE;
        synchronized (RetrofitFactory.class) {
            if (INSTANCE != null) return INSTANCE;
            INSTANCE = new RetrofitFactory();
        }
        return INSTANCE;
    }

    public ApiService createService() {
        return mRetrofit.create(ApiService.class);
    }
}
