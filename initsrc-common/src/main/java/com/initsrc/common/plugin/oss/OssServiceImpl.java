package com.initsrc.common.plugin.oss;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.auth.sts.AssumeRoleRequest;
import com.aliyuncs.auth.sts.AssumeRoleResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.initsrc.common.base.Result;
import com.initsrc.common.enums.ResultEnum;
import com.initsrc.common.exception.BusinessException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
/**
 * 阿里云OSS插件
 * @author INITSRC
 */
@Service
public class OssServiceImpl {
    private String url ="";
    private String endpoint ="sts.cn-hangzhou.aliyuncs.com";
    private String AccessKeyId ="";
    private String accessKeySecret ="";
    private String roleArn ="";
    private String roleSessionName ="";
    private String policy;
    private String bucket ="";

    public Result<Map<String,Object>> getStsAuthorization(){
        System.out.println("aa");
        try {
            // 添加endpoint（直接使用STS endpoint，前两个参数留空，无需添加region ID）
            DefaultProfile.addEndpoint("", "", "Sts", endpoint);
            // 构造default profile（参数留空，无需添加region ID）
            IClientProfile profile = DefaultProfile.getProfile("", AccessKeyId, accessKeySecret);
            // 用profile构造client
            DefaultAcsClient client = new DefaultAcsClient(profile);
            final AssumeRoleRequest request = new AssumeRoleRequest();
            request.setMethod(MethodType.POST);
            request.setRoleArn(roleArn);
            request.setRoleSessionName(roleSessionName);
            request.setPolicy(policy); // 若policy为空，则用户将获得该角色下所有权限
            request.setDurationSeconds(1000L); // 设置凭证有效时间
            final AssumeRoleResponse response = client.getAcsResponse(request);
            Map<String,Object> map = new HashMap<>();
            map.put("url",url);
            map.put("expiration",response.getCredentials().getExpiration());
            map.put("AccessKeyId",response.getCredentials().getAccessKeyId());
            map.put("accessKeySecret",response.getCredentials().getAccessKeySecret());
            map.put("securityToken",response.getCredentials().getSecurityToken());
            map.put("bucket",bucket);
            return Result.success(map);
        } catch (ClientException e) {
            throw new BusinessException(ResultEnum.CODE_501.getCode(),ResultEnum.CODE_501.getMsg(),e.getErrMsg());
        }
    }
}
