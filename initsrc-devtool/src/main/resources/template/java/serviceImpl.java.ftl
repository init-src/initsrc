package ${packageName}.${moduleName}.service.impl;

import ${packageName}.${moduleName}.entity.${bizName}.${className};
import ${packageName}.${moduleName}.entity.${bizName}.vo.${className}ListVo;
import ${packageName}.${moduleName}.entity.${bizName}.vo.${className}DetailVo;
import ${packageName}.${moduleName}.entity.${bizName}.dto.${className}QueryDto;
import ${packageName}.${moduleName}.entity.${bizName}.dto.${className}SaveDto;
<#if isSelect == '1'>
import ${packageName}.${moduleName}.entity.${bizName}.vo.${className}SelectVo;
</#if>
import ${packageName}.${moduleName}.service.${className}Service;
import ${packageName}.${moduleName}.dao.${className}Mapper;
<#if subTable??>
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import ${subTable.packageName}.${subTable.moduleName}.entity.${subTable.bizName}.${subTable.className};
import ${subTable.packageName}.${subTable.moduleName}.service.${subTable.className}Service;
import com.initsrc.common.util.BeanArrayUtils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
</#if>
<#if isSelect == '1' || isCategory == "2">
import com.initsrc.common.base.NodeEntity;
import com.initsrc.common.base.BaseEntity;
import com.initsrc.common.util.tree.TreeParser;
</#if>
<#if isExcel == "1">
import com.initsrc.common.util.easypoi.ExcelUtil;
import javax.servlet.http.HttpServletResponse;
import com.initsrc.common.base.BaseEntity;
import org.springframework.web.multipart.MultipartFile;
import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.afterturn.easypoi.excel.entity.result.ExcelImportResult;
import org.apache.poi.ss.usermodel.Workbook;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
</#if>
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
    * ${functionName}-???????????????
    * </p>
*
* @author ${genAuthor}
* @since  ${date?string("yyyy-MM-dd HH:mm:ss")}
*/
@Service
public class ${className}ServiceImpl extends ServiceImpl<${className}Mapper, ${className}> implements ${className}Service {
    <#if subTable??>
    @Resource
    private  ${subTable.className}Service  ${subTable.className?uncap_first}Service;</#if>

    /**
    * ??????${functionName}??????
    *
    * @param  dto
    * @return List<${className}ListVo>
    */
    @Override
    public List<${className}ListVo> pageData(${className}QueryDto dto) {
        List<${className}ListVo> list = this.getBaseMapper().findList(dto);
        return list;
    }

    <#if vueTableType =='2'>
    /**
    * ??????${functionName}??????
    *
    * @param  dto
    * @return List<NodeEntity>
    */
    @Override
    public List<NodeEntity> leftData(BaseEntity dto) {
        <#list columns as field>
        <#if field.columnName == columnKey>
        List<NodeEntity> list = this.getBaseMapper().${field.javaField}SelectData(dto);
        <#if field.htmlType == '11'>
        list = TreeParser.getTreeList("0",list);
        </#if>
        </#if>
        </#list>
        return list;
    }
    </#if>
    <#if isSelect == '1'>
    /**
    * ??????${functionName}?????????????????????
    *
    * @param dto
    * @return ${className}SelectVo
    */
    @Override
    public ${className}SelectVo selectData(BaseEntity dto) {
        ${className}SelectVo list = new ${className}SelectVo();
        <#list columns as field>
        <#if field.isSearch == '1'>
        // ${field.columnName}????????????????????????
        List<NodeEntity> ${field.javaField}List = this.getBaseMapper().${field.javaField}SelectData(dto);
        <#if field.htmlType == '11'>
        list.set${field.javaField?cap_first}List(TreeParser.getTreeList("0",${field.javaField}List));
        <#else >
        list.set${field.javaField?cap_first}List(${field.javaField}List);
        </#if>
        </#if>
        </#list>
     return list;
    }
    </#if>

    /**
    * ??????${functionName}??????
    *
    * @param id
    * @return Result<${className}DetailVo>
    */
    @Override
    public Result<${className}DetailVo> detail(String id) {
        ${className}DetailVo detail = this.getBaseMapper().selectDetailById(id);
        if(detail != null){
            return Result.success(detail);
        }else{
            return Result.fail("????????????");
        }
    }

    <#if isExcel == "1">
    /**
    * ??????${functionName}??????
    *
    * @param dto
    * @return
    */
    @Override
    public void export(HttpServletResponse response, ${className}QueryDto dto) {
        List<${className}ListVo> list = this.getBaseMapper().findList(dto);
        ExcelUtil.exoprtExcel(list, "${functionName}??????",${className}ListVo.class, response);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result<String> batchImport(MultipartFile file, BaseEntity dto, HttpServletResponse response) throws Exception {
        ImportParams importParams = new ImportParams();
        importParams.setNeedVerify(true);
        ExcelImportResult<${className}SaveDto> importResult = ExcelImportUtil.importExcelMore(file.getInputStream(), ${className}SaveDto.class, importParams);
        //???????????????????????????????????????
        if (importResult.isVerifyFail()) {
            //?????????????????????????????????excel
            Workbook workbook = importResult.getFailWorkbook();
            ExcelUtil.exoprtExcel(workbook, "????????????????????????", ${className}SaveDto.class, response);
            return Result.fail("??????");
        }
        //?????????????????????????????????????????????
        List<${className}SaveDto> list = importResult.getList();
            if (CollectionUtils.isEmpty(list)) {
            return Result.fail("???????????????");
        }
        for(${className}SaveDto item: list){
            <#if isCategory == "2">
            //????????????ID????????????
            if(!item.get${parentKey?cap_first}().equals("0")){
                ${className} p${className?cap_first} = this.getById(item.get${parentObj.javaField?cap_first}());
                if(null == p${className?cap_first}){
                    throw new BusinessException(("????????????,??????????????????");
                }
            }
            </#if>
            ${className}SaveDto dto2 = new ${className}SaveDto();
            BeanUtils.copyProperties(dto, dto2);
            <#list columns as field>
            <#if field.isKey == "1">
            //??????????????????
            if (null != item.get${field.javaField?cap_first}() && StringUtils.isNotBlank(item.get${field.javaField?cap_first}())) {
                throw new BusinessException("????????????");
            }

            </#if>
            <#if field.isOnly !="0">
            //??????${field.columnComment}??????????????????
            int ${field.javaField}Count = this.getBaseMapper().checker${field.javaField?cap_first}Only(dto2);
            if (${field.javaField}Count > 0) {
             throw new BusinessException("????????????,${field.columnComment}?????????");
            }
            </#if>
            </#list>
            //????????????
            ${className} cdto = new ${className}();
            BeanUtils.copyProperties(item, cdto);
            <#if subTable??>
            //????????????
            if(dto.get${subTable.className}s().size()>0){
                cdto.set${keyLabel?cap_first}(IdWorker.getIdStr());
                List<${subTable.className}> sonDto = BeanArrayUtils.toBeanList(dto.get${subTable.className}s(),${subTable.className}.class);
                sonDto.forEach(item ->{
                item.set<#list subTable.columns as item><#if subKey == item.columnId>${item.javaField?cap_first}</#if></#list>(cdto.get${keyLabel?cap_first}());
                });
                boolean s = this.${subTable.className?uncap_first}Service.saveBatch(sonDto);
                if(!s){
                 throw new BusinessException("????????????");
                }
            }
            </#if>
            //??????
            int n = getBaseMapper().insert(cdto);
            if (n<=0) {
                throw new BusinessException("????????????");
            }
        }
        return Result.success("????????????");
    }
    </#if>

    /**
    * ??????${functionName}??????
    *
    * @param  dto
    * @return Result
    */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result saveData(${className}SaveDto dto) {
        <#if isCategory == "2">
        //????????????ID????????????
        if(!dto.get${parentKey?cap_first}().equals("0")){
            ${className} p${className?cap_first} = this.getById(dto.get${parentObj.javaField?cap_first}());
            if(null == p${className?cap_first}){
                return Result.fail("????????????,??????????????????");
            }
        }
        </#if>
        <#list columns as field>
        <#if field.isKey == "1">
        //??????????????????
        if (null != dto.get${field.javaField?cap_first}() && StringUtils.isNotBlank(dto.get${field.javaField?cap_first}())) {
            return Result.fail("????????????");
        }

        </#if>
        <#if field.isOnly !="0">
        //??????${field.columnComment}??????????????????
        int ${field.javaField}Count = this.getBaseMapper().checker${field.javaField?cap_first}Only(dto);
        if (${field.javaField}Count > 0) {
           return Result.fail("????????????,${field.columnComment}?????????");
        }
        </#if>
        </#list>
        //????????????
        ${className} cdto = new ${className}();
        BeanUtils.copyProperties(dto, cdto);
        <#if subTable??>
        //????????????
        if(dto.get${subTable.className}s().size()>0){
            cdto.set${keyLabel?cap_first}(IdWorker.getIdStr());
            List<${subTable.className}> sonDto = BeanArrayUtils.toBeanList(dto.get${subTable.className}s(),${subTable.className}.class);
            sonDto.forEach(item ->{
            item.set<#list subTable.columns as item><#if subKey == item.columnId>${item.javaField?cap_first}</#if></#list>(cdto.get${keyLabel?cap_first}());
            });
            boolean s = this.${subTable.className?uncap_first}Service.saveBatch(sonDto);
            if(!s){
                return Result.fail("????????????");
            }
        }
        </#if>
        //??????
        int n = getBaseMapper().insert(cdto);
        if (n>0) {
           return Result.success("????????????");
        }
        throw new BusinessException("????????????");
    }

    /**
    * ??????${functionName}??????
    *
    * @param dto
    * @return Result
    */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result updateData(${className}SaveDto dto) {
        <#if isCategory == "2">
        //????????????ID????????????
        if(!dto.get${parentObj.javaField?cap_first}().equals("0")){
        ${className} p${className?cap_first} = this.getById(dto.get${parentObj.javaField?cap_first}());
            if(null == p${className?cap_first}){
                return Result.fail("????????????,??????????????????");
            }
        }
        </#if>
        <#list columns as field>
        <#if field.isKey == "1">
        //??????????????????
        ${className} ${className?uncap_first} = this.getById(dto.get${field.javaField?cap_first}());
        if(null ==${className?uncap_first}){
            return Result.fail("????????????,???????????????");
        }
        </#if>
        <#if field.isOnly !="0">
        //??????${field.columnComment}??????????????????
        if(!(dto.get${field.javaField?cap_first}().equals(${className?uncap_first}.get${field.javaField?cap_first}()))){
            int ${field.javaField}Count = this.getBaseMapper().checker${field.javaField?cap_first}Only(dto);
            if (${field.javaField}Count > 0) {
                return Result.fail("${field.columnComment}?????????");
            }
        }
            </#if>
        </#list>
        //????????????
        BeanUtils.copyProperties(dto, ${className?uncap_first});
        <#if subTable??>
        //????????????
        LambdaQueryWrapper<${subTable.className}> lqw = new LambdaQueryWrapper<>();
        lqw.eq(${subTable.className}::get<#list subTable.columns as item><#if subKey == item.columnId>${item.javaField?cap_first}</#if></#list>,${className?uncap_first}.get${keyLabel?cap_first}());
        this.${subTable.className?uncap_first}Service.remove(lqw);
        if(dto.get${subTable.className}s().size()>0){
            List<${subTable.className}> sonDto = BeanArrayUtils.toBeanList(dto.get${subTable.className}s(),${subTable.className}.class);
            sonDto.forEach(item ->{
                item.set<#list subTable.columns as item><#if subKey == item.columnId>${item.javaField?cap_first}</#if></#list>(${className?uncap_first} .get${keyLabel?cap_first}());
            });
            boolean s = this.${subTable.className?uncap_first}Service.saveBatch(sonDto);
            if(!s){
                return Result.fail("????????????");
            }
        }
        </#if>
        //??????
        int n = getBaseMapper().updateById(${className?uncap_first});
        if (n>0) {
            return Result.success("????????????");
        }
        throw new BusinessException("????????????");
    }

    /**
    * ??????${functionName}??????
    *
    * @param
    * @return Result
    */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result deleteData(String id) {
    <#if isCategory == "2">
        int n = this.getBaseMapper().selectByParendId(id);
        if(n>0) {
        return Result.fail("????????????,???${functionName}???????????????");
        }
    </#if>
        int flag = this.getBaseMapper().deleteById(id);
        if (flag > 0) {
    <#if subTable??>
        LambdaQueryWrapper<${subTable.className}> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.in(${subTable.className}::get<#list subTable.detailColumns as field><#if field.columnId == subKey>${field.javaField?cap_first}</#if></#list>,id);
        ${subTable.className?uncap_first}Service.remove(queryWrapper);
    </#if>
        return Result.success("????????????");
        }
        throw new BusinessException("????????????");
    }
    <#if isCategory != "2">
    /**
    * ????????????${functionName}??????
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
            <#if subTable??>
            LambdaQueryWrapper<${subTable.className}> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.in(${subTable.className}::get<#list subTable.detailColumns as field><#if field.columnId == subKey>${field.javaField?cap_first}</#if></#list>,ids);
            ${subTable.className?uncap_first}Service.remove(queryWrapper);
            </#if>
            return Result.success("????????????");
        }
        throw new BusinessException("????????????");
    }
    </#if>


}
