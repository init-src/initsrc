package com.initsrc.dev.entity.table.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.sql.Date;

;

/**
 * <p>
 * 数据库表结构-列表对象
 * </p>
 *
 * @author INITSRC
 * @since  2021-01-26 17:09:36
 */

@Data
@Accessors(chain = true)
@ApiModel(value="GenTableListVo列表对象", description="列表对象")
public class DevTableListVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "表主键")
    private String tableId;

    @ApiModelProperty(value = "数据库表名称")
    private String tableName;

    @ApiModelProperty(value = "数据库表描述")
    private String tableComment;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

}
