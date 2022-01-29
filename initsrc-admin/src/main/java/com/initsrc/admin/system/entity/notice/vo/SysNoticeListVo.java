package com.initsrc.admin.system.entity.notice.vo;

import java.util.Date;
import java.io.Serializable;

import cn.afterturn.easypoi.excel.annotation.Excel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 通知公告-列表对象
 * </p>
 *
 * @author 启源（INITSRC）
 * @since  2021-06-07 15:25:08
 */

@Data
@Accessors(chain = true)
@ApiModel(value="SysNoticeListVo列表对象", description="列表对象")
public class SysNoticeListVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @Excel(name = "主键", orderNum = "1" , width = 20
    )
    private String noticeId;

    @ApiModelProperty(value = "公告标题")
    @Excel(name = "公告标题", orderNum = "2" , width = 20
    )
    private String title;

    @ApiModelProperty(value = "公告状态（1正常 0关闭）")
    @Excel(name = "公告状态（1正常 0关闭）", orderNum = "3" , width = 20
            , replace = {"启用_1","禁用_0"})
    private String status;

    @ApiModelProperty(value = "创建时间")
    @Excel(name = "创建时间", orderNum = "4" , width = 20  , format = "yyyy-MM-dd HH:mm:ss"
    )
    private Date createTime;

}
