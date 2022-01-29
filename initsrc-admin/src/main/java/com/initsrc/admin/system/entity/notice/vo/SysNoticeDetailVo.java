package com.initsrc.admin.system.entity.notice.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
* <p>
* 通知公告-详情对象
* </p>
*
* @author 启源（INITSRC）
* @since  2021-06-02 14:23:15
*/

@Data
@Accessors(chain = true)
@ApiModel(value="SysNoticeDetailVo详情对象", description="详情对象")
public class SysNoticeDetailVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    private String noticeId;

    @ApiModelProperty(value = "公告标题")
    private String title;

    @ApiModelProperty(value = "公告内容")
    private String content;

    @ApiModelProperty(value = "公告状态（1正常 0关闭）")
    private String status;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

}
