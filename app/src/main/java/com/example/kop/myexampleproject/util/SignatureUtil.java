package com.example.kop.myexampleproject.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Http请求数据的签名工具类
 * Created by yonguo on 2015-07-20.
 */
public class SignatureUtil {

    private static final String TAG = "SignatureUtil";
    private static final char[] HEX_CHAR = {
            '0', '1', '2', '3', '4', '5',
            '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'
    };

    /**
     * 获取Http请求的签名串。签名规则：
     * 1）所有值非空的请求参数参与签名；
     * 2）所有非空参数进行排序，先以参数名排序，如果参数名相同则以参数值排序；
     * 3）所有非空参数按照先后顺序组合成下面形式的字符串：key1=value1&key2=value2&key3=value3....
     * 4）上面字符串基础上拼接签名密钥：key1=value1&key2=value2&key3=value3......&key=XXXXXXXXX
     * 5）最后对上面拼接好的字符串使用MD5签名后转换为16进制形式字符串；
     *
     * @param params Http请求参数
     * @param key 签名密钥
     * @return 签名串
     */
    public static String sign2Request(Map<String, String> params, String key) {
        Map<String, String> orderedParams = new TreeMap<>(params);

        List<Parameter> list = new ArrayList<>();
        if (params != null) {
            for (Map.Entry<String, String> entry : orderedParams.entrySet()) {
                String value = entry.getValue();
                if (value == null || value.length() == 0) {
                    continue;
                }
                Parameter param = new Parameter();
                param.setName(entry.getKey());
                param.setValue(value);
                list.add(param);
            }
        }

        //排序
        Collections.sort(list);

        StringBuilder paramsStr = new StringBuilder();
        for (Parameter param : list) {
            paramsStr.append(param.getName()).append("=").append(param.getValue()).append("&");
        }
        paramsStr.append("key=").append(key);

        return hash(paramsStr.toString());
    }

    /**
     * 先MD5加密在转为16进制
     */
    private static String hash(String data) {
        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            digest.update(data.getBytes());
            return bytesToHex(digest.digest());
        } catch (NoSuchAlgorithmException ignored) {

        }
        return null;
    }

    /**
     * byte[] to hex string
     */
    private static String bytesToHex(byte[] bytes) {
        char[] buf = new char[bytes.length * 2];
        int index = 0;
        for (byte b : bytes) { // 利用位运算进行转换，可以看作方法一的变种
            buf[index++] = HEX_CHAR[b >>> 4 & 0xf];
            buf[index++] = HEX_CHAR[b & 0xf];
        }
        return new String(buf);
    }
}
