package com.example.kop.myexampleproject.net;

/**
 * 功    能: //TODO
 * 创 建 人: KOP
 * 创建日期: 2018/6/17 21:41
 */
public class ApiException extends RuntimeException {

    private int errorCode;
    public String message;

    public ApiException(int code, String msg) {
        super(msg);
        this.errorCode = code;
        this.message = msg;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public String getMessage() {
        return message;
    }
}
