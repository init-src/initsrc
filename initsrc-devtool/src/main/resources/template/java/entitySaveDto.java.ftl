package ${packageName}.${moduleName}.entity.${bizName}.dto;

<#list importSavePackages as pkg>
import ${pkg}
</#list>
<#if isExcel == "1">
import cn.afterturn.easypoi.excel.annotation.Excel;
</#if>
import com.initsrc.common.base.BaseEntity;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
* <p>
* ${tableComment!}-更新或编辑对象
* </p>
*
* @author ${genAuthor}
* @since  ${date?string("yyyy-MM-dd HH:mm:ss")}
*/

@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(value="${className}SaveDto更新或编辑对象", description="更新或编辑对象")
public class ${className}SaveDto extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    <#list saveColumns as field>
    @ApiModelProperty(value = "${field.columnComment}")
    <#if field.isRequired=='1'>
    <#if field.javaType != "String">
    @NotNull(message = "${field.columnComment}不能为空")
    </#if>
    <#if field.javaType == "String">
    @NotBlank(message = "${field.columnComment}不能为空")
    </#if>
    </#if>
    <#if isExcel == '1'>
    <#if field.isKey != '1'>
    @Excel(name = "${field.columnComment}", orderNum = "${field_index+1}" , width = 20 <#if field.javaType == 'Date'> , format = "yyyy-MM-dd HH:mm:ss"</#if><#if field.isSearch == '0'><#if field.dictType??>, replace = {<#list dictMap[field.dictType] as item>"${item.label}_${item.key}"<#if item_has_next>,</#if></#list>}</#if></#if>)
    </#if>
    </#if>
    private ${field.javaType} ${field.javaField};

    </#list>
    <#if subTable??>
    @ApiModelProperty(value = "${subTable.functionName}子表")
    private List<${subTable.className}> ${subTable.className?uncap_first}s;
    </#if>
}
