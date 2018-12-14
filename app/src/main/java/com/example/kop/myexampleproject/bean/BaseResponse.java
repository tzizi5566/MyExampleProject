package com.example.kop.myexampleproject.bean;

import java.io.Serializable;

/**
 * Created by zhangwentao on 2015/11/12.
 */
public class BaseResponse<T> implements Serializable {

    /**
     * resCode : 0
     * resMsg : success
     * results : {}
     * sign :
     * time : 1541061521584
     * empty : false
     */

    private String resCode;
    private String resMsg;
    private T results;
    private String sign;
    private long time;
    private boolean empty;

    public String getResCode() {
        return resCode;
    }

    public void setResCode(String resCode) {
        this.resCode = resCode;
    }

    public String getResMsg() {
        return resMsg;
    }

    public void setResMsg(String resMsg) {
        this.resMsg = resMsg;
    }

    public T getResults() {
        return results;
    }

    public void setResults(T results) {
        this.results = results;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public boolean isEmpty() {
        return empty;
    }

    public void setEmpty(boolean empty) {
        this.empty = empty;
    }

    @Override public String toString() {
        return "BaseResponse{" +
                "resCode='" + resCode + '\'' +
                ", resMsg='" + resMsg + '\'' +
                ", results=" + results +
                ", sign='" + sign + '\'' +
                ", time=" + time +
                ", empty=" + empty +
                '}';
    }
}
