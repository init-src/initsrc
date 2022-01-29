package com.initsrc.admin.system.entity.dept.dto;

import com.initsrc.common.base.BaseSearchEntity;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
* <p>
* 部门表-列表查询对象
* </p>
*
* @author 启源（INITSRC）
* @since  2021-05-28 21:34:09
*/

@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(value="SysDeptQueryDto列表查询对象", description="列表查询对象")
public class SysDeptQueryDto extends BaseSearchEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "上级部门")
    private String parentId;

    @ApiModelProperty(value = "部门编号")
    private String code;

    @ApiModelProperty(value = "部门名称")
    private String name;

}
