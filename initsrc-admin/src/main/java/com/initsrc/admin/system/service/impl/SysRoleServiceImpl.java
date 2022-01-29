package com.initsrc.admin.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.initsrc.admin.system.dao.SysPermMapper;
import com.initsrc.admin.system.dao.SysUserMapper;
import com.initsrc.admin.system.entity.role.SysRole;
import com.initsrc.admin.system.entity.role.SysRolePerm;
import com.initsrc.admin.system.entity.role.vo.SysRoleListVo;
import com.initsrc.admin.system.entity.role.vo.SysRoleDetailVo;
import com.initsrc.admin.system.entity.role.dto.SysRoleQueryDto;
import com.initsrc.admin.system.entity.role.dto.SysRoleSaveDto;
import com.initsrc.admin.system.entity.role.vo.SysRoleSelectVo;
import com.initsrc.admin.system.entity.user.vo.SysUserListVo;
import com.initsrc.admin.system.service.SysDeptService;
import com.initsrc.admin.system.service.SysPermService;
import com.initsrc.admin.system.service.SysRolePermService;
import com.initsrc.admin.system.service.SysRoleService;
import com.initsrc.admin.system.dao.SysRoleMapper;
import com.initsrc.common.base.BaseEntity;
import com.initsrc.common.base.LoginPermVo;
import com.initsrc.common.base.NodeEntity;
import com.initsrc.common.exception.BusinessException;
import com.initsrc.common.base.Result;
import com.initsrc.common.util.tree.TreeParser;
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
 * 角色-服务实现类
 * </p>
 *
 * @author 启源（INITSRC）
 * @since  2021-05-28 14:57:28
 */
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {
    @Resource
    private SysPermMapper sysPermMapper;
    @Resource
    private SysRolePermService sysRolePermService;
    @Resource
    private SysUserMapper sysUserMapper;
    @Resource
    private SysDeptService sysDeptService;

    /**
     * 查询角色列表
     *
     * @param  dto
     * @return List<SysRoleListVo>
     */
    @Override
    public List<SysRoleListVo> pageData(SysRoleQueryDto dto) {
        List<SysRoleListVo> list = this.getBaseMapper().findList(dto);
        return list;
    }

    /**
     * 查询角色下拉选择框列表
     *
     * @param dto
     * @return SysRoleSelectVo
     */
    @Override
    public SysRoleSelectVo selectData(BaseEntity dto) {
        SysRoleSelectVo list = new SysRoleSelectVo();
        // power_depts字段关联查询列表
        List<NodeEntity> powerDeptsList = this.sysDeptService.getPowerTreeNode(dto);
        list.setPowerDeptsList(powerDeptsList);
        List<LoginPermVo> permList = this.sysPermMapper.getAllPermList();
        list.setPermList(TreeParser.getTreeList("0",permList));
        return list;
    }

    /**
     * 查询角色详情
     *
     * @param id
     * @return Result<SysRoleDetailVo>
     */
    @Override
    public Result<SysRoleDetailVo> detail(String id) {
        SysRoleDetailVo detail = this.getBaseMapper().selectDetailById(id);
        if(detail != null){
            List<LoginPermVo> permVos = this.sysPermMapper.getPermListByRoleId(id);
            detail.setPermVos(TreeParser.getTreeList("0",permVos));
            List<SysUserListVo> sysUserListVo = sysUserMapper.findUserByRoleId(id);
            detail.setSysUserListVo(sysUserListVo);
            return Result.success(detail);
        }else{
            return Result.fail("查询失败");
        }
    }

    /**
     * 保存角色数据
     *
     * @param  dto
     * @return Result
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result saveData(SysRoleSaveDto dto) {
        //判断是否新增
        if (null != dto.getRoleId() && StringUtils.isNotBlank(dto.getRoleId())) {
            return Result.fail("新增失败");
        }

        //复制信息
        SysRole cdto = new SysRole();
        BeanUtils.copyProperties(dto, cdto);
        String ids = IdWorker.getIdStr();
        cdto.setRoleId(ids);
        if(dto.getPowerDepts() != null){
            cdto.setPowerDepts(String.join(",",dto.getPowerDepts()));
        }
        //创建
        int n = getBaseMapper().insert(cdto);
        if (n>0) {
            List<SysRolePerm> list = new ArrayList<>();
            if (dto.getPermList() != null) {
                for (int i = 0; i < dto.getPermList().size(); i++) {
                    SysRolePerm rp = new SysRolePerm();
                    rp.setRoleId(ids);
                    rp.setPermId(dto.getPermList().get(i));
                    list.add(rp);
                }
                boolean ns = this.sysRolePermService.saveBatch(list);
            }
            return Result.success("新增成功");
        }
        throw new BusinessException("新增失败");
    }

    /**
     * 更新角色数据
     *
     * @param dto
     * @return Result
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result updateData(SysRoleSaveDto dto) {
        //判断是否更新
        SysRole sysRole = this.getById(dto.getRoleId());
        if(null ==sysRole){
            return Result.fail("更新失败,查无此信息");
        }
        //复制信息
        BeanUtils.copyProperties(dto, sysRole);
        if(dto.getPowerDepts() != null){
            sysRole.setPowerDepts(String.join(",",dto.getPowerDepts()));
        }
        //更新
        int n = getBaseMapper().updateById(sysRole);
        if (n>0) {
            QueryWrapper<SysRolePerm> queryWrapper = new QueryWrapper<>();
            queryWrapper.lambda().eq(SysRolePerm::getRoleId, sysRole.getRoleId());
            boolean nx = this.sysRolePermService.remove(queryWrapper);
            List<SysRolePerm> list = new ArrayList<>();
            if (dto.getPermList() != null) {
                for (int i = 0; i < dto.getPermList().size(); i++) {
                    SysRolePerm rp = new SysRolePerm();
                    rp.setPermId(dto.getPermList().get(i));
                    rp.setRoleId(dto.getRoleId());
                    list.add(rp);
                }
                boolean ns = this.sysRolePermService.saveBatch(list);
            }
            return Result.success("更新成功");
        }
        throw new BusinessException("更新失败");
    }

    /**
     * 删除角色数据
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
     * 批量删除角色数据
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


}
