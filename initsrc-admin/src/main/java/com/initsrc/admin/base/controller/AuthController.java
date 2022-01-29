package com.initsrc.admin.base.controller;

import com.initsrc.admin.base.entity.auth.dto.LoginUserDto;
import com.initsrc.common.base.LoginResultVo;
import com.initsrc.admin.base.entity.auth.vo.UserInfoVo;
import com.initsrc.admin.base.service.AuthService;
import com.initsrc.common.annotation.LogAnnotation;
import com.initsrc.common.annotation.LoginUser;
import com.initsrc.common.base.BaseEntity;
import com.initsrc.common.base.Result;
import com.initsrc.common.constant.AuthConstant;
import com.initsrc.common.enums.LogOperateTypeEnum;
import com.initsrc.common.plugin.redis.RedisImpl;
import com.initsrc.common.util.jwt.JwtUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 *  基础模块-登录模块
 * </p>
 *
 * @author INITSRC
 * @since 2020-07-28
 */
@RestController
@RequestMapping("/webApi/base")
@Api(tags = "基础模块-登录模块")
public class AuthController {
    @Resource
    private AuthService authService;
    @Resource
    private RedisImpl redisImpl;

    /**
     * 系统管理-登录接口
     *
     * @param dto
     * @return
     */
    @PostMapping("/login")
    @ApiOperation(value = "基础模块-登录接口", notes = "无需在head中输入token")
    @LogAnnotation(operationType = LogOperateTypeEnum.LOGIN, operateContent = "登录了管理后台")
    public Result<LoginResultVo> login(@Validated LoginUserDto dto, HttpServletRequest request) {
        return authService.login(dto,request);
    }

    /**
     * 基础模块-获取权限
     *
     * @param dto
     * @return
     */
    @PostMapping("/refreshAuth")
    @ApiOperation(value = "基础模块-获取权限", notes = "基础模块-获取权限")
    @LoginUser
    public Result<UserInfoVo> getResfUserInfo(BaseEntity dto, HttpServletRequest request) {
        return authService.getResfUserInfo(dto, request);
    }

    /**
     * 基础模块-退出登录接口
     *
     * @param
     * @return
     */
    @PostMapping("/outLogin")
    @ApiOperation(value = "基础模块-退出登录接口", notes = "无需在head中输入token")
    public Result outLogin(HttpServletRequest request) {
        String token = request.getHeader("token");
        if(!token.equals("INITSRC")){
            String account = JwtUtil.getClaim(token, AuthConstant.TOKEN_ACCOUNT);
            redisImpl.del(AuthConstant.REDIS_ACCOUNT_KEY + account);
        }
        return Result.success();
    }

}
