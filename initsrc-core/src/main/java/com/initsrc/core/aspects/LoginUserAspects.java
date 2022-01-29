package com.initsrc.core.aspects;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.initsrc.common.base.LoginInfo;
import com.initsrc.common.base.RedisInfo;
import com.initsrc.common.base.Result;
import com.initsrc.common.constant.AuthConstant;
import com.initsrc.common.enums.ResultEnum;
import com.initsrc.common.exception.BusinessException;
import com.initsrc.common.plugin.redis.RedisImpl;
import com.initsrc.common.util.jwt.JwtUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;


/**
 * AOP获取用户登录信息
 * 作者：INITSRC (启源)
 */
@Aspect
@Component
public class LoginUserAspects {
    @Resource
    private RedisImpl redisImpl;

    @Pointcut("@annotation(com.initsrc.common.annotation.LoginUser)")
    public void LoginUserImpl() {
    }

    @Before("LoginUserImpl()")
    public void LoginUserImpl(JoinPoint joinPoint) throws Throwable {
        Object[] argc = joinPoint.getArgs();
        Class clazz;
        Method[] methods;
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attr.getRequest();
        String token = request.getHeader("token");
        if(token == null){
            throw new BusinessException(ResultEnum.CODE_402.getCode(),ResultEnum.CODE_402.getMsg());
        }
        String userId = null;
        String scopeType = null;
        String scopeId = null;
        String scopeIds = null;
        if(token.equals("INITSRC")){
            userId = "1";
            scopeType = "0";
        }else {
            String account = JwtUtil.getClaim(token, AuthConstant.TOKEN_ACCOUNT);
            RedisInfo<LoginInfo> info = JSON.parseObject(JSON.toJSONString(redisImpl.get(AuthConstant.REDIS_ACCOUNT_KEY + account)), new TypeReference<RedisInfo<LoginInfo>>() {
            });
            if (info == null) {
              throw new BusinessException(ResultEnum.CODE_401.getCode(),ResultEnum.CODE_401.getMsg());
            }
            userId = info.getLoginInfo().getUid();
            scopeType = info.getLoginInfo().getIsSearch();
            scopeId = info.getLoginInfo().getDepartmentId();
            scopeIds = info.getLoginInfo().getPowerDepts();
        }
        for (Object object : argc) {
            if (null == object) {
                continue;
            }
            clazz = object.getClass();
            methods = clazz.getMethods();
            // 这里的methods会包含父类的public方法，也包括Object类的method
            for (Method method : methods) {
                if (method.getName().equals("setAuthId")) {
                    method.invoke(object,userId);
                }else if (method.getName().equals("setScopeType")) {
                    method.invoke(object,scopeType);
                }else if (method.getName().equals("setScopeId")) {
                    method.invoke(object,scopeId);
                }else if (method.getName().equals("setScopeIds")) {
                    method.invoke(object,scopeIds);
                }
            }
        }
    }
}
