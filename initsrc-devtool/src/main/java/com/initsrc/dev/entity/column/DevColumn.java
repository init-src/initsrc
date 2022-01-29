package com.initsrc.dev.entity.column;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * <p>
 * 数据库表字段-t_dev_column
 * </p>
 *
 * @author INITSRC
 * @since  2021-01-28 11:01:36
 */

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("is_dev_column")
@ApiModel(value="DevColumn对象", description="")
public class DevColumn {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "数据库表列主键")
    @TableId(value = "column_id", type = IdType.ASSIGN_ID)
    private String columnId;

    @ApiModelProperty(value = "数据库表ID")
    private String tableId;

    @ApiModelProperty(value = "列名称")
    private String columnName;

    @ApiModelProperty(value = "列描述")
    private String columnComment;

    @ApiModelProperty(value = "列类型")
    private String columnType;

    @ApiModelProperty(value = "列类型")
    private String columnTypeName;

    @ApiModelProperty(value = "JAVA类型")
    private String javaType;

    @ApiModelProperty(value = "JAVA字段名")
    private String javaField;

    @ApiModelProperty(value = "是否主键字段 （0：否 1：是）")
    private String isKey;

    @ApiModelProperty(value = "是否自增长（0：否 1：是）")
    private String isIncrement;

    @ApiModelProperty(value = "是否列表字段（0：否 1：是）")
    private String isList;

    @ApiModelProperty(value = "是否插入字段（0：否 1：是）")
    private String isInsert;

    @ApiModelProperty(value = "是否编辑字段（0：否 1：是）")
    private String isEdit;

    @ApiModelProperty(value = "是否必填字段（0：否 1：是）")
    private String isRequired;

    @ApiModelProperty(value = "是否详情字段（0：否 1：是）")
    private String isDetail;

    @ApiModelProperty(value = "是否查询字段（0：否 1：是）")
    private String isQuery;

    @ApiModelProperty(value = "查询方式（1：EQ等于、2：NE不等于、3：GT大于、4：LT小于、5：LIKE模糊、6：BETWEEN范围）")
    private String queryType;

    @ApiModelProperty(value = "是否唯一字段（0：否 1：是）")
    private String isOnly;

    @ApiModelProperty(value = "正则方法名")
    private String verifyName;

    @ApiModelProperty(value = "是否幂等字段（0：否 1：是）")
    private String isVersion;

    @ApiModelProperty(value = "是否插入填充字段（0：否 1：是）")
    private String isFillCreate;

    @ApiModelProperty(value = "是否更新填充字段（0：否 1：是）")
    private String isFillUpdate;

    @ApiModelProperty(value = "是否逻辑删除字段（0：否 1：是）")
    private String isTableLogic;

    @ApiModelProperty(value = "显示类型 (1:文本框 2：文本域 3：数字文本框 4：浮点型文本框 5：富文本框 6：日期选择器 7：图片选择器 8：文件选择器 9：选择器 10：远程选择器 11：树形选择器 12：复选框 13：单选框 )")
    private String htmlType;

    @ApiModelProperty(value = "查询方式（0：字典 1：数据库表）")
    private Integer isSearch;

    @ApiModelProperty(value = "字典类型key")
    private String dictType;

    @ApiModelProperty(value = "关联表名")
    private String leftTable;

    @ApiModelProperty(value = "关联表主键")
    private String leftKey;

    @ApiModelProperty(value = "关联表父类")
    private String leftParent;

    @ApiModelProperty(value = "关联表显示字段")
    private String leftLabel;

    @ApiModelProperty(value = "关联表显示别名")
    private String leftAlias;

    @ApiModelProperty(value = "排序")
    private Integer sort;

    @ApiModelProperty(value = "删除标识 （0：正常 1：删除）")
    private Integer dr;

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
