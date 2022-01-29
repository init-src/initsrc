package com.initsrc.admin.system.entity.dict.dto;

import com.initsrc.common.base.BaseEntity;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
* <p>
* 字典表-更新或编辑对象
* </p>
*
* @author 启源（INITSRC）
* @since  2021-05-20 15:56:31
*/

@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(value="SysDictSaveDto更新或编辑对象", description="更新或编辑对象")
public class SysDictSaveDto extends BaseEntity implements Serializable {

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
