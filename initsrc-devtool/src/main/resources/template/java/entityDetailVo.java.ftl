package ${packageName}.${moduleName}.entity.${bizName}.vo;

<#list importDetailPackages as pkg>
import ${pkg}
</#list>
<#if subTable??>
import ${subTable.packageName}.${subTable.moduleName}.entity.${subTable.bizName}.${subTable.className};
</#if>
import com.initsrc.common.base.NodeEntity;
import java.util.List;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
* <p>
* ${tableComment!}-详情对象
* </p>
*
* @author ${genAuthor}
* @since  ${date?string("yyyy-MM-dd HH:mm:ss")}
*/

@Data
@Accessors(chain = true)
@ApiModel(value="${className}DetailVo详情对象", description="详情对象")
public class ${className}DetailVo implements Serializable {

    private static final long serialVersionUID = 1L;

    <#list detailColumns as field>
    @ApiModelProperty(value = "${field.columnComment}")
    private ${field.javaType} ${field.javaField};

    <#if field.isSearch == '1'>
    @ApiModelProperty(value = "${field.columnComment}")
    private String ${field.leftAlias};

    </#if>
    </#list>
    <#if subTable??>
    @ApiModelProperty(value = "${subTable.functionName}子表")
    private List<${subTable.className}> ${subTable.className?uncap_first}s;
    </#if>
}
