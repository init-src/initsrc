package ${packageName}.${moduleName}.entity.${bizName}.vo;

<#list importPackages as pkg>
import ${pkg}
</#list>
<#list importMpPackages as pkg>
import ${pkg};
</#list>
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
* <p>
    * ${tableComment!}-${tableName}
    * </p>
*
* @author ${genAuthor}
* @since  ${date?string("yyyy-MM-dd HH:mm:ss")}
*/

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("${tableName}")
@ApiModel(value="${className}对象", description="")
public class ${className} implements Serializable {

    private static final long serialVersionUID = 1L;

    <#list columns as field>
    @ApiModelProperty(value = "${field.columnComment}")
    <#if field.isKey=='1'>
    <#if field.isIncrement=='1'>
    @TableId(value = "${field.columnName}", type = IdType.AUTO)
    <#else >
    @TableId(value = "${field.columnName}", type = IdType.ASSIGN_ID)
    </#if>
    </#if>
    <#if field.isVersion??>
    <#if field.isVersion=='1'>
    @Version
    @TableField(fill = FieldFill.INSERT)
    </#if>
    </#if>
    <#if field.isFillUpdate??>
    <#if field.isFillUpdate='1'>
    @TableField(fill = FieldFill.UPDATE)
    </#if>
    </#if>
    <#if field.isFillCreate??>
    <#if field.isFillCreate=='1'>
    @TableField(fill = FieldFill.INSERT)
    </#if>
    </#if>
    <#if field.isTableLogic??>
    <#if field.isTableLogic=='1'>
    @TableLogic
    </#if>
    </#if>
    private ${field.javaType} ${field.javaField};

</#list>
}
