package com.initsrc.admin.system.entity.user.dto;

import com.initsrc.common.base.BaseSearchEntity;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
* <p>
* 用户表-列表查询对象
* </p>
*
* @author 启源（INITSRC）
* @since  2021-05-21 15:25:48
*/

@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(value="SysUserQueryDto列表查询对象", description="列表查询对象")
public class SysUserQueryDto extends BaseSearchEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "所属部门")
    private String deptId;

    @ApiModelProperty(value = "昵称")
    private String nickName;

    @ApiModelProperty(value = "用户名")
    private String userName;

    @ApiModelProperty(value = "手机号")
    private String mobile;

}
