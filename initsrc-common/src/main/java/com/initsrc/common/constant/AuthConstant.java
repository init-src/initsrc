package com.initsrc.common.constant;

/**
 * JAVA-授权常量
 *
 * @author 启源 （INITSRC）
 * @date 2020/8/30 11:45
 */
public class AuthConstant {

    /**
     * JWT加密KEY
     */
    public static final String ENCRYPT_JWT_KEY = "INITSRC1221";

    /**
     * JWT加密KEY
     */
    public static final String TOKEN_ACCOUNT = "account";

    /**
     * JWT-platform: 平台key
     */
    public final static String PLATFORM = "platform";

    /**
     * JWT-key 根据当前时间戳来存储。验证刷新和验证账户异地登录
     */
    public static final String IS_JWT_KEY = "timeKey";


    /**
     * 旧token有效自动过期时间 单位秒
     */
    public static final long REFRESH_TIME = 30;

    /**
     * Redis有效时间，单位秒 60*60=3600
     */
    public static final long REDIS_EXPIRE_TIME = 3600;

    /**
     * Token有效时间，进行更换，单位秒 5*60=300
     */
    public static final long TOKEN_EXPIRE_TIME = 300;

    /**
     * 登录信息rediskey
     */
    public final  static String REDIS_ACCOUNT_KEY = "initsrc:login:key:";

    /**
     * 允许token过度key
     */
    public final  static String PREFIX_REFRESH_TOKEN_TRANSITION  = "initsrc:pass:key:";
}
