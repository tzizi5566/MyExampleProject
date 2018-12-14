package com.example.kop.myexampleproject.net;

/**
 * 功    能: //TODO
 * 创 建 人: KOP
 * 创建日期: 2018/6/17 21:41
 */
public interface ApiErrorCode {

    /**
     * 所有接口 成功请求后返回的内容
     */
    int API_SUCCUSE = 0;

    /**
     * 重新登录
     */
    int NOT_LOGIN = 9999;

    /**
     * 系统异常
     */
    int SYSTEM_ERROR = 100002;

    /**
     * 第三方系统异常
     */
    int THIRD_SYSTEM_ERROR = 100003;

    /**
     * 验签失败
     */
    int SIGN_ERROR = 100004;

    /**
     * 入参为空值
     */
    int PARAM_NULL_ERROR = 100005;

    /**
     * 输入参数不符合规范
     */
    int PARAM_ERROR = 100006;

    /**
     * 短信发送当天超过上限
     */
    int SMS_NUM_ERROR = 100007;

    /**
     * 验证码错误
     */
    int VERIFICATION_CODE_ERROR = 100008;

    /**
     * 用户银行签约状态错误
     */
    int BANK_SIGN_ERROR = 100009;

    /**
     * 用户未签约
     */
    int USER_NO_SIGN_ERROR = 100010;

    /**
     * 未知错误
     */
    int UNKNOWN = 100;

    /**
     * 解析错误
     */
    int PARSE_ERROR = 101;

    /**
     * 无网络
     */
    int NOT_NETWORK = 102;

    /**
     * 网络错误
     */
    int HTTP_ERROR = 103;

    /**
     * 网络连接超时
     */
    int HTTP_TIMEOUT = 104;

    /**
     * 无网络
     */
    int NETWORK_ERROR = 105;
}
