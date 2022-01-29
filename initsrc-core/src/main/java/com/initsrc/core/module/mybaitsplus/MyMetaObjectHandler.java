package com.initsrc.core.module.mybaitsplus;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.initsrc.common.base.LoginInfo;
import com.initsrc.common.base.RedisInfo;
import com.initsrc.common.constant.AuthConstant;
import com.initsrc.common.plugin.redis.RedisImpl;
import com.initsrc.common.util.jwt.JwtUtil;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * 自动注入createBy、updateBy、createTime和updateTime
 * 作者：INITSRC
 */
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {
    @Resource
    private RedisImpl redis;

    @Override
    public void insertFill(MetaObject metaObject) {
        String userId = getRequestUserId();
        Object createTime = getFieldValByName("createTime", metaObject);
        Object createBy = getFieldValByName("createBy", metaObject);
        Object version = getFieldValByName("version", metaObject);
        if(version == null)
            setFieldValByName("version", 0, metaObject);

        if (createTime == null)
            setFieldValByName("createTime", new Date(), metaObject);//mybatis-plus版本2.0.9+

        if (createBy == null)
            setFieldValByName("createBy", userId, metaObject);//mybatis-plus版本2.0.9+
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        String userId = getRequestUserId();
        Object version = getFieldValByName("version", metaObject);
        setFieldValByName("version", version, metaObject);
        setFieldValByName("updateTime", new Date(), metaObject);//mybatis-plus版本2.0.9+
        setFieldValByName("updateBy", userId, metaObject);//mybatis-plus版本2.0.9+
    }

    /**
     * 获取请求用户的用户id
     *
     * @return
     */
    private String getRequestUserId() {
        String userId = "1";
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attr.getRequest();
        String token = request.getHeader("token");
        if (token != null && !token.equals("INITSRC")) {
            //获取token值
            String account = JwtUtil.getClaim(token, AuthConstant.TOKEN_ACCOUNT);
            RedisInfo<LoginInfo> loginInfo = JSON.parseObject(JSON.toJSONString(redis.get(AuthConstant.REDIS_ACCOUNT_KEY + account)), new TypeReference<RedisInfo<LoginInfo>>() {
            });
            userId = loginInfo.getLoginInfo().getUid();
        } else {
            userId = "1";
        }
        return userId;
    }


}
