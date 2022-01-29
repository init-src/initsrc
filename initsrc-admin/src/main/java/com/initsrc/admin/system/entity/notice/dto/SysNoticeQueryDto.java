package com.initsrc.admin.system.entity.notice.dto;

import java.util.Date;
import com.initsrc.common.base.BaseSearchEntity;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 通知公告-列表查询对象
 * </p>
 *
 * @author 启源（INITSRC）
 * @since  2021-06-07 15:25:08
 */

@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(value="SysNoticeQueryDto列表查询对象", description="列表查询对象")
public class SysNoticeQueryDto extends BaseSearchEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "公告标题")
    private String title;

    @ApiModelProperty(value = "创建时间开始时间")
    private Date beginCreateTime;

    @ApiModelProperty(value = "创建时间结束时间")
    private Date endCreateTime;


}
