package com.initsrc.admin.system.entity.dict.vo;

import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
* <p>
* 字典表-列表对象
* </p>
*
* @author 启源（INITSRC）
* @since  2021-05-20 15:56:31
*/

@Data
@Accessors(chain = true)
@ApiModel(value="SysDictListVo列表对象", description="列表对象")
public class SysDictListVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "字典主键")
    private String dictId;

    @ApiModelProperty(value = "字典key")
    private String dictKey;

    @ApiModelProperty(value = "类型：（1：文本  2：数组 3：文件）")
    private String type;

    @ApiModelProperty(value = "配置描述")
    private String des;

}
