package com.initsrc.admin.system.service.impl;

import com.initsrc.admin.system.entity.notice.SysNotice;
import com.initsrc.admin.system.entity.notice.vo.SysNoticeListVo;
import com.initsrc.admin.system.entity.notice.vo.SysNoticeDetailVo;
import com.initsrc.admin.system.entity.notice.dto.SysNoticeQueryDto;
import com.initsrc.admin.system.entity.notice.dto.SysNoticeSaveDto;
import com.initsrc.admin.system.service.SysNoticeService;
import com.initsrc.admin.system.dao.SysNoticeMapper;
import com.initsrc.common.util.easypoi.ExcelUtil;
import javax.servlet.http.HttpServletResponse;
import com.initsrc.common.base.BaseEntity;
import org.springframework.web.multipart.MultipartFile;
import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.afterturn.easypoi.excel.entity.result.ExcelImportResult;
import org.apache.poi.ss.usermodel.Workbook;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
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
 * 通知公告-服务实现类
 * </p>
 *
 * @author 启源（INITSRC）
 * @since  2021-06-07 17:02:27
 */
@Service
public class SysNoticeServiceImpl extends ServiceImpl<SysNoticeMapper, SysNotice> implements SysNoticeService {


    /**
     * 查询通知公告列表
     *
     * @param  dto
     * @return List<SysNoticeListVo>
     */
    @Override
    public List<SysNoticeListVo> pageData(SysNoticeQueryDto dto) {
        List<SysNoticeListVo> list = this.getBaseMapper().findList(dto);
        return list;
    }


    /**
     * 查询通知公告详情
     *
     * @param id
     * @return Result<SysNoticeDetailVo>
     */
    @Override
    public Result<SysNoticeDetailVo> detail(String id) {
        SysNoticeDetailVo detail = this.getBaseMapper().selectDetailById(id);
        if(detail != null){
            return Result.success(detail);
        }else{
            return Result.fail("查询失败");
        }
    }

    /**
     * 导出通知公告数据
     *
     * @param dto
     * @return
     */
    @Override
    public void export(HttpServletResponse response, SysNoticeQueryDto dto) {
        List<SysNoticeListVo> list = this.getBaseMapper().findList(dto);
        ExcelUtil.exoprtExcel(list, "通知公告数据",SysNoticeListVo.class, response);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result<String> batchImport(MultipartFile file, BaseEntity dto, HttpServletResponse response) throws Exception {
        ImportParams importParams = new ImportParams();
        importParams.setNeedVerify(true);
        ExcelImportResult<SysNoticeSaveDto> importResult = ExcelImportUtil.importExcelMore(file.getInputStream(), SysNoticeSaveDto.class, importParams);
        //判断第一层注解验证是否错误
        if (importResult.isVerifyFail()) {
            //把注解验证错误信息生成excel
            Workbook workbook = importResult.getFailWorkbook();
            ExcelUtil.exoprtExcel(workbook, "批量导入失败数据", SysNoticeSaveDto.class, response);
            return Result.fail("失败");
        }
        //所有注解验证成功后开始逻辑验证
        List<SysNoticeSaveDto> list = importResult.getList();
        if (CollectionUtils.isEmpty(list)) {
            return Result.fail("无导入数据");
        }
        for(SysNoticeSaveDto item: list){
            //判断是否新增
            if (null != item.getNoticeId() && StringUtils.isNotBlank(item.getNoticeId())) {
                throw new BusinessException("导入失败");
            }

            //复制信息
            SysNotice cdto = new SysNotice();
            BeanUtils.copyProperties(item, cdto);
            //创建
            int n = getBaseMapper().insert(cdto);
            if (n<=0) {
                throw new BusinessException("导入失败");
            }
        }
        return Result.success("导入成功");
    }

    /**
     * 保存通知公告数据
     *
     * @param  dto
     * @return Result
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result saveData(SysNoticeSaveDto dto) {
        //判断是否新增
        if (null != dto.getNoticeId() && StringUtils.isNotBlank(dto.getNoticeId())) {
            return Result.fail("新增失败");
        }

        //复制信息
        SysNotice cdto = new SysNotice();
        BeanUtils.copyProperties(dto, cdto);
        //创建
        int n = getBaseMapper().insert(cdto);
        if (n>0) {
            return Result.success("新增成功");
        }
        throw new BusinessException("新增失败");
    }

    /**
     * 更新通知公告数据
     *
     * @param dto
     * @return Result
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result updateData(SysNoticeSaveDto dto) {
        //判断是否更新
        SysNotice sysNotice = this.getById(dto.getNoticeId());
        if(null ==sysNotice){
            return Result.fail("更新失败,查无此信息");
        }
        //复制信息
        BeanUtils.copyProperties(dto, sysNotice);
        //更新
        int n = getBaseMapper().updateById(sysNotice);
        if (n>0) {
            return Result.success("更新成功");
        }
        throw new BusinessException("更新失败");
    }

    /**
     * 删除通知公告数据
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
     * 批量删除通知公告数据
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
