package com.initsrc.admin.system.entity.role.vo;

import com.initsrc.admin.system.entity.perm.vo.SysPermListVo;
import com.initsrc.common.base.LoginPermVo;
import com.initsrc.common.base.NodeEntity;
import java.util.List;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 * 角色表-下拉选择对象
 * </p>
 *
 * @author 启源（INITSRC）
 * @since  2021-05-28 14:57:28
 */

@Data
@Accessors(chain = true)
@ApiModel(value="SysRoleSelectVo详情对象", description="详情对象")
public class SysRoleSelectVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "power_depts字段关联查询列表")
    List<NodeEntity> powerDeptsList;

    @ApiModelProperty(value = "权限列表")
    List<LoginPermVo> permList;

}
