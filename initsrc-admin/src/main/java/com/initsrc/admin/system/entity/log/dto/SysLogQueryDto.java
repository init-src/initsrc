package com.initsrc.admin.system.entity.log.dto;

import com.initsrc.common.base.BaseSearchEntity;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 系统操作日志-列表查询对象
 * </p>
 *
 * @author 启源（INITSRC）
 * @since  2021-05-31 16:52:03
 */

@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(value="SysLogQueryDto列表查询对象", description="列表查询对象")
public class SysLogQueryDto extends BaseSearchEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "模块标题")
    private String title;

    @ApiModelProperty(value = "日志类型（1：登录 2：查询 3：新增 4：编辑 5：删除 6：导入 7：导出）")
    private String bizType;

    @ApiModelProperty(value = "设备类型 （0：管理端 1：PC端  2：安卓端 3：IOS端 4：H5端 5：小程序端）")
    private String platformType;

}
