package com.initsrc.dev.entity.table.vo;

import com.initsrc.common.base.NodeEntity;
import com.initsrc.dev.entity.column.vo.DevColumnVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;
/**
 * <p>
 * 数据库表结构-详情对象
 * </p>
 *
 * @author INITSRC
 * @since  2021-01-26 17:11:46
 */

@Data
@Accessors(chain = true)
@ApiModel(value="DevTableDetailVo详情对象", description="详情对象")
public class DevTableDetailVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "数据库表主键")
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

    @ApiModelProperty(value = "正则方法名")
    private String verifyName;

    @ApiModelProperty(value = "左侧关联字段")
    private String columnKey;

    @ApiModelProperty(value = "列表页类型 1：正常列表 2：左侧列表")
    private String vueTableType;

    @ApiModelProperty(value = "编辑页面类型 （1：弹窗 2：抽屉 3：新页面）")
    private String vueEditType;

    @ApiModelProperty(value = "详情页面类型 （1：弹窗 2：抽屉 3：新页面）")
    private String vueDetailType;

    @ApiModelProperty(value = "菜单父类ID")
    private String permId;

    @ApiModelProperty(value = "子节点")
    private List<DevColumnVo> columns;

    @ApiModelProperty(value = "所有表格")
    private List<DevTableDetailVo> tables;

    @ApiModelProperty(value = "菜单权限")
    private List<NodeEntity> permList;

}
