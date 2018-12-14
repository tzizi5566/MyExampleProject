package com.example.kop.myexampleproject.net;

import androidx.annotation.NonNull;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import java.io.IOException;
import okhttp3.ResponseBody;
import org.json.JSONException;
import org.json.JSONObject;
import retrofit2.Converter;

/**
 * 功    能: 统一处理API错误码
 * 创 建 人: KOP
 * 创建日期: 2018/6/17 21:40
 */
public class MyGsonResponseBodyConverter<T> implements Converter<ResponseBody, T> {

    private final Gson mGson;
    private final TypeAdapter<T> adapter;

    MyGsonResponseBodyConverter(Gson gson, TypeAdapter<T> adapter) {
        mGson = gson;
        this.adapter = adapter;
    }

    @Override
    public T convert(@NonNull ResponseBody value) throws IOException {
        String response = value.string();
        try {
            JSONObject json = new JSONObject(response);
            int resCode = json.getInt("resCode");
            if (resCode != 0) {
                String resMsg = json.getString("resMsg");
                throw new ApiException(resCode, resMsg);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        } finally {
            value.close();
        }
        return adapter.fromJson(response);
    }
}
