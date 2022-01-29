package com.initsrc.admin.system.entity.log.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 系统操作日志-列表对象
 * </p>
 *
 * @author 启源（INITSRC）
 * @since  2021-05-31 16:52:03
 */

@Data
@Accessors(chain = true)
@ApiModel(value="SysLogListVo列表对象", description="列表对象")
public class SysLogListVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    private String logId;

    @ApiModelProperty(value = "模块标题")
    private String title;

    @ApiModelProperty(value = "日志类型（1：登录 2：查询 3：新增 4：编辑 5：删除 6：导入 7：导出）")
    private String bizType;

    @ApiModelProperty(value = "访问方法名")
    private String method;

    @ApiModelProperty(value = "请求方式")
    private String requestType;

    @ApiModelProperty(value = "设备类型 （0：管理端 1：PC端  2：安卓端 3：IOS端 4：H5端 5：小程序端）")
    private String platformType;

    @ApiModelProperty(value = "请求用户名")
    private String requestName;

    @ApiModelProperty(value = "请求IP")
    private String requestIp;

    @ApiModelProperty(value = "请求URL")
    private String requestUrl;

    @ApiModelProperty(value = "请求状态 0：异常 1：正常")
    private String status;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

}
