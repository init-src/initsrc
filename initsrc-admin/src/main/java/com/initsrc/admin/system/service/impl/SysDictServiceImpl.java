package com.initsrc.admin.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.initsrc.admin.system.entity.dict.SysDict;
import com.initsrc.admin.system.entity.dict.vo.SysDictListVo;
import com.initsrc.admin.system.entity.dict.vo.SysDictDetailVo;
import com.initsrc.admin.system.entity.dict.dto.SysDictQueryDto;
import com.initsrc.admin.system.entity.dict.dto.SysDictSaveDto;
import com.initsrc.admin.system.service.SysDictService;
import com.initsrc.admin.system.dao.SysDictMapper;
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
    * 字典表-服务实现类
    * </p>
*
* @author 启源（INITSRC）
* @since  2021-05-20 15:56:31
*/
@Service
public class SysDictServiceImpl extends ServiceImpl<SysDictMapper, SysDict> implements SysDictService {


    /**
    * 查询字典表列表
    *
    * @param  dto
    * @return List<SysDictListVo>
    */
    @Override
    public List<SysDictListVo> pageData(SysDictQueryDto dto) {
        List<SysDictListVo> list = this.getBaseMapper().findList(dto);
        return list;
    }


    /**
    * 查询字典表详情
    *
    * @param id
    * @return Result<SysDictDetailVo>
    */
    @Override
    public Result<SysDictDetailVo> detail(String id) {
        SysDictDetailVo detail = this.getBaseMapper().selectDetailById(id);
        if(detail != null){
            return Result.success(detail);
        }else{
            return Result.fail("查询失败");
        }
    }

    /**
    * 保存字典表数据
    *
    * @param  dto
    * @return Result
    */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result saveData(SysDictSaveDto dto) {
        //判断是否新增
        if (null != dto.getDictId() && StringUtils.isNotBlank(dto.getDictId())) {
            return Result.fail("新增失败");
        }

        //复制信息
        SysDict cdto = new SysDict();
        BeanUtils.copyProperties(dto, cdto);
        //创建
        int n = getBaseMapper().insert(cdto);
        if (n>0) {
           return Result.success("新增成功");
        }
        throw new BusinessException("新增失败");
    }

    /**
    * 更新字典表数据
    *
    * @param dto
    * @return Result
    */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result updateData(SysDictSaveDto dto) {
        //判断是否更新
        SysDict sysDict = this.getById(dto.getDictId());
        if(null ==sysDict){
            return Result.fail("更新失败,查无此信息");
        }
        //复制信息
        BeanUtils.copyProperties(dto, sysDict);
        //更新
        int n = getBaseMapper().updateById(sysDict);
        if (n>0) {
            return Result.success("更新成功");
        }
        throw new BusinessException("更新失败");
    }

    /**
    * 删除字典表数据
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
    * 批量删除字典表数据
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
    public Map<String, Object> getDictMap() {
        QueryWrapper<SysDict> sysDictQueryWrapper = new QueryWrapper<>();
        List<SysDict> dict = this.getBaseMapper().selectList(sysDictQueryWrapper);
        Map<String, Object> map = new HashMap<>();
        for(SysDict item:dict){
            map.put(item.getDictKey(), item.getValue());
        }
        return map;
    }


}
