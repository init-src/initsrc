package ${packageName}.${moduleName}.entity.${bizName}.vo;

import com.initsrc.common.base.NodeEntity;
import java.util.List;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
* <p>
    * ${tableComment!}-下拉选择对象
    * </p>
*
* @author ${genAuthor}
* @since  ${date?string("yyyy-MM-dd HH:mm:ss")}
*/

@Data
@Accessors(chain = true)
@ApiModel(value="${className}SelectVo详情对象", description="详情对象")
public class ${className}SelectVo implements Serializable {

    private static final long serialVersionUID = 1L;

    <#list columns as field>
    <#if field.isSearch == '1'>
    @ApiModelProperty(value = "${field.columnName}字段关联查询列表")
    List<NodeEntity> ${field.javaField}List;

    </#if>
    </#list>
}
