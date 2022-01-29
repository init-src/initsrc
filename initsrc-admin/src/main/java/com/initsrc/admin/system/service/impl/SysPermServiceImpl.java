package com.initsrc.admin.system.service.impl;

import com.initsrc.admin.system.entity.perm.SysPerm;
import com.initsrc.admin.system.entity.perm.vo.SysPermListVo;
import com.initsrc.admin.system.entity.perm.vo.SysPermDetailVo;
import com.initsrc.admin.system.entity.perm.dto.SysPermQueryDto;
import com.initsrc.admin.system.entity.perm.dto.SysPermSaveDto;
import com.initsrc.admin.system.entity.perm.vo.SysPermSelectVo;
import com.initsrc.admin.system.service.SysPermService;
import com.initsrc.admin.system.dao.SysPermMapper;
import com.initsrc.common.base.NodeEntity;
import com.initsrc.common.base.BaseEntity;
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
 * 菜单-服务实现类
 * </p>
 *
 * @author 启源（INITSRC）
 * @since  2021-05-26 17:03:56
 */
@Service
public class SysPermServiceImpl extends ServiceImpl<SysPermMapper, SysPerm> implements SysPermService {


    /**
     * 查询菜单列表
     *
     * @param  dto
     * @return List<SysPermListVo>
     */
    @Override
    public List<SysPermListVo> pageData(SysPermQueryDto dto) {
        List<SysPermListVo> list = this.getBaseMapper().findList(dto);
        return list;
    }

    /**
     * 查询菜单列表
     *
     * @param  dto
     * @return List<NodeEntity>
     */
    @Override
    public List<NodeEntity> leftData(BaseEntity dto) {
        List<NodeEntity> list = this.getBaseMapper().parentIdSelectData();
        list = TreeParser.getTreeList("0",list);
        return list;
    }
    /**
     * 查询菜单下拉选择框列表
     *
     * @param dto
     * @return SysPermSelectVo
     */
    @Override
    public SysPermSelectVo selectData(BaseEntity dto) {
        SysPermSelectVo list = new SysPermSelectVo();
        // parent_id字段关联查询列表
        List<NodeEntity> parentIdList = this.getBaseMapper().parentIdSelectData();
        list.setParentIdList(TreeParser.getTreeList("0",parentIdList));
        return list;
    }

    /**
     * 查询菜单详情
     *
     * @param id
     * @return Result<SysPermDetailVo>
     */
    @Override
    public Result<SysPermDetailVo> detail(String id) {
        SysPermDetailVo detail = this.getBaseMapper().selectDetailById(id);
        if(detail != null){
            return Result.success(detail);
        }else{
            return Result.fail("查询失败");
        }
    }

    /**
     * 保存菜单数据
     *
     * @param  dto
     * @return Result
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result saveData(SysPermSaveDto dto) {
        //判断父类ID是否存在
        if(!dto.getParentId().equals("0")){
            SysPerm pSysPerm = this.getById(dto.getParentId());
            if(null == pSysPerm){
                return Result.fail("新增失败,父节点不存在");
            }
        }
        //判断是否新增
        if (null != dto.getPermId() && StringUtils.isNotBlank(dto.getPermId())) {
            return Result.fail("新增失败");
        }

        //复制信息
        SysPerm cdto = new SysPerm();
        BeanUtils.copyProperties(dto, cdto);
        //创建
        int n = getBaseMapper().insert(cdto);
        if (n>0) {
            return Result.success("新增成功");
        }
        throw new BusinessException("新增失败");
    }

    /**
     * 更新菜单数据
     *
     * @param dto
     * @return Result
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result updateData(SysPermSaveDto dto) {
        //判断父类ID是否存在
        if(!dto.getParentId().equals("0")){
            SysPerm pSysPerm = this.getById(dto.getParentId());
            if(null == pSysPerm){
                return Result.fail("更新失败,父节点不存在");
            }
        }
        //判断是否更新
        SysPerm sysPerm = this.getById(dto.getPermId());
        if(null ==sysPerm){
            return Result.fail("更新失败,查无此信息");
        }
        //复制信息
        BeanUtils.copyProperties(dto, sysPerm);
        //更新
        int n = getBaseMapper().updateById(sysPerm);
        if (n>0) {
            return Result.success("更新成功");
        }
        throw new BusinessException("更新失败");
    }

    /**
     * 删除菜单数据
     *
     * @param
     * @return Result
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result deleteData(String id) {
        int n = this.getBaseMapper().selectByParendId(id);
        if(n>0) {
            return Result.fail("删除失败,此菜单含有子节点");
        }
        int flag = this.getBaseMapper().deleteById(id);
        if (flag > 0) {
            return Result.success("删除成功");
        }
        throw new BusinessException("更新失败");
    }


}
