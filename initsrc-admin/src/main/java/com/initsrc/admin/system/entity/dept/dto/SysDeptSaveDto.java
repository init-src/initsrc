package com.initsrc.admin.system.entity.dept.dto;

import javax.validation.constraints.*;
import com.initsrc.common.base.BaseEntity;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
* <p>
* 部门表-更新或编辑对象
* </p>
*
* @author 启源（INITSRC）
* @since  2021-05-28 21:34:09
*/

@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(value="SysDeptSaveDto更新或编辑对象", description="更新或编辑对象")
public class SysDeptSaveDto extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "序号")
    private String deptId;

    @ApiModelProperty(value = "上级部门")
    @NotBlank(message = "上级部门不能为空")
    private String parentId;

    @ApiModelProperty(value = "部门编号")
    @NotBlank(message = "部门编号不能为空")
    private String code;

    @ApiModelProperty(value = "部门名称")
    @NotBlank(message = "部门名称不能为空")
    private String name;

    @ApiModelProperty(value = "负责人")
    @NotBlank(message = "负责人不能为空")
    private String linkMan;

    @ApiModelProperty(value = "联系电话")
    @NotBlank(message = "联系电话不能为空")
    private String linkMobile;

    @ApiModelProperty(value = "排序")
    private Integer sort;

    @ApiModelProperty(value = "状态（0：正常 1：停用）")
    @NotBlank(message = "状态（0：正常 1：停用）不能为空")
    private String status;

}
