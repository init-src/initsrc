package ${packageName}.${moduleName}.entity.${bizName}.dto;

<#list importQueryPackages as pkg>
import ${pkg}
</#list>
import com.initsrc.common.base.BaseSearchEntity;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
* <p>
* ${tableComment!}-列表查询对象
* </p>
*
* @author ${genAuthor}
* @since  ${date?string("yyyy-MM-dd HH:mm:ss")}
*/

@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(value="${className}QueryDto列表查询对象", description="列表查询对象")
public class ${className}QueryDto extends BaseSearchEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    <#list queryColumns as field>
    <#if field.queryType != "8">
    @ApiModelProperty(value = "${field.columnComment}")
    private ${field.javaType} ${field.javaField};
    <#else >
    @ApiModelProperty(value = "${field.columnComment}开始时间")
    private ${field.javaType} begin${field.javaField?cap_first};

    @ApiModelProperty(value = "${field.columnComment}结束时间")
    private ${field.javaType} end${field.javaField?cap_first};

    </#if>

    </#list>
}
