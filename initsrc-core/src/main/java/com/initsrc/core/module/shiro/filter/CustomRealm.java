package com.initsrc.core.module.shiro.filter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.initsrc.common.base.LoginInfo;
import com.initsrc.common.base.RedisInfo;
import com.initsrc.common.constant.AuthConstant;
import com.initsrc.common.plugin.redis.RedisImpl;
import com.initsrc.common.util.jwt.JWTToken;
import com.initsrc.common.util.jwt.JwtUtil;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
/**
 * @author INITSRC
 * @date 2021/4/23
 * @description  自定义Realm,实现Shiro安全认证
 */

@Component
public class CustomRealm extends AuthorizingRealm {

    @Resource
    RedisImpl redisImpl;

    /**
     * 必须重写此方法，不然会报错
     */
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JWTToken;
    }

    /**
     *  只有当需要检测用户权限的时候才会调用此方法，例如checkRole,checkPermission之类的
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        //获取token
        String token = principals.toString();
        if (token.equals("INITSRC")) {
            simpleAuthorizationInfo.addStringPermission("p");
        } else {
            //根据token获取用户名
            String account = JwtUtil.getClaim(token, AuthConstant.TOKEN_ACCOUNT);
            //根据用户名获取redis存储的权限
            RedisInfo<LoginInfo> loginScRedisInfo = JSON.parseObject(JSON.toJSONString(redisImpl.get(AuthConstant.REDIS_ACCOUNT_KEY + account)), new TypeReference<RedisInfo<LoginInfo>>() {
            });
            if (loginScRedisInfo != null) {
                //注册权限
                loginScRedisInfo.getLoginInfo().getLoginPermVos().forEach(item -> {
                    simpleAuthorizationInfo.addStringPermission(item.getPerm());
                });
            } else {
                throw new AuthenticationException("凭证过期");
            }
        }
        return simpleAuthorizationInfo;
    }


    /**
     * 默认使用此方法进行用户名正确与否验证，错误抛出异常即可。
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken auth) throws AuthenticationException {
        String token = (String) auth.getCredentials();

        return new SimpleAuthenticationInfo(token, token, "MyRealm");
    }

}
