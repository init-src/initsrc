package com.initsrc.common.base;

import com.initsrc.common.util.tree.TreeEntityImpl;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel(value="基础模块-返回权限列表VO", description="返回权限列表")
public class LoginPermVo implements TreeEntityImpl<LoginPermVo> {
    @ApiModelProperty(value = "菜单权限主键")
    private String id;

    @ApiModelProperty(value = "所属菜单")
    private String parentId;

    @ApiModelProperty(value = "菜单名称")
    private String name;

    @ApiModelProperty(value = "菜单路径")
    private String path;

    @ApiModelProperty(value = "菜单类型 （0：菜单 1：按钮 2：表格按钮）")
    private Integer resource;

    @ApiModelProperty(value = "路由组件")
    private String component;

    @ApiModelProperty(value = "shiro权限")
    private String perm;

    @ApiModelProperty(value = "菜单图标")
    private String icon;

    @ApiModelProperty(value = "按钮颜色")
    private String color;

    @ApiModelProperty(value = "排序")
    private Integer sort;

    @ApiModelProperty(value = "链接类型 （0：正常 1：满屏 2：外链）")
    private Integer linkType;

    @ApiModelProperty(value = "是否缓存 0：无 1：是")
    private Integer isCache;

    @ApiModelProperty(value = "菜单子类", example = "")
    public List<LoginPermVo> children;
}
