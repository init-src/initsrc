package com.initsrc.common.util.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.initsrc.common.constant.AuthConstant;
import com.initsrc.common.enums.ResultEnum;
import com.initsrc.common.exception.BusinessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.util.Date;

/**
 * JAVA-JWT工具类
 *
 * @author INITSRC
 * @date 2021/4/21 15:14
 */
@Component
public class JwtUtil {

    /**
     * LOGGER
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(JwtUtil.class);

    /**
     * 过期时间改为从配置文件获取
     */
    private static long accessTokenExpireTime = AuthConstant.TOKEN_EXPIRE_TIME;

    /**
     * JWT认证加密私钥(Base64加密)
     */
    private static String encryptJWTKey = AuthConstant.ENCRYPT_JWT_KEY;

    /**
     * 校验token是否正确
     *
     * @param token Token
     * @return boolean 是否正确
     * @author Wang926454
     * @date 2018/8/31 9:05
     */
    public static boolean verify(String token) {
        return verify(token, AuthConstant.TOKEN_ACCOUNT);
    }

    /**
     * 校验token是否正确
     *
     * @param token Token
     * @return boolean 是否正确
     * @author Wang926454
     * @date 2018/8/31 9:05
     */
    public static boolean verify(String token, String baseKey) {
        Algorithm algorithm = null;
        try {
            // 帐号加JWT私钥解密
            try {
                String secret = getClaim(token, baseKey) + Base64ConvertUtil.decode(encryptJWTKey);

                algorithm = Algorithm.HMAC256(secret);
            } catch (UnsupportedEncodingException e) {
                throw new BusinessException(ResultEnum.CODE_501.getCode(), ResultEnum.CODE_501.getMsg(), e.getMessage());
            }
            JWTVerifier verifier = JWT.require(algorithm)
                    .build();
            DecodedJWT jwt = verifier.verify(token);
            return true;
        } catch (TokenExpiredException e) {
            return false;
        }
    }

    /**
     * 获得Token中的信息无需secret解密也能获得
     *
     * @param token
     * @param claim
     * @return java.lang.String
     * @author Wang926454
     * @date 2018/9/7 16:54
     */
    public static String getClaim(String token, String claim) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            // 只能输出String类型，如果是其他类型返回null
            return jwt.getClaim(claim).asString();
        } catch (JWTDecodeException e) {
            LOGGER.error("解密Token中的公共信息出现JWTDecodeException异常:" + e.getMessage());
            throw new BusinessException(ResultEnum.CODE_501.getCode(), ResultEnum.CODE_501.getMsg(), e.getMessage());
        }
    }

    /**
     * 生成签名
     *
     * @param account 帐号
     * @return java.lang.String 返回加密的Token
     * @author Wang926454
     * @date 2018/8/31 9:07
     */
    public static String sign(String account, String platform, long time) {
        try {
            // 帐号加JWT私钥加密
            String secret = account + Base64ConvertUtil.decode(encryptJWTKey);
            // 此处过期时间是以毫秒为单位，所以乘以1000
            Date date = new Date(System.currentTimeMillis() + accessTokenExpireTime * 1000);
            Algorithm algorithm = Algorithm.HMAC256(secret);
            // 附带account帐号信息
            return JWT.create()
                    .withClaim(AuthConstant.TOKEN_ACCOUNT, account)
                    .withClaim(AuthConstant.IS_JWT_KEY, String.valueOf(time)) //验证token是否和redis一致
                    .withClaim(AuthConstant.PLATFORM, platform) //平台
                    .withExpiresAt(date)
                    .sign(algorithm);
        } catch (UnsupportedEncodingException e) {
            LOGGER.error("JWTToken加密出现UnsupportedEncodingException异常:" + e.getMessage());
            throw new BusinessException(ResultEnum.CODE_501.getCode(), ResultEnum.CODE_501.getMsg(), e.getMessage());
        }
    }

}
