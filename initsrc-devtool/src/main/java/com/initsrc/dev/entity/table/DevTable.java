package com.initsrc.dev.entity.table;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 数据库表-is_dev_table
 * </p>
 *
 * @author 启源（INITSRC）
 * @since  2021-05-10 01:46:31
 */

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("is_dev_table")
@ApiModel(value="DevTable对象", description="")
public class DevTable implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "数据库表主键")
    @TableId(value = "table_id", type = IdType.ASSIGN_ID)
    private String tableId;

    @ApiModelProperty(value = "数据库表名称")
    private String tableName;

    @ApiModelProperty(value = "数据库表描述")
    private String tableComment;

    @ApiModelProperty(value = "实体类名称")
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
    private String viewPath;

    @ApiModelProperty(value = "包名路径")
    private String packageName;

    @ApiModelProperty(value = "模块名称")
    private String moduleName;

    @ApiModelProperty(value = "业务名称")
    private String bizName;

    @ApiModelProperty(value = "功能名称")
    private String functionName;

    @ApiModelProperty(value = "作者名称")
    private String genAuthor;

    @ApiModelProperty(value = "子关联表id")
    private String subTableId;

    @ApiModelProperty(value = "子关联表key")
    private String subKey;

    @ApiModelProperty(value = "列表页类型 1：正常列表 2：左侧列表")
    private String vueTableType;

    @ApiModelProperty(value = "左侧关联字段")
    private String columnKey;

    @ApiModelProperty(value = "编辑页面类型 （1：弹窗 2：抽屉 3：新页面）")
    private String vueEditType;

    @ApiModelProperty(value = "详情页面类型 （1：弹窗 2：抽屉 3：新页面）")
    private String vueDetailType;

    @ApiModelProperty(value = "菜单父类ID")
    private String permId;

    @ApiModelProperty(value = "删除标识 （0：删除 1：正常）")
    @TableLogic
    private String dr;

    @ApiModelProperty(value = "幂等")
    @Version
    @TableField(fill = FieldFill.INSERT)
    private Integer version;

    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    @TableField(fill = FieldFill.UPDATE)
    private Date updateTime;

    @ApiModelProperty(value = "创建人")
    @TableField(fill = FieldFill.INSERT)
    private String createBy;

    @ApiModelProperty(value = "更新人")
    @TableField(fill = FieldFill.UPDATE)
    private String updateBy;

}
