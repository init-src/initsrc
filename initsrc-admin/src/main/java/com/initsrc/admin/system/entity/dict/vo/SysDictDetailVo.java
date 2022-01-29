package com.initsrc.admin.system.entity.dict.vo;

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
* 字典表-详情对象
* </p>
*
* @author 启源（INITSRC）
* @since  2021-05-20 15:56:31
*/

@Data
@Accessors(chain = true)
@ApiModel(value="SysDictDetailVo详情对象", description="详情对象")
public class SysDictDetailVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "字典主键")
    private String dictId;

    @ApiModelProperty(value = "字典key")
    private String dictKey;

    @ApiModelProperty(value = "类型：（1：文本  2：数组 3：文件）")
    private String type;

    @ApiModelProperty(value = "字典值")
    private String value;

    @ApiModelProperty(value = "配置描述")
    private String des;

}
