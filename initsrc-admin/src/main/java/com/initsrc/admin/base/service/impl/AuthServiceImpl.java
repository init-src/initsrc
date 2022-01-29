package com.initsrc.admin.base.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.google.code.kaptcha.Constants;
import com.initsrc.admin.base.entity.auth.dto.LoginUserDto;
import com.initsrc.admin.base.entity.auth.vo.AuthInfoVo;
import com.initsrc.admin.base.entity.auth.vo.LoginUserInfoVo;
import com.initsrc.admin.base.entity.auth.vo.UserInfoVo;
import com.initsrc.admin.base.service.AuthService;
import com.initsrc.admin.system.dao.SysPermMapper;
import com.initsrc.admin.system.dao.SysUserMapper;
import com.initsrc.admin.system.service.SysDictService;
import com.initsrc.common.base.*;
import com.initsrc.common.constant.AuthConstant;
import com.initsrc.common.constant.Constant;
import com.initsrc.common.plugin.redis.RedisImpl;
import com.initsrc.common.util.IpUtil;
import com.initsrc.common.util.Md5Util;
import com.initsrc.common.util.jwt.JwtUtil;
import com.initsrc.common.util.tree.TreeParser;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>
 * 授权登录-服务实现类
 * </p>
 *
 * @author 启源（INITSRC）
 * @since  2021-05-21 15:25:48
 */
@Service
public class AuthServiceImpl implements AuthService {
    @Resource
    private SysUserMapper sysUserMapper;
    @Resource
    private RedisImpl redisImpl;
    @Resource
    private SysPermMapper sysPermMapper;
    @Resource
    private SysDictService sysDictService;

    @Override
    public Result<LoginResultVo> login(LoginUserDto dto, HttpServletRequest request) {
        String captchaId = (String)
                request.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);
        if (captchaId == null) {
            return Result.fail("验证码失效");
        }
        if (!captchaId.equals(dto.getCode())) {
            return Result.fail("验证码不正确");
        }
        //根据用户名获取用户信息
        LoginUserInfoVo loginInfo = this.sysUserMapper.getLoginInfoByUsername(dto.getUserName());
        if(loginInfo == null){
            return Result.fail("登录失败，用户不存在或被禁用");
        }
        String pwd = Md5Util.md5Encryption(loginInfo.getUserName(), dto.getPassword(), loginInfo.getSalt());
        if (!pwd.equals(loginInfo.getUserPwd())) {
            return Result.fail("登录失败，用户密码错误");
        }
        // 门票
        Long currentTimeMillis = System.currentTimeMillis();
        RedisInfo<LoginInfo> redisInfo = new RedisInfo<>();
        redisInfo.setCurrentTimeMillis(currentTimeMillis);
        redisInfo.setIp(IpUtil.getIpAddr(request));
        redisInfo.setTicket(currentTimeMillis);
        LoginInfo info = new LoginInfo();
        info.setUid(loginInfo.getId());
        info.setNickName(loginInfo.getNickName());
        redisInfo.setLoginInfo(info);
        redisInfo.setPlf(Constant.PLATFORM_WEB);
        //生成token
        String token = JwtUtil.sign(dto.getUserName(), Constant.PLATFORM_WEB, currentTimeMillis);
        //存储redis 确保token验证
        boolean n = redisImpl.set(AuthConstant.REDIS_ACCOUNT_KEY + dto.getUserName(), redisInfo, AuthConstant.REDIS_EXPIRE_TIME);
        if(!n){
            return Result.fail("登录失败，服务器异常");
        }
        LoginResultVo loginResultVo = new LoginResultVo();
        loginResultVo.setTicket(String.valueOf(currentTimeMillis));
        loginResultVo.setToken(token);
        return Result.success(loginResultVo, "登录成功");
    }

    @Override
    public Result<UserInfoVo> getResfUserInfo(BaseEntity dto, HttpServletRequest request) {
        AuthInfoVo authInfoVo = this.sysUserMapper.getLoginPermByUserId(dto.getAuthId());
        if(authInfoVo == null){
            return Result.fail("获取权限失败");
        }
        List<String> isSearch =new ArrayList<>();
        List<String> roleIds =new ArrayList<>();
        List<String> powerDisps = new ArrayList<>();
        List<String> finalPowerDisps = powerDisps;
        authInfoVo.getRoles().forEach(item ->{
            roleIds.add(item.getRoleId());
            if(!item.getIsSearch().equals("-1") && item.getStatus().equals("1")) {
                isSearch.add(item.getIsSearch());
            }
            //获取自定义权限列表
            if(item.getIsSearch().equals("1")){
                if(StringUtils.isNotBlank(item.getPowerDepts())) {
                    finalPowerDisps.addAll(Arrays.asList(item.getPowerDepts().split(",")));
                }
            }
        });
        //去重自定义部门列表权限
        powerDisps = powerDisps.stream().distinct().collect(Collectors.toList());
        String token = request.getHeader("token");
        if(StringUtils.isBlank(token)){
            return Result.fail("获取权限失败");
        }
        List<LoginPermVo> list = new ArrayList<>();
        if(!token.equals("INITSRC")) {
            String account = JwtUtil.getClaim(token, AuthConstant.TOKEN_ACCOUNT);
            RedisInfo<LoginInfo> info = JSON.parseObject(JSON.toJSONString(redisImpl.get(AuthConstant.REDIS_ACCOUNT_KEY + account)), new TypeReference<RedisInfo<LoginInfo>>() {
            });
            if (info == null) {
                return Result.fail("凭证无效");
            }
            //获取最高权限
            if (isSearch.size() > 0) {
                String min = Collections.min(isSearch);
                info.getLoginInfo().setIsSearch(min);
                //判断是否是最高权限获取菜单权限
//                if (info.getLoginInfo().getIsSearch().equals("0")) {
//                    list = this.sysPermMapper.getAllPermList();
//                } else {
                    list = this.sysPermMapper.getPermListByRoleId(String.join(",",roleIds));
//                }
            }
            info.getLoginInfo().setDepartmentId(authInfoVo.getDeptId());
            info.getLoginInfo().setPowerDepts(StringUtils.join(powerDisps,","));
            info.getLoginInfo().setLoginPermVos(list);
            BeanUtils.copyProperties(authInfoVo, info.getLoginInfo());
            //存储redis 确保token验证
            boolean n = redisImpl.set(AuthConstant.REDIS_ACCOUNT_KEY + account, info, AuthConstant.REDIS_EXPIRE_TIME);
            if (!n) {
                return Result.fail("获取权限失败");
            }
        }else {
            list = this.sysPermMapper.getAllPermList();
        }
        Map<String, Object> map = this.sysDictService.getDictMap();
        UserInfoVo userInfoVo = new UserInfoVo();
        if (list != null) {
            list = TreeParser.getTreeList("0", list);
        }
        userInfoVo.setDictList(map);
        userInfoVo.setAuthInfoVo(authInfoVo);
        userInfoVo.setPermVoList(list);
        return Result.success(userInfoVo);
    }


}
