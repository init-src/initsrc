package com.initsrc.common.util.jwt;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

/**
 * Base64工具
 *
 * @author INITSRC
 * @date 2021/4/21 15:14
 */
public class Base64ConvertUtil {

    /**
     * 加密JDK1.8
     *
     * @param str
     * @return java.lang.String
     * @author Wang926454
     * @date 2018/8/21 15:28
     */
    public static String encode(String str) throws UnsupportedEncodingException {
        if (str == null) {
            return null;
        }
        byte[] encodeBytes = Base64.getEncoder().encode(str.getBytes("utf-8"));
        return new String(encodeBytes);
    }

    /**
     * 解密JDK1.8
     *
     * @param str
     * @return java.lang.String
     * @author Wang926454
     * @date 2018/8/21 15:28
     */
    public static String decode(String str) throws UnsupportedEncodingException {
        if (str == null) {
            return null;
        }
        byte[] decodeBytes = Base64.getDecoder().decode(str.getBytes("utf-8"));
        return new String(decodeBytes);
    }

}
