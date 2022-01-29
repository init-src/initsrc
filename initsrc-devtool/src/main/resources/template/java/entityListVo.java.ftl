package ${packageName}.${moduleName}.entity.${bizName}.vo;

<#list importListPackages as pkg>
import ${pkg}
</#list>
<#if isExcel == "1">
import cn.afterturn.easypoi.excel.annotation.Excel;
</#if>
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
* <p>
* ${tableComment!}-列表对象
* </p>
*
* @author ${genAuthor}
* @since  ${date?string("yyyy-MM-dd HH:mm:ss")}
*/

@Data
@Accessors(chain = true)
@ApiModel(value="${className}ListVo列表对象", description="列表对象")
public class ${className}ListVo implements Serializable {

    private static final long serialVersionUID = 1L;

    <#list listColumns as field>
    @ApiModelProperty(value = "${field.columnComment}")
    <#if isExcel == '1'>
    @Excel(name = "${field.columnComment}", orderNum = "${field_index+1}" , width = 20 <#if field.javaType == 'Date'> , format = "yyyy-MM-dd HH:mm:ss"</#if><#if field.isSearch == '0'><#if field.dictType??>, replace = {<#list dictMap[field.dictType] as item>"${item.label}_${item.key}"<#if item_has_next>,</#if></#list>}</#if></#if>)
    </#if>
    private ${field.javaType} ${field.javaField};

    <#if field.isSearch == '1'>
    @ApiModelProperty(value = "${field.columnComment}")
    <#if isExcel == '1'>
    @Excel(name = "${field.columnComment}", orderNum = "${field_index+1}" , width = 20)
    </#if>
    private String ${field.leftAlias};

    </#if>
    </#list>
}
