package com.initsrc.admin.system.entity.user.vo;

import com.initsrc.common.base.NodeEntity;
import java.util.List;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
* <p>
    * 用户表-下拉选择对象
    * </p>
*
* @author 启源（INITSRC）
* @since  2021-05-21 15:25:48
*/

@Data
@Accessors(chain = true)
@ApiModel(value="SysUserSelectVo详情对象", description="详情对象")
public class SysUserSelectVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "dept_id字段关联查询列表")
    List<NodeEntity> deptIdList;

    @ApiModelProperty(value = "role_id字段关联查询列表")
    List<NodeEntity> roleIdList;

}
