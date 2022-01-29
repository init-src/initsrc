package com.initsrc.common.enums;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 描述：请求结果类型
 * 作者：INITSRC
 */
@ApiModel(value = "ResultEnum", description = "请求结果代码")
public enum ResultEnum {
    //通用编码
    @ApiModelProperty(value = "请求成功", example = "0")
    CODE_0(0, "请求成功"),

    @ApiModelProperty(value = "请求失败", example = "1")
    CODE_1(1, "请求失败"),

    //4xx-前端与权限错误编码
    @ApiModelProperty(value = "错误的请求参数", example = "400")
    CODE_400(400,"错误的请求参数"),

    @ApiModelProperty(value = "凭证过期，请重新登录", example = "401")
    CODE_401(401,"凭证过期，请重新登录"),

    @ApiModelProperty(value = "非法操作", example = "402")
    CODE_402(402,"非法操作"),

    @ApiModelProperty(value = "无权限访问", example = "403")
    CODE_403(403,"无权限访问"),

    @ApiModelProperty(value = "参数不能为空", example = "404")
    CODE_404(404, "参数不能为空"),

    @ApiModelProperty(value = "接口版本错误", example = "405")
    CODE_405(405, "非法操作，接口版本错误"),

    @ApiModelProperty(value = "非法操作，请求IP不符合实际IP", example = "406")
    CODE_406(406, "非法操作，请求IP不符合实际IP"),

    @ApiModelProperty(value = "您的账号在其他设备登录，请重新登录", example = "407")
    CODE_407(407, "您的账号在其他设备登录，请重新登录"),

    @ApiModelProperty(value = "解析SC失败，非法参数", example = "407")
    CODE_408(407, "解析SC失败，非法参数"),

    //5xx-服务器端发生错误
    @ApiModelProperty(value = "系统错误", example = "500")
    CODE_500(500, "系统错误"),

    @ApiModelProperty(value = "系统工具错误", example = "501")
    CODE_501(501, "系统工具错误"),

    //1xxx-通用业务类型

    CODE_10000(10000, "枚举结束");

    private Integer code;
    private String msg;

    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
