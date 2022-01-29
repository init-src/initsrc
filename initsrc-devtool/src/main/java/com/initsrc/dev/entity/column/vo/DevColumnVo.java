package com.initsrc.dev.entity.column.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@ApiModel(value="DevColumnVo数据表列表对象", description="数据表列表对象")
@Data
public class DevColumnVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "数据库表列主键")
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
    private String isSearch;

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

}
