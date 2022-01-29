package com.initsrc.admin.base.entity.auth.vo;

import com.initsrc.common.base.LoginPermVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
@ApiModel(value = "基础模块-返回权限信息VO", description = "返回权限信息")
public class UserInfoVo {
    @ApiModelProperty(value = "用户信息", example = "")
    private AuthInfoVo authInfoVo;
    @ApiModelProperty(value = "门票", example = "xxx")
    private Long ticket;
    @ApiModelProperty(value = "用户菜单权限", example = "xxx")
    private List<LoginPermVo> permVoList;
    @ApiModelProperty(value = "字典列表", example = "xxx")
    private Map<String, Object> dictList;
}
