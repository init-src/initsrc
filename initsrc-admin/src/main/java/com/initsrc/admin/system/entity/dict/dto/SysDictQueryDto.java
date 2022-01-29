package com.initsrc.admin.system.entity.dict.dto;

import com.initsrc.common.base.BaseSearchEntity;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
* <p>
* 字典表-列表查询对象
* </p>
*
* @author 启源（INITSRC）
* @since  2021-05-20 15:56:31
*/

@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(value="SysDictQueryDto列表查询对象", description="列表查询对象")
public class SysDictQueryDto extends BaseSearchEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "字典key")
    private String dictKey;

}
