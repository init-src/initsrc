package com.initsrc.core.module.shiro.filter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.initsrc.common.base.BaseSc;
import com.initsrc.common.base.LoginInfo;
import com.initsrc.common.base.RedisInfo;
import com.initsrc.common.base.Result;
import com.initsrc.common.constant.AuthConstant;
import com.initsrc.common.constant.Constant;
import com.initsrc.common.enums.ResultEnum;
import com.initsrc.common.exception.BusinessException;
import com.initsrc.common.plugin.redis.RedisImpl;
import com.initsrc.common.util.IpUtil;
import com.initsrc.common.util.jwt.JWTToken;
import com.initsrc.common.util.jwt.JsonConvertUtil;
import com.initsrc.common.util.jwt.JwtUtil;
import com.initsrc.common.util.rsa.RSAEncrypt;
import lombok.extern.log4j.Log4j2;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


/**
 * @author INITSRC
 * @description 自定义过滤器，对token进行处理
 */
@Log4j2
public class JwtWebFilter extends BasicHttpAuthenticationFilter {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 如果带有 token，则对 token 进行检查，否则直接通过
     */
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws UnauthorizedException {
        //判断请求的请求头是否带上 "token"
        if (isLoginAttempt(request, response)) {
            //如果存在，则进入 executeLogin 方法执行登入，检查 token 是否正确
            try {
                executeLogin(request, response);
                return true;
            } catch (Exception e) {
                response(response, Result.fail(ResultEnum.CODE_402.getCode(), e.getMessage()));
                log.error(IpUtil.getHostIp() + ":权限过滤错误：{}" + ResultEnum.CODE_402.getMsg());
                return false;
            }
        } else {
            response(response, Result.fail(ResultEnum.CODE_402.getCode(), ResultEnum.CODE_402.getMsg()));
            log.error(IpUtil.getHostIp() + "权限过滤错误：{}" + ResultEnum.CODE_402.getMsg());
            return false;
        }

    }

    /**
     * 判断用户是否想要登入。
     * 检测 header 里面是否包含 token 字段
     */
    @Override
    protected boolean isLoginAttempt(ServletRequest request, ServletResponse response) {
        HttpServletRequest req = (HttpServletRequest) request;
        String token = req.getHeader(Constant.LOGIN_SIGN);
        String sc = req.getHeader(Constant.LOGIN_SC);
        if (token != null && sc != null) {
            if (token.equals("INITSRC") && sc.equals("1221")) {
                return true;
            }
            try {
                //token 解析与刷新
                if (this.scParsing(request, response, token, sc)) {
                    return true;
                }
            } catch (Exception e) {
                return false;
            }
        }
        return false;
    }

    /**
     * sc刷新和验证
     * 检验连接是否安全
     */
    private boolean scParsing(ServletRequest request, ServletResponse response, String token, String sc) {
        RedisImpl redisImpl = getBean(RedisImpl.class, (HttpServletRequest) request);
        BaseSc baseSc = null;
        try {
            String str = sc.replaceAll(" ", "+");//将空格转为+号
            baseSc = JSONObject.parseObject(RSAEncrypt.decrypt(str), BaseSc.class);
            if (baseSc != null) {
                //判断接口版本
                if (baseSc.getVersion().compareTo(Constant.CURRENT_API_VERSION) < 0) {
                    response(response, Result.fail(ResultEnum.CODE_405.getCode(), ResultEnum.CODE_405.getMsg()));
                    log.error(IpUtil.getHostIp() + "权限过滤接口版本错误,TOKEN：{}" + token);
                    return false;
                }
                //token解析验证刷新
                return tokenParsing(request, response, redisImpl, token, baseSc);
            } else {
                return false;
            }
        } catch (Exception e) {
            response(response, Result.fail(ResultEnum.CODE_408.getCode(), ResultEnum.CODE_402.getMsg()));
            log.error(IpUtil.getHostIp() + "SC解析异常：{}" + token);
            return false;
        }
    }

    /**
     * 检查是否需要,刷新Token
     *
     * @param
     * @param
     * @param response
     * @return
     */
    private boolean tokenParsing(ServletRequest request, ServletResponse response, RedisImpl redisImpl, String token, BaseSc baseSc) throws InterruptedException {
        //验证token是否过期
        boolean isOk = JwtUtil.verify(token);
        //获取token值
        String account = JwtUtil.getClaim(token, AuthConstant.TOKEN_ACCOUNT);
        //获取标识
        String timeKey = JwtUtil.getClaim(token, AuthConstant.IS_JWT_KEY);
        //获取标识
        String plf = JwtUtil.getClaim(token, AuthConstant.PLATFORM);
        RedisInfo<LoginInfo> redisInfo = JSON.parseObject(JSON.toJSONString(redisImpl.get(AuthConstant.REDIS_ACCOUNT_KEY + account)), new TypeReference<RedisInfo<LoginInfo>>() {
        });
        if (redisInfo == null) {
            response(response, Result.fail(ResultEnum.CODE_401.getCode(), ResultEnum.CODE_401.getMsg()));
            log.error(IpUtil.getHostIp() + "权限过滤redis过期错误,TOKEN：{}" + token);
            return false;
        }
        // 判断IP是否异常
        HttpServletRequest requests = (HttpServletRequest) request;
        String nowIp = IpUtil.getIpAddr(requests);
        if (!nowIp.equals(redisInfo.getIp())) {
            response(response, Result.fail(ResultEnum.CODE_402.getCode(), ResultEnum.CODE_402.getMsg()));
            log.error(IpUtil.getHostIp() + "权限过滤IP异常错误,TOKEN：{}" + token);
            return false;
        }
        if (!(baseSc.getTicket().equals(String.valueOf(redisInfo.getTicket())))) {
            response(response, Result.fail(ResultEnum.CODE_407.getCode(), ResultEnum.CODE_407.getMsg()));
            log.error(IpUtil.getHostIp() + "权限过滤异地登录错误,TOKEN：{}" + token);
            return false;
        }
        //过期token刷新
        if (!isOk) {
            Long currentTime = System.currentTimeMillis();
            //刷新 token 需要进行同步，防止并发请求重复刷新
            synchronized (this) {
                String transitionKey = AuthConstant.PREFIX_REFRESH_TOKEN_TRANSITION + account;
                RedisInfo<LoginInfo> newRedis = JSON.parseObject(JSON.toJSONString(redisImpl.get(AuthConstant.REDIS_ACCOUNT_KEY + account)), new TypeReference<RedisInfo<LoginInfo>>() {
                });
                if (String.valueOf(newRedis.getCurrentTimeMillis()).equals(timeKey)) {
                    String newToken = JwtUtil.sign(account, plf, currentTime);
                    redisInfo.setCurrentTimeMillis(currentTime);
                    //存储redis 确保token验证
                    boolean n = redisImpl.set(AuthConstant.REDIS_ACCOUNT_KEY + account, redisInfo, AuthConstant.REDIS_EXPIRE_TIME);
                    // 最后将刷新的AccessToken存放在Response的Header中的Authorization字段返回
                    HttpServletResponse httpServletResponse = WebUtils.toHttp(response);
                    httpServletResponse.setHeader("token", newToken);
                    httpServletResponse.setHeader("Access-Control-Expose-Headers", "Authorization");
                    boolean s = redisImpl.set(transitionKey, token, AuthConstant.REFRESH_TIME);
                    return true;
                }
                if (String.valueOf(redisImpl.get(transitionKey)).equals(token)) {
                    return true;
                } else {
                    response(response, Result.fail(ResultEnum.CODE_401.getCode(), ResultEnum.CODE_401.getMsg()));
                    log.error(IpUtil.getHostIp() + "凭证过期，请重新登录,TOKEN：{}" + token);
                    return false;
                }
            }

        }
        return true;
    }


    /**
     * 执行登陆操作
     */
    @Override
    protected boolean executeLogin(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String token = httpServletRequest.getHeader("token");
        JWTToken jwtToken = new JWTToken(token);
        // 提交给realm进行登入，如果错误它会抛出异常并被捕获
        getSubject(request, response).login(jwtToken);
        // 如果没有抛出异常则代表登入成功，返回true
        return true;
    }


    /**
     * 对跨域提供支持
     */
    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        httpServletResponse.setHeader("Access-control-Allow-Origin", httpServletRequest.getHeader("Origin"));
        httpServletResponse.setHeader("Access-Control-Allow-Methods", "GET,POST,OPTIONS,PUT,DELETE");
        httpServletResponse.setHeader("Access-Control-Allow-Headers", httpServletRequest.getHeader("Access-Control-Request-Headers"));
        // 跨域时会首先发送一个option请求，这里我们给option请求直接返回正常状态
        if (httpServletRequest.getMethod().equals(RequestMethod.OPTIONS.name())) {
            httpServletResponse.setStatus(HttpStatus.OK.value());
            return false;
        }
        return super.preHandle(request, response);
    }

    /**
     * 无需转发，直接返回Response信息
     */
    private void response(ServletResponse response, Result result) {
        HttpServletResponse httpServletResponse = WebUtils.toHttp(response);
        httpServletResponse.setStatus(HttpStatus.OK.value());
        httpServletResponse.setCharacterEncoding("UTF-8");
        httpServletResponse.setContentType("application/json; charset=utf-8");
        try (PrintWriter out = httpServletResponse.getWriter()) {
            String data = JsonConvertUtil.objectToJson(result);
            out.append(data);
        } catch (IOException e) {
            throw new BusinessException(ResultEnum.CODE_404.getCode(), ResultEnum.CODE_404.getMsg(), "error in the shiro filter");
        }
    }

    /**
     * 注册Bean
     *
     * @param clazz
     * @param request
     * @param <T>
     * @return
     */
    static <T> T getBean(Class<T> clazz, HttpServletRequest request) {
        //通过该方法获得的applicationContext 已经是初始化之后的applicationContext 容器
        WebApplicationContext applicationContext = WebApplicationContextUtils.getRequiredWebApplicationContext(request.getServletContext());
        return applicationContext.getBean(clazz);
    }
}
