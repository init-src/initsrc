package ${packageName}.${moduleName}.service;

import ${packageName}.${moduleName}.entity.${bizName}.${className};
import ${packageName}.${moduleName}.entity.${bizName}.vo.${className}ListVo;
import ${packageName}.${moduleName}.entity.${bizName}.vo.${className}DetailVo;
import ${packageName}.${moduleName}.entity.${bizName}.dto.${className}QueryDto;
import ${packageName}.${moduleName}.entity.${bizName}.dto.${className}SaveDto;
<#if isSelect == '1' || isCategory == "2">
import com.initsrc.common.base.NodeEntity;
import com.initsrc.common.base.BaseEntity;
import ${packageName}.${moduleName}.entity.${bizName}.vo.${className}SelectVo;
</#if>
<#if isExcel == "1">
import javax.servlet.http.HttpServletResponse;
import com.initsrc.common.base.BaseEntity;
import org.springframework.web.multipart.MultipartFile;
</#if>
import com.baomidou.mybatisplus.extension.service.IService;
import com.initsrc.common.base.Result;
import java.util.List;

/**
* <p>
* ${functionName}-服务类
* </p>
*
* @author ${genAuthor}
* @since  ${date?string("yyyy-MM-dd HH:mm:ss")}
*/
public interface ${className}Service extends IService<${className}>{

    //查询${functionName}列表
    List<${className}ListVo> pageData(${className}QueryDto dto);

    <#if vueTableType =='2'>
    //查询${functionName}左侧列表
    List<NodeEntity> leftData(BaseEntity dto);

    </#if>
    <#if isSelect == '1'>
    //查询${functionName}下拉框列表
    ${className}SelectVo selectData(BaseEntity dto);

    </#if>
    <#if isExcel == "1">
    //导出${functionName}数据
    void export(HttpServletResponse response, ${className}QueryDto dto);

    //${functionName}导入
    Result<String> batchImport(MultipartFile file, BaseEntity dto, HttpServletResponse response) throws Exception;

    </#if>
    //查询${functionName}详情
    Result<${className}DetailVo> detail(String id);

    //保存${functionName}数据
    Result saveData(${className}SaveDto dto);

    //更新${functionName}数据
    Result updateData(${className}SaveDto dto);

    //删除${functionName}数据
    Result deleteData(String id);

    <#if isCategory != "2">
    // 批量删除${functionName}数据
    Result deletesData(List<String> ids);
    </#if>

}
