package com.initsrc.common.base;

import lombok.Data;

/**
 * 描述：验证请求是否安全
 * 作者：INITSRC
 */
@Data
public class BaseSc {

    /**
     * 登录时返回的协议串
     */
    private String ticket;

    /**
     * 接口版本
     */
    private String version;

    /**
     * 平台标识
     */
    private String plf;

}
