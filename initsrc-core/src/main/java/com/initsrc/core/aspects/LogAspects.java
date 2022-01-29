package com.initsrc.core.aspects;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.google.common.collect.Maps;
import com.initsrc.common.annotation.LogAnnotation;
import com.initsrc.common.base.LoginInfo;
import com.initsrc.common.base.LoginResultVo;
import com.initsrc.common.base.RedisInfo;
import com.initsrc.common.constant.AuthConstant;
import com.initsrc.common.constant.Constant;
import com.initsrc.common.enums.LogOperateTypeEnum;
import com.initsrc.common.plugin.address.IpToAddressUtil;
import com.initsrc.common.plugin.redis.RedisImpl;
import com.initsrc.common.util.IpUtil;
import com.initsrc.common.util.ServletUtils;
import com.initsrc.common.util.jwt.JwtUtil;
import com.initsrc.core.biz.entity.SysLogCore;
import com.initsrc.core.biz.service.SysLogService;
import eu.bitwalker.useragentutils.UserAgent;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.cglib.beans.BeanMap;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * 日志AOP实现
 * 作者：INITSRC (启源)
 */
@Aspect
@Component
public class LogAspects {
    @Resource
    private SysLogService sysLogService;
    @Resource
    private RedisImpl redis;

    //只要使用了该注解,就会进入切面
    @Pointcut("@annotation(com.initsrc.common.annotation.LogAnnotation)")
    public void operationLog() {
    }

    /**
     * 方法返回之后调用
     *
     * @param joinPoint
     * @param returnValue 方法返回值
     */
    @AfterReturning(value = "operationLog()", returning = "returnValue")
    public void doAfter(JoinPoint joinPoint, Object returnValue) {
        getLogVo(joinPoint, returnValue);
    }

    private void getLogVo(JoinPoint joinPoint, Object returnValue) {
        //登录的token中去拿当前登录的用户Id
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attr.getRequest();
        RedisInfo<LoginInfo> loginInfo = null;
        //获取token值
        String token = request.getHeader("token");
        if (token != null) {
            if (token.equals("INITSRC")) {
                loginInfo = new RedisInfo<>();
                LoginInfo l = new LoginInfo();
                loginInfo.setPlf(Constant.PLATFORM_WEB);
                l.setUid("1");
                l.setNickName("INITSRC");
                loginInfo.setLoginInfo(l);
            } else {
                String account = JwtUtil.getClaim(token, AuthConstant.TOKEN_ACCOUNT);
                loginInfo = JSON.parseObject(JSON.toJSONString(redis.get(AuthConstant.REDIS_ACCOUNT_KEY + account)), new TypeReference<RedisInfo<LoginInfo>>() {
                });
            }
        }
        SysLogCore logVo = new SysLogCore();
        //获取类名称
        String targetName = joinPoint.getTarget().getClass().getName();
        Class targetClass = null;
        LogAnnotation logAnnotation = null;
        try {
            //反射
            targetClass = Class.forName(targetName);
            //获得切入点所在类的所有方法
            Method[] methods = targetClass.getMethods();
            //获取切入点的方法名称
            String methodName = joinPoint.getSignature().getName();
            //获取切入点的参数
            Object[] arguments = joinPoint.getArgs();

            //遍历方法名
            for (Method method : methods) {
                if (method.getName().equals(methodName)) {
                    Class[] clazzs = method.getParameterTypes();
                    //比较声明的参数个数和传入的是否相同
                    if (clazzs.length == arguments.length) {
                        //获取切入点方法上的注解
                        logAnnotation = method.getAnnotation(LogAnnotation.class);
                        break;
                    }
                }
            }
            if (returnValue != null && returnValue.getClass() != HashMap.class) {
                Map<String, Object> map = Maps.newHashMap();
                if (returnValue != null) {
                    BeanMap beanMap = BeanMap.create(returnValue);
                    for (Object key : beanMap.keySet()) {
                        map.put(key + "", beanMap.get(key));
                    }
                }
                logVo.setRequestResult(JSON.toJSONString(map));
                if (map.get("code").toString().equals("0")) {
                    logVo.setStatus("1");
                    if (logAnnotation.operationType().equals(LogOperateTypeEnum.LOGIN)) {
                        LoginResultVo loginVo = JSONObject.parseObject(JSON.toJSONString(map.get("data")), LoginResultVo.class);
                        if (loginVo.getToken().equals("INITSRC")) {
                            loginInfo = new RedisInfo<>();
                            LoginInfo l = new LoginInfo();
                            loginInfo.setPlf(Constant.PLATFORM_WEB);
                            l.setUid("1");
                            l.setNickName("INITSRC");
                            loginInfo.setLoginInfo(l);
                        } else {
                            String account = JwtUtil.getClaim(loginVo.getToken(), AuthConstant.TOKEN_ACCOUNT);
                            if (loginVo != null) {
                                loginInfo = JSON.parseObject(JSON.toJSONString(redis.get(AuthConstant.REDIS_ACCOUNT_KEY + account)), new TypeReference<RedisInfo<LoginInfo>>() {
                                });
                            } else {
                                logVo.setUserId(null);
                            }
                        }
                    }
                } else {
                    logVo.setStatus("0");
                    logVo.setErrorMsg(map.get("msg").toString());
                }
            }
            if (loginInfo != null) {
                //请求用户ID
                logVo.setUserId(loginInfo.getLoginInfo().getUid());
                //请求用户
                logVo.setRequestName(loginInfo.getLoginInfo().getNickName());
                //为日志实体类赋值
                logVo.setBizType(String.valueOf(logAnnotation.operationType().getOperateCode()));
                //忽略shiro返回的数据
                logVo.setTitle(logAnnotation.operateContent());
                //请求路径
                logVo.setRequestUrl(ServletUtils.getRequest().getRequestURI());
                //请求参数
                if(request.getMethod().equals("POST")){
                    Object[] o = joinPoint.getArgs();
                    logVo.setRequestParam(JSON.toJSONString(o[0]));
                }else{
                    logVo.setRequestParam(JSON.toJSONString(request.getParameterMap()));
                }
                // 设置方法名称
                String className = joinPoint.getTarget().getClass().getName();
                logVo.setMethod(className + "." + methodName + "()");
                // 设置请求方式
                logVo.setRequestType(ServletUtils.getRequest().getMethod());
                //获取ip地址
                logVo.setRequestIp(IpUtil.getIpAddr(request));
                //平台类型
                logVo.setPlatformType(loginInfo.getPlf());
                UserAgent userAgent = UserAgent.parseUserAgentString(request.getHeader("user-agent"));
                logVo.setOs(userAgent.getOperatingSystem().getDeviceType().toString());
                logVo.setBrowser(userAgent.getBrowser().toString());
                logVo.setRequestAdress(IpToAddressUtil.getCityInfo(logVo.getRequestIp()));
                //添加日志
                sysLogService.save(logVo);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


    }
}
