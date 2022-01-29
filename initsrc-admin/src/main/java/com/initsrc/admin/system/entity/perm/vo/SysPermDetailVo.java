package com.initsrc.admin.system.entity.perm.vo;

import com.initsrc.common.base.NodeEntity;
import java.util.List;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 权限表-详情对象
 * </p>
 *
 * @author 启源（INITSRC）
 * @since  2021-05-26 17:03:56
 */

@Data
@Accessors(chain = true)
@ApiModel(value="SysPermDetailVo详情对象", description="详情对象")
public class SysPermDetailVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "序号")
    private String permId;

    @ApiModelProperty(value = "所属菜单")
    private String parentId;

    @ApiModelProperty(value = "所属菜单")
    private String permName;

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

}
