package com.initsrc.admin.system.entity.dept.vo;

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
* 部门表-详情对象
* </p>
*
* @author 启源（INITSRC）
* @since  2021-05-28 21:34:09
*/

@Data
@Accessors(chain = true)
@ApiModel(value="SysDeptDetailVo详情对象", description="详情对象")
public class SysDeptDetailVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "序号")
    private String deptId;

    @ApiModelProperty(value = "上级部门")
    private String parentId;

    @ApiModelProperty(value = "上级部门")
    private String fdeptName;

    @ApiModelProperty(value = "部门编号")
    private String code;

    @ApiModelProperty(value = "部门名称")
    private String name;

    @ApiModelProperty(value = "负责人")
    private String linkMan;

    @ApiModelProperty(value = "联系电话")
    private String linkMobile;

    @ApiModelProperty(value = "排序")
    private Integer sort;

    @ApiModelProperty(value = "状态（0：正常 1：停用）")
    private String status;

}
