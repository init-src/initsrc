package com.initsrc.admin.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.initsrc.admin.base.entity.auth.vo.LoginUserInfoVo;
import com.initsrc.admin.system.dao.SysRoleMapper;
import com.initsrc.admin.system.entity.role.SysRole;
import com.initsrc.admin.system.entity.role.SysRolePerm;
import com.initsrc.admin.system.entity.user.SysUser;
import com.initsrc.admin.system.entity.user.SysUserRole;
import com.initsrc.admin.system.entity.user.vo.SysUserListVo;
import com.initsrc.admin.system.entity.user.vo.SysUserDetailVo;
import com.initsrc.admin.system.entity.user.dto.SysUserQueryDto;
import com.initsrc.admin.system.entity.user.dto.SysUserSaveDto;
import com.initsrc.admin.system.entity.user.vo.SysUserSelectVo;
import com.initsrc.admin.system.service.SysDeptService;
import com.initsrc.admin.system.service.SysUserRoleService;
import com.initsrc.admin.system.service.SysUserService;
import com.initsrc.admin.system.dao.SysUserMapper;
import com.initsrc.common.annotation.DataScope;
import com.initsrc.common.base.NodeEntity;
import com.initsrc.common.base.BaseEntity;
import com.initsrc.common.util.Md5Util;
import com.initsrc.common.util.tree.TreeParser;
import com.initsrc.common.exception.BusinessException;
import com.initsrc.common.base.Result;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import java.math.BigDecimal;
import java.util.*;
import javax.annotation.Resource;
import org.apache.commons.lang3.StringUtils;
/**
* <p>
    * 用户-服务实现类
    * </p>
*
* @author 启源（INITSRC）
* @since  2021-05-21 15:25:48
*/
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {
    @Resource
    private SysUserRoleService sysUserRoleService;
    @Resource
    private SysRoleMapper sysRoleMapper;
    @Resource
    private SysDeptService sysDeptService;

    /**
    * 查询用户列表
    *
    * @param  dto
    * @return List<SysUserListVo>
    */
    @Override
    @DataScope(deptAlias = "a")
    public List<SysUserListVo> pageData(SysUserQueryDto dto) {
        List<SysUserListVo> list = this.getBaseMapper().findList(dto);
        return list;
    }

    /**
    * 查询用户列表
    *
    * @param  dto
    * @return List<NodeEntity>
    */
    @Override
    public List<NodeEntity> leftData(BaseEntity dto) {
        List<NodeEntity> list = this.sysDeptService.getPowerTreeNode(dto);
        return list;
    }
    /**
    * 查询用户下拉选择框列表
    *
    * @param dto
    * @return SysUserSelectVo
    */
    @Override
    public SysUserSelectVo selectData(BaseEntity dto) {
        SysUserSelectVo list = new SysUserSelectVo();
        // dept_id字段关联查询列表
        List<NodeEntity> treeNode = this.sysDeptService.getPowerTreeNode(dto);
        list.setDeptIdList(treeNode);
        // role_id字段关联查询列表
        List<NodeEntity> roleIdList = this.sysRoleMapper.roleIdSelectData();
        list.setRoleIdList(roleIdList);
     return list;
    }

    /**
    * 查询用户详情
    *
    * @param id
    * @return Result<SysUserDetailVo>
    */
    @Override
    public Result<SysUserDetailVo> detail(String id) {
        SysUserDetailVo detail = this.getBaseMapper().selectDetailById(id);
        if(detail != null){
            return Result.success(detail);
        }else{
            return Result.fail("查询失败");
        }
    }

    /**
    * 保存用户数据
    *
    * @param  dto
    * @return Result
    */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result saveData(SysUserSaveDto dto) {
        //判断是否新增
        if (null != dto.getUserId() && StringUtils.isNotBlank(dto.getUserId())) {
            return Result.fail("新增失败");
        }

        //验证用户名字段是否存在
        int userNameCount = this.getBaseMapper().checkerUserNameOnly(dto);
        if (userNameCount > 0) {
            return Result.fail("新增失败,用户名已存在");
        }
        //复制信息
        SysUser cdto = new SysUser();
        BeanUtils.copyProperties(dto, cdto);
        String ids = IdWorker.getIdStr();
        cdto.setUserId(ids);
        String pwddf = "a123456";
        int i = (int) (Math.random() * (9999 - 1000 + 1)) + 1000;
        String pwd = Md5Util.md5Encryption(dto.getUserName(), pwddf, String.valueOf(i));
        cdto.setSalt(String.valueOf(i));
        cdto.setUserPwd(pwd);
        if (StringUtils.isNotBlank(dto.getUserPwd())) {
            pwddf = dto.getUserPwd();
        }
        //创建
        int n = getBaseMapper().insert(cdto);
        if (n>0) {
            List<SysUserRole> list = new ArrayList<>();
            if (dto.getRoleId() != null) {
                for (int s = 0; s < dto.getRoleId().size(); s++) {
                    SysUserRole rp = new SysUserRole();
                    rp.setUserId(ids);
                    rp.setRoleId(dto.getRoleId().get(s));
                    list.add(rp);
                }
                boolean ns = this.sysUserRoleService.saveBatch(list);
            }
            return Result.success("新增成功");
        }
        throw new BusinessException("新增失败");
    }

    /**
    * 更新用户数据
    *
    * @param dto
    * @return Result
    */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result updateData(SysUserSaveDto dto) {
        //判断是否更新
        SysUser sysUser = this.getById(dto.getUserId());
        if(sysUser == null){
            return Result.fail("更新失败,查无此信息");
        }
        //验证用户名字段是否存在
        if(!(dto.getUserName().equals(sysUser.getUserName()))){
            int userNameCount = this.getBaseMapper().checkerUserNameOnly(dto);
            if (userNameCount > 0) {
                return Result.fail("用户名已存在");
            }
        }
        //复制信息
        BeanUtils.copyProperties(dto, sysUser);
        if (StringUtils.isNotBlank(dto.getUserPwd())) {
            String pwd = Md5Util.md5Encryption(dto.getUserName(), dto.getUserPwd(), sysUser.getSalt());
            sysUser.setUserPwd(pwd);
        }
        //更新
        int n = getBaseMapper().updateById(sysUser);
        if (n>0) {
            QueryWrapper<SysUserRole> queryWrapper = new QueryWrapper<>();
            queryWrapper.lambda().eq(SysUserRole::getUserId, sysUser.getUserId());
            boolean nx = this.sysUserRoleService.remove(queryWrapper);
            List<SysUserRole> list = new ArrayList<>();
            if (dto.getRoleId() != null) {
                for (int s = 0; s < dto.getRoleId().size(); s++) {
                    SysUserRole rp = new SysUserRole();
                    rp.setUserId(sysUser.getUserId());
                    rp.setRoleId(dto.getRoleId().get(s));
                    list.add(rp);
                }
                boolean ns = this.sysUserRoleService.saveBatch(list);
            }
            return Result.success("更新成功");
        }
        throw new BusinessException("更新失败");
    }

    /**
    * 删除用户数据
    *
    * @param
    * @return Result
    */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result deleteData(String id) {
        int flag = this.getBaseMapper().deleteById(id);
        if (flag > 0) {
        return Result.success("删除成功");
        }
        throw new BusinessException("更新失败");
    }
    /**
    * 批量删除用户数据
    *
    * @param
    * @return Result
    */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result deletesData(List<String> ids) {
        if(ids.size()<=0){
          return Result.errorParam();
        }
        int flag = this.getBaseMapper().deleteBatchIds(ids);
        if (flag > 0) {
            return Result.success("删除成功");
        }
        throw new BusinessException("更新失败");
    }

    @Override
    public LoginUserInfoVo getLoginInfoByUsername(String userName) {
        return this.getBaseMapper().getLoginInfoByUsername(userName);
    }


}
