package com.initsrc.dev.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.initsrc.common.base.NodeEntity;
import com.initsrc.common.base.Result;
import com.initsrc.common.exception.BusinessException;
import com.initsrc.common.util.BeanArrayUtils;
import com.initsrc.common.util.tree.TreeParser;
import com.initsrc.dev.dao.DevTableMapper;
import com.initsrc.dev.dao.SysDictDao;
import com.initsrc.dev.entity.column.DevColumn;
import com.initsrc.dev.entity.dict.Dict;
import com.initsrc.dev.entity.table.DevTable;
import com.initsrc.dev.entity.table.dto.DevTableQueryDto;
import com.initsrc.dev.entity.table.dto.DevTableSaveDto;
import com.initsrc.dev.entity.table.vo.DevTableDetailVo;
import com.initsrc.dev.entity.table.vo.DevTableListVo;
import com.initsrc.dev.entity.table.vo.DevTableVo;
import com.initsrc.dev.service.DevColumnService;
import com.initsrc.dev.service.DevTableService;
import com.initsrc.dev.util.GenUtils;
import freemarker.template.TemplateException;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author INITSRC
 * @since 2021-01-25
 */
@Service
public class DevTableServiceImpl extends ServiceImpl<DevTableMapper, DevTable> implements DevTableService {
    @Resource
    private DevColumnService devColumnService;
    @Resource
    private SysDictDao sysDictDao;

    /**
     * 查询数据库表列表
     *
     * @param dto
     * @return
     */
    @Override
    public List<DevTableListVo> pageData(DevTableQueryDto dto) {
        List<DevTableListVo> list = this.getBaseMapper().findList(dto);
        return list;
    }

    @Override
    public Result<DevTableDetailVo> detail(String id) {
        DevTableDetailVo detail = this.getBaseMapper().selectDetailById(id);
        if (detail != null) {
            List<DevTableDetailVo> tables = this.getBaseMapper().findDetailList();
            detail.setTables(tables);
            List<NodeEntity> permList = this.getBaseMapper().getPermList();
            detail.setPermList(TreeParser.getTreeList("0",permList));
            return Result.success(detail);
        } else {
            return Result.fail("查无此信息");
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result SyncDbByTableName(String tableName) {
        DevTableDetailVo genTableDetailVo = this.getBaseMapper().selectDetailByDb(tableName);
        if (genTableDetailVo == null) {
            return Result.fail("同步失败，查无此原表");
        }
        DevTable genTable = new DevTable();
        BeanUtils.copyProperties(genTableDetailVo, genTable);
        LambdaQueryWrapper<DevTable> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(DevTable::getTableName, tableName);
        DevTable data = this.getOne(queryWrapper);
        if (data != null) {
            genTable.setTableId(data.getTableId());
            LambdaQueryWrapper<DevColumn> columnQueryWrapper = new LambdaQueryWrapper<>();
            columnQueryWrapper.eq(DevColumn::getTableId, data.getTableId());
            this.devColumnService.remove(columnQueryWrapper);
            boolean n = this.updateById(genTable);
            if (!n) {
                return Result.fail("同步失败");
            }
        } else {
            String tableId = IdWorker.getIdStr();
            genTable.setTableId(tableId);
            genTable.setFunctionName(genTable.getTableComment());
            genTable.setClassName(GenUtils.camelCaseName(genTable.getTableName()));
            genTable.setBizName(GenUtils.camelCaseName(genTable.getTableName()));
            boolean n = this.save(genTable);
            if (!n) {
                return Result.fail("同步失败");
            }
        }
        List<DevColumn> genColumns = BeanArrayUtils.toBeanList(genTableDetailVo.getColumns(), DevColumn.class);
        genColumns.forEach(item -> {
            item.setTableId(genTable.getTableId());
            GenUtils.ConversionType(item);
        });
        boolean s = this.devColumnService.saveBatch(genColumns);
        if (!s) {
            throw new BusinessException("同步失败");
        }
        return Result.success("同步成功");
    }

    @Override
    public Result<Map<String, String>> previewData(String id) throws Exception {
        DevTableDetailVo detail = this.getBaseMapper().selectDetailById(id);
        if (detail == null) {
            return Result.fail("查无此信息");
        }
        DevTableVo devTableVo = new DevTableVo();
        devTableVo.setParentKey(detail.getParentKey());
        BeanUtils.copyProperties(detail, devTableVo);
        //关联子表信息
        if (detail.getIsRef().equals("1")) {
            DevTableDetailVo subTable = this.getBaseMapper().selectDetailById(detail.getSubTableId());
            DevTableVo subTableVo = new DevTableVo();
            BeanUtils.copyProperties(subTable, subTableVo);
            devTableVo.setSubTable(subTableVo);
        }
        Map<String,Object> dictMap = getDictMap();
        devTableVo.setDictMap(dictMap);
        //生成代码
        Map<String, String>  map = GenUtils.initTemplateByNormal(devTableVo);
        return Result.success(map);
    }

    @Override
    public Result updateData(DevTableSaveDto dto) {
        //判断是否更新
        DevTable genTable = this.getById(dto.getTableId());
        if (genTable == null) {
            return Result.fail("更新失败,查无此信息");
        }
        //复制信息
        DevTable cdto = new DevTable();
        BeanUtils.copyProperties(dto, cdto);
        List<DevColumn> sonDto = BeanArrayUtils.toBeanList(dto.getColumns(), DevColumn.class);
        boolean s = this.devColumnService.updateBatchById(sonDto);
        if (!s) {
            return Result.fail("更新失败");
        }
        //更新
        int n = getBaseMapper().updateById(cdto);
        if (n > 0) {
            return Result.success("更新成功");
        }
        throw new BusinessException("更新失败");
    }

    @Override
    public byte[] downloadCode(String id) throws IOException, TemplateException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ZipOutputStream zip = new ZipOutputStream(outputStream);
        DevTableDetailVo detail = this.getBaseMapper().selectDetailById(id);
        if (detail == null) {
            throw new BusinessException("下载失败");
        }
        DevTableVo devTableVo = new DevTableVo();
        devTableVo.setParentKey(detail.getParentKey());
        BeanUtils.copyProperties(detail, devTableVo);
        //关联子表信息
        if (detail.getIsRef().equals("1")) {
            DevTableDetailVo subTable = this.getBaseMapper().selectDetailById(detail.getSubTableId());
            DevTableVo subTableVo = new DevTableVo();
            BeanUtils.copyProperties(subTable, subTableVo);
            devTableVo.setSubTable(subTableVo);
        }
        //生成代码
        Map<String, String>  map = GenUtils.initTemplateByNormal(devTableVo);
        for (String key : map.keySet()) {
            try
            {
                // 添加到zip
               zip.putNextEntry(new ZipEntry(GenUtils.getFileName(key, devTableVo)));
               IOUtils.write(map.get(key), zip, "UTF-8");
               zip.flush();
               zip.closeEntry();
            }
            catch (IOException e)
            {
                throw new BusinessException("渲染模板失败，表名：" + devTableVo.getTableName()+"失败结果："+e);
            }
        }
        IOUtils.closeQuietly(zip);
        return outputStream.toByteArray();
    }

    public Map<String, Object> getDictMap() {
        QueryWrapper<Dict> sysDictQueryWrapper = new QueryWrapper<>();
        List<Dict> dict = sysDictDao.selectList(sysDictQueryWrapper);
        Map<String, Object> map = new HashMap<>();
        for(Dict item:dict){
            if(item.getType().equals("2")) {
                map.put(item.getDictKey(), JSON.parse(item.getValue()));
            }else {
                map.put(item.getDictKey(), item.getValue());
            }
        }
        return map;
    }
}
