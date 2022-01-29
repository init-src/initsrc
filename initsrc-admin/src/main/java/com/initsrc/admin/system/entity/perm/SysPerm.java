package com.initsrc.admin.system.entity.perm;

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
 * 权限表-is_sys_perm
 * </p>
 *
 * @author 启源（INITSRC）
 * @since  2021-05-26 17:03:56
 */

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("is_sys_perm")
@ApiModel(value="SysPerm对象", description="")
public class SysPerm implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "序号")
    @TableId(value = "perm_id", type = IdType.ASSIGN_ID)
    private String permId;

    @ApiModelProperty(value = "所属菜单")
    private String parentId;

    @ApiModelProperty(value = "菜单名称")
    private String name;

    @ApiModelProperty(value = "菜单路径")
    private String path;

    @ApiModelProperty(value = "菜单类型")
    private String resource;

    @ApiModelProperty(value = "路由组件")
    private String component;

    @ApiModelProperty(value = "shiro权限")
    private String perm;

    @ApiModelProperty(value = "菜单图标")
    private String icon;

    @ApiModelProperty(value = "按钮颜色")
    private String color;

    @ApiModelProperty(value = "是否缓存")
    private String isCache;

    @ApiModelProperty(value = "排序")
    private Integer sort;

    @ApiModelProperty(value = "链接类型")
    private String linkType;

    @ApiModelProperty(value = "删除标识 （0：删除 1：正常）")
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
