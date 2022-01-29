package com.initsrc.admin.base.service;

import com.initsrc.admin.base.entity.auth.dto.LoginUserDto;
import com.initsrc.common.base.LoginResultVo;
import com.initsrc.admin.base.entity.auth.vo.UserInfoVo;
import com.initsrc.common.base.BaseEntity;
import com.initsrc.common.base.Result;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 授权登录-服务类
 * </p>
 *
 * @author 启源（INITSRC）
 * @since  2021-05-21 15:25:48
 */
public interface AuthService {
    //用户登录接口
    Result<LoginResultVo> login(LoginUserDto dto, HttpServletRequest request);
    //用户刷新获取配置信息
    Result<UserInfoVo> getResfUserInfo(BaseEntity dto, HttpServletRequest request);
}
