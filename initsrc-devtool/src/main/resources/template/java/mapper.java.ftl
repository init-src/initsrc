package ${packageName}.${moduleName}.dao;

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
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import java.util.List;

/**
* <p>
    * ${functionName}-服务类
    * </p>
*
* @author ${genAuthor}
* @since  ${date?string("yyyy-MM-dd HH:mm:ss")}
*/
public interface ${className}Mapper extends BaseMapper<${className}>{

    //查询${functionName}列表
    List<${className}ListVo> findList(${className}QueryDto dto);

    //查询${functionName}详情
    ${className}DetailVo selectDetailById(String id);

    <#list columns as field>
    <#if field.isOnly !="0">
    //验证${field.columnComment}字段是否存在
    int checker${field.javaField?cap_first}Only(${className}SaveDto dto);

    </#if>
    </#list>
    <#if isCategory == "2">
    //根据父类ID获取子类数量
    int selectByParendId(String id);

    </#if>
    <#list columns as field>
    <#if field.isSearch == '1'>
    // ${field.columnName}字段关联查询列表
    List<NodeEntity> ${field.javaField}SelectData(BaseEntity dto);

    </#if>
    </#list>
}
