package com.initsrc.common.util;

import org.apache.shiro.crypto.hash.SimpleHash;

/**
 * 密码加密类
 * 作者：INITSRC (启源)
 */
public class Md5Util {
    //此处我们使用 MD5 算法，“密码 + 盐（用户名 + 随机数）” 的方式生成散列值
    public static String md5Encryption(String username,String password,String salt){
        String algorithmName = "md5";
        String salt1 = username;
        String salt2 = salt;
        int hashIterations = 2;
        SimpleHash hash = new SimpleHash(algorithmName, password, salt1 + salt2, hashIterations);
        String encodedPassword = hash.toHex();
        return encodedPassword;
    }


}
