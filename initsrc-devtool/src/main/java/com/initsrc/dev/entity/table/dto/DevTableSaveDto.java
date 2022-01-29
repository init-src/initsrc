package com.initsrc.dev.entity.table.dto;

import com.initsrc.common.base.BaseEntity;
import com.initsrc.dev.entity.column.vo.DevColumnVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 数据库表结构-更新或编辑对象
 * </p>
 *
 * @author INITSRC
 * @since  2021-02-02 15:30:14
 */

@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(value="GenTableSaveDto更新或编辑对象", description="更新或编辑对象")
public class DevTableSaveDto extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "数据库表主键")
    private String tableId;

    @ApiModelProperty(value = "菜单父类id")
    private String permId;

    @ApiModelProperty(value = "数据库表名称")
    @NotBlank(message = "数据库表名称不能为空")
    private String tableName;

    @ApiModelProperty(value = "数据库表描述")
    @NotBlank(message = "数据库表描述不能为空")
    private String tableComment;

    @ApiModelProperty(value = "实体类名称")
    @NotBlank(message = "实体类名称不能为空")
    private String className;

    @ApiModelProperty(value = "生成模板类型 1：正常 2：树形 ")
    private String isCategory;

    @ApiModelProperty(value = "父类字段")
    private String parentKey;

    @ApiModelProperty(value = "是否主子关联 （0：否 1：是）")
    private String isRef;

    @ApiModelProperty(value = "是否需导入导出（0：不需要 1：需要）")
    private String isExcel;

    @ApiModelProperty(value = "页面路径")
    @NotBlank(message = "页面路径不能为空")
    private String viewPath;

    @ApiModelProperty(value = "包名路径")
    @NotBlank(message = "包名路径不能为空")
    private String packageName;

    @ApiModelProperty(value = "模块名称")
    @NotBlank(message = "模块名称不能为空")
    private String moduleName;

    @ApiModelProperty(value = "业务名称")
    @NotBlank(message = "业务名称不能为空")
    private String bizName;

    @ApiModelProperty(value = "功能名称")
    @NotBlank(message = "功能名称不能为空")
    private String functionName;

    @ApiModelProperty(value = "作者名称")
    @NotBlank(message = "作者名称不能为空")
    private String genAuthor;

    @ApiModelProperty(value = "子关联表id")
    private String subTableId;

    @ApiModelProperty(value = "子关联表key")
    private String subKey;

    @ApiModelProperty(value = "左边列表/树形关联字段")
    private String columnKey;

    @ApiModelProperty(value = "列表页类型 1：正常列表 2：左侧树形列表（本表）3：左侧树形列表（关联）4：左侧列表（关联）")
    private String vueTableType;

    @ApiModelProperty(value = "编辑页面类型 （1：弹窗 2：抽屉 3：新页面）")
    private String vueEditType;

    @ApiModelProperty(value = "详情页面类型 （1：弹窗 2：抽屉 3：新页面）")
    private String vueDetailType;

    @ApiModelProperty(value = "表字段信息")
    private List<DevColumnVo> columns;

}
