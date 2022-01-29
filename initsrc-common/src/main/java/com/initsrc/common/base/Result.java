package com.initsrc.common.base;

import com.initsrc.common.enums.ResultEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * JAVA-请求结果
 *
 * @author 启源 （INITSRC）
 * @date 2020/8/30 11:45
 */
@Data
@ApiModel(value = "Result", description = "请求结果")
public class Result<T> implements Serializable {
    @ApiModelProperty(value = "返回状态码（0=请求成功，1=请求失败，-1=系统异常）", name = "", example = "0")
    private int code;
    @ApiModelProperty(value = "返回状态码消息", name = "", example = "请求成功")
    private String msg;
    @ApiModelProperty(value = "返回数据", name = "", example = "")
    private T data;
    @ApiModelProperty(value = "返回时间", name = "", example = "yyyy-MM-dd HH:mm:ss")
    private String time;

    private Result(int code) {
        this.code = code;
    }

    //带数据返回成功
    public static <T> Result<T> success(T data) {
        Result result = new Result(ResultEnum.CODE_0.getCode());
        if (data != null){
            result.data = data;
        }
        result.msg = ResultEnum.CODE_0.getMsg();
        result.time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        return result;
    }


    //不带数据返回成功
    public static <T> Result<T> success() {
        Result result = new Result(ResultEnum.CODE_0.getCode());
        result.msg = ResultEnum.CODE_0.getMsg();
        result.time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        return result;
    }

    //不带数据返回成功
    public static <T> Result<T> success(String msg) {
        Result result = new Result(ResultEnum.CODE_0.getCode());
        result.msg = msg;
        result.time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        return result;
    }

    //带数据和消息返回成功
    public static <T> Result<T> success(T data,String msg) {
        Result result = new Result(ResultEnum.CODE_0.getCode());
        result.data = data;
        result.msg = msg;
        result.time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        return result;
    }

    //返回失败
    public static <T> Result<T> fail() {
        Result result = new Result(ResultEnum.CODE_1.getCode());
        result.msg = ResultEnum.CODE_1.getMsg();
        result.time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        return result;
    }

    //返回失败
    public static <T> Result<T> fail(String msg) {
        Result result = new Result(ResultEnum.CODE_1.getCode());
        result.msg = msg;
        result.time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        return result;
    }


    //返回失败
    public static <T> Result<T> fail(int code,String msg) {
        Result result = new Result(code);
        result.msg = msg;
        result.time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        return result;
    }

    //错误的请求参数
    public static <T> Result<T> errorParam() {
        Result result = new Result(ResultEnum.CODE_400.getCode());
        result.msg = ResultEnum.CODE_400.getMsg();
        result.time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        return result;
    }

    //自定义返回
    public static <T> Result<T> ret(T data, String msg, int code) {
        Result result = new Result(code);
        result.data = data;
        result.msg = msg;
        result.time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        return result;
    }
}
