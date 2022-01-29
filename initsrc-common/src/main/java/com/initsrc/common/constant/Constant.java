package com.initsrc.common.constant;

/**
 * JAVA-REDIS常量
 *
 * @author 启源 （INITSRC）
 * @date 2020/8/30 11:45
 */
public class Constant {
    /**
     * 请求头token标识
     */
    public final static String LOGIN_SIGN = "token";
    /**
     * 请求头sc标识
     */
    public final static String LOGIN_SC = "sc";
    /**
     * 当前api版本号
     */
    public static final String CURRENT_API_VERSION = "1.0";



    /**
     * 平台（0表示PC管理端，1表示APP管理端）
     */
    //PC管理端
    public final static String PLATFORM_WEB = "0";
    //PC前端
    public final static String PLATFORM_PC = "1";
    //安卓
    public final static String PLATFORM_ANDR = "2";
    //IOS
    public final static String PLATFORM_IOS = "3";
    //H5
    public final static String PLATFORM_H5 = "4";
    //小程序
    public final static String PLATFORM_WX = "5";



}
