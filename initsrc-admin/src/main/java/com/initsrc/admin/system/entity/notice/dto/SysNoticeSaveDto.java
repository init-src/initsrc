package com.initsrc.admin.system.entity.notice.dto;

import javax.validation.constraints.*;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.initsrc.common.base.BaseEntity;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 通知公告-更新或编辑对象
 * </p>
 *
 * @author 启源（INITSRC）
 * @since  2021-06-07 17:06:20
 */

@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(value="SysNoticeSaveDto更新或编辑对象", description="更新或编辑对象")
public class SysNoticeSaveDto extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    private String noticeId;

    @ApiModelProperty(value = "公告标题")
    @NotBlank(message = "公告标题不能为空")
    @Excel(name = "公告标题", orderNum = "2" , width = 20 )
    private String title;

    @ApiModelProperty(value = "公告内容")
    @NotBlank(message = "公告内容不能为空")
    @Excel(name = "公告内容", orderNum = "3" , width = 20 )
    private String content;

    @ApiModelProperty(value = "公告状态（1正常 0关闭）")
    @NotBlank(message = "公告状态（1正常 0关闭）不能为空")
    @Excel(name = "公告状态", orderNum = "4" , width = 20 , replace = {"启用_1","禁用_0"})
    private String status;

}
