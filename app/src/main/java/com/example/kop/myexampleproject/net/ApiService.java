package com.example.kop.myexampleproject.net;

import com.example.kop.myexampleproject.bean.BaseResponse;
import com.example.kop.myexampleproject.bean.Token;
import com.example.kop.myexampleproject.bean.Version;
import io.reactivex.Observable;
import java.util.Map;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * 接口
 * Created by KOP on 2016/5/8.
 */
public interface ApiService {

    @FormUrlEncoded
    @POST("common/getToken")
    Observable<BaseResponse<Token>> getToken(@FieldMap Map<String,String> map);

    @FormUrlEncoded
    @POST("account/getVersion")
    Observable<BaseResponse<Version>> getVersion(@FieldMap Map<String, String> map);
}
