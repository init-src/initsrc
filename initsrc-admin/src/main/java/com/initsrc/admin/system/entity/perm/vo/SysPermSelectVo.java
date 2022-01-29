package com.initsrc.admin.system.entity.perm.vo;

import com.initsrc.common.base.NodeEntity;
import java.util.List;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 * 权限表-下拉选择对象
 * </p>
 *
 * @author 启源（INITSRC）
 * @since  2021-05-26 17:03:56
 */

@Data
@Accessors(chain = true)
@ApiModel(value="SysPermSelectVo详情对象", description="详情对象")
public class SysPermSelectVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "parent_id字段关联查询列表")
    List<NodeEntity> parentIdList;

}
