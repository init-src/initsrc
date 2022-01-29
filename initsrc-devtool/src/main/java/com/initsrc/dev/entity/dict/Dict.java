package com.initsrc.dev.entity.dict;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
* <p>
    * 字典表-is_sys_dict
    * </p>
*
* @author 启源（INITSRC）
* @since  2021-05-20 15:56:31
*/

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("is_sys_dict")
@ApiModel(value="SysDict对象", description="")
public class Dict implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "字典主键")
    @TableId(value = "dict_id", type = IdType.ASSIGN_ID)
    private String dictId;

    @ApiModelProperty(value = "字典key")
    private String dictKey;

    @ApiModelProperty(value = "类型：（1：文本  2：数组 3：文件）")
    private String type;

    @ApiModelProperty(value = "字典值")
    private String value;

    @ApiModelProperty(value = "配置描述")
    private String des;

    @ApiModelProperty(value = "删除标识 （0：删除 1：正常）")
    @TableLogic
    private String dr;

    @ApiModelProperty(value = "幂等")
    @Version
    @TableField(fill = FieldFill.INSERT)
    private Integer version;

    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    @TableField(fill = FieldFill.UPDATE)
    private Date updateTime;

    @ApiModelProperty(value = "创建人")
    @TableField(fill = FieldFill.INSERT)
    private String createBy;

    @ApiModelProperty(value = "更新人")
    @TableField(fill = FieldFill.UPDATE)
    private String updateBy;

}
