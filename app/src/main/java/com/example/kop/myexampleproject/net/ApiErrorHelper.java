package com.example.kop.myexampleproject.net;

import android.net.ParseException;
import com.google.gson.JsonParseException;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import org.json.JSONException;
import retrofit2.HttpException;

/**
 * 功    能: //TODO
 * 创 建 人: KOP
 * 创建日期: 2018/6/17 21:42
 */
public class ApiErrorHelper {

    public static ApiException handleCommonError(Throwable e) {
        ApiException ex;
        if (e instanceof HttpException) {
            HttpException httpException = (HttpException) e;
            ex = new ApiException(ApiErrorCode.HTTP_ERROR, httpException.message());
            ex.message = "网络错误";
        } else if (e instanceof ConnectException) {
            ex = new ApiException(ApiErrorCode.NOT_NETWORK, e.getMessage());
            ex.message = "网络连接失败";
        } else if (e instanceof UnknownHostException) {
            ex = new ApiException(ApiErrorCode.NETWORK_ERROR, e.getMessage());
            ex.message = "网络链接失败";
        } else if (e instanceof SocketTimeoutException) {
            ex = new ApiException(ApiErrorCode.HTTP_TIMEOUT, e.getMessage());
            ex.message = "网络连接超时";
        } else if (e instanceof JsonParseException || e instanceof JSONException || e instanceof ParseException) {
            ex = new ApiException(ApiErrorCode.PARSE_ERROR, e.getMessage());
            ex.message = "解析错误";
        } else if (e instanceof ApiException) {
            ex = (ApiException) e;
        } else {
            ex = new ApiException(ApiErrorCode.UNKNOWN, e.getMessage());
            ex.message = "未知错误";
        }
        return ex;
    }
}
