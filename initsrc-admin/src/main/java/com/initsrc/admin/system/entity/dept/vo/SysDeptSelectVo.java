package com.initsrc.admin.system.entity.dept.vo;

import com.initsrc.common.base.NodeEntity;
import java.util.List;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
* <p>
    * 部门表-下拉选择对象
    * </p>
*
* @author 启源（INITSRC）
* @since  2021-05-28 21:34:09
*/

@Data
@Accessors(chain = true)
@ApiModel(value="SysDeptSelectVo详情对象", description="详情对象")
public class SysDeptSelectVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "parent_id字段关联查询列表")
    List<NodeEntity> parentIdList;

}
