package com.initsrc.common.exception;


import com.initsrc.common.enums.ResultEnum;
import lombok.Data;

/**
 * 描述：业务异常
 * 作者：INITSRC
 */
@Data
public class BusinessException extends RuntimeException {

    /*异常处理编码*/
    private Integer code;
    /*异常处理信息*/
    private String msg;
    /*异常处理描述*/
    private String desc;

    public BusinessException(Integer code, String msg, String desc) {
        this.code = code;
        this.msg = msg;
        this.desc = desc;
    }

    public BusinessException(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public BusinessException(String msg) {
        this.code = ResultEnum.CODE_1.getCode();
        this.msg = msg;
    }
}
