package com.example.kop.myexampleproject.bean;

/**
 * 功    能: //TODO
 * 创 建 人: KOP
 * 创建日期: 2018/11/1 16:44
 */
public class Token {

    /**
     * token : 1b3d0976-e346-43eb-80c6-1b6774130c75
     */

    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override public String toString() {
        return "Token{" +
                "token='" + token + '\'' +
                '}';
    }
}
