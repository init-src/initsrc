package com.initsrc.dev.entity.table.dto;

import com.initsrc.common.base.BaseSearchEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 数据库表结构-列表查询对象
 * </p>
 *
 * @author INITSRC
 * @since  2021-01-26 17:05:53
 */

@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(value="DevTableQueryDto列表查询对象", description="列表查询对象")
public class DevTableQueryDto extends BaseSearchEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "数据库表名称")
    private String tableName;

    @ApiModelProperty(value = "数据库表描述")
    private String tableComment;
}
