package com.initsrc.admin.system.service.impl;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.initsrc.admin.system.entity.dept.SysDept;
import com.initsrc.admin.system.entity.dept.vo.SysDeptListVo;
import com.initsrc.admin.system.entity.dept.vo.SysDeptDetailVo;
import com.initsrc.admin.system.entity.dept.dto.SysDeptQueryDto;
import com.initsrc.admin.system.entity.dept.dto.SysDeptSaveDto;
import com.initsrc.admin.system.entity.dept.vo.SysDeptSelectVo;
import com.initsrc.admin.system.service.SysDeptService;
import com.initsrc.admin.system.dao.SysDeptMapper;
import com.initsrc.common.annotation.DataScope;
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
 * 部门-服务实现类
 * </p>
 *
 * @author 启源（INITSRC）
 * @since  2021-05-28 21:44:02
 */
@Service
public class SysDeptServiceImpl extends ServiceImpl<SysDeptMapper, SysDept> implements SysDeptService {


    /**
     * 查询部门列表
     *
     * @param  dto
     * @return List<SysDeptListVo>
     */
    @Override
    @DataScope(deptAlias = "a")
    public List<SysDeptListVo> pageData(SysDeptQueryDto dto) {
        List<SysDeptListVo> list = this.getBaseMapper().findList(dto);
        return list;
    }

    /**
     * 查询部门列表
     *
     * @param  dto
     * @return List<NodeEntity>
     */
    @Override
    @DataScope(deptAlias = "a")
    public List<NodeEntity> leftData(BaseEntity dto) {
        return getPowerTreeNode(dto);
    }
    /**
     * 查询部门下拉选择框列表
     *
     * @param dto
     * @return SysDeptSelectVo
     */
    @Override
    @DataScope(deptAlias = "a")
    public SysDeptSelectVo selectData(BaseEntity dto) {
        SysDeptSelectVo list = new SysDeptSelectVo();
        list.setParentIdList(getPowerTreeNode(dto));
        return list;
    }

    /**
     * 查询部门详情
     *
     * @param id
     * @return Result<SysDeptDetailVo>
     */
    @Override
    public Result<SysDeptDetailVo> detail(String id) {
        SysDeptDetailVo detail = this.getBaseMapper().selectDetailById(id);
        if(detail != null){
            return Result.success(detail);
        }else{
            return Result.fail("查询失败");
        }
    }

    /**
     * 保存部门数据
     *
     * @param  dto
     * @return Result
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result saveData(SysDeptSaveDto dto) {
        //判断父类ID是否存在
        if(!dto.getParentId().equals("0")){
            SysDept pSysDept = this.getById(dto.getParentId());
            if(null == pSysDept){
                return Result.fail("新增失败,父节点不存在");
            }
        }
        //判断是否新增
        if (null != dto.getDeptId() && StringUtils.isNotBlank(dto.getDeptId())) {
            return Result.fail("新增失败");
        }

        //验证部门编号字段是否存在
        int codeCount = this.getBaseMapper().checkerCodeOnly(dto);
        if (codeCount > 0) {
            return Result.fail("新增失败,部门编号已存在");
        }
        //复制信息
        SysDept cdto = new SysDept();
        BeanUtils.copyProperties(dto, cdto);
        String id = IdWorker.getIdStr();
        cdto.setDeptId(id);
        List<String> searchCode = new ArrayList<>();
        if(!dto.getParentId().equals("0")){
            SysDept fdept = this.getById(dto.getParentId());
            if(fdept != null){
                String sc = fdept.getSearchCode()+","+String.valueOf(dto.getDeptId());
                searchCode = Arrays.asList(sc.split(","));
            }else{
                throw new BusinessException("新增失败,上级部门不存在");
            }
        }else{
            searchCode.add(id);
        }
        cdto.setSearchCode(StringUtils.join(searchCode,","));
        //创建
        int n = getBaseMapper().insert(cdto);
        if (n>0) {
            return Result.success("新增成功");
        }
        throw new BusinessException("新增失败");
    }

    /**
     * 更新部门数据
     *
     * @param dto
     * @return Result
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result updateData(SysDeptSaveDto dto) {
        //判断父类ID是否存在
        if(!dto.getParentId().equals("0")){
            SysDept pSysDept = this.getById(dto.getParentId());
            if(null == pSysDept){
                return Result.fail("更新失败,父节点不存在");
            }
        }
        //判断是否更新
        SysDept sysDept = this.getById(dto.getDeptId());
        if(null ==sysDept){
            return Result.fail("更新失败,查无此信息");
        }
        //验证部门编号字段是否存在
        if(!(dto.getCode().equals(sysDept.getCode()))){
            int codeCount = this.getBaseMapper().checkerCodeOnly(dto);
            if (codeCount > 0) {
                return Result.fail("部门编号已存在");
            }
        }
        //复制信息
        BeanUtils.copyProperties(dto, sysDept);
        List<String> searchCode = new ArrayList<>();
        if(!dto.getParentId().equals("0")){
            SysDept fdept = this.getById(dto.getParentId());
            if(fdept != null){
                String sc = fdept.getSearchCode()+","+String.valueOf(sysDept.getDeptId());
                searchCode = Arrays.asList(sc.split(","));
            }
        }else{
            searchCode.add(String.valueOf(sysDept.getDeptId()));
        }
        sysDept.setSearchCode(StringUtils.join(searchCode,","));
        //更新
        int n = getBaseMapper().updateById(sysDept);
        if (n>0) {
            return Result.success("更新成功");
        }
        throw new BusinessException("更新失败");
    }

    /**
     * 删除部门数据
     *
     * @param
     * @return Result
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result deleteData(String id) {
        int n = this.getBaseMapper().selectByParendId(id);
        if(n>0) {
            return Result.fail("删除失败,此部门含有子节点");
        }
        int flag = this.getBaseMapper().deleteById(id);
        if (flag > 0) {
            return Result.success("删除成功");
        }
        throw new BusinessException("更新失败");
    }

    @Override
    @DataScope(deptAlias = "a")
    public List<NodeEntity> getPowerTreeNode(BaseEntity dto) {
        // parent_id字段关联查询列表
        List<NodeEntity> list = this.getBaseMapper().parentIdSelectData(dto);
        if(dto.getScopeType().equals("1")) {
            //判断是否自定义权限或本部门权限，如果是则不生成树形
        }else if(dto.getScopeType().equals("2")){ //判断如果权限为本部门及以下者父类ID为当前用户部门ID
            for(NodeEntity item : list){
                if(item.getId().equals(dto.getScopeId())){
                    list = TreeParser.getTreeList(item.getParentId(),list);
                }
            }

        }else {
            list = TreeParser.getTreeList("0",list);
        }
        return list;
    }


}
