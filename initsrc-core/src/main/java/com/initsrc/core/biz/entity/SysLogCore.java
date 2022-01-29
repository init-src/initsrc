package com.initsrc.core.biz.entity;

import java.util.Date;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 系统操作日志-is_sys_log
 * </p>
 *
 * @author 启源（INITSRC）
 * @since  2021-05-31 16:52:03
 */

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("is_sys_log")
@ApiModel(value="SysLog对象", description="")
public class SysLogCore implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "log_id", type = IdType.ASSIGN_ID)
    private String logId;

    @ApiModelProperty(value = "用户ID")
    private String userId;

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

    @ApiModelProperty(value = "请求路径")
    private String requestUrl;

    @ApiModelProperty(value = "请求IP")
    private String requestIp;

    @ApiModelProperty(value = "请求地址")
    private String requestAdress;

    @ApiModelProperty(value = "请求参数")
    private String requestParam;

    @ApiModelProperty(value = "请求响应数据")
    private String requestResult;

    @ApiModelProperty(value = "请求状态 0：异常 1：正常")
    private String status;

    @ApiModelProperty(value = "错误提示")
    private String errorMsg;

    @ApiModelProperty(value = "浏览器类型")
    private String browser;

    @ApiModelProperty(value = "操作系统")
    private String os;

    @ApiModelProperty(value = "删除标识 （0：正常 1：删除）")
    @TableLogic
    private String dr;

    @ApiModelProperty(value = "幂等")
    @Version
    @TableField(fill = FieldFill.INSERT)
    private Integer version;

    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    @TableField(fill = FieldFill.UPDATE)
    private Date updateTime;

    @ApiModelProperty(value = "创建人")
    @TableField(fill = FieldFill.INSERT)
    private String createBy;

    @ApiModelProperty(value = "更新人")
    @TableField(fill = FieldFill.UPDATE)
    private String updateBy;

}
