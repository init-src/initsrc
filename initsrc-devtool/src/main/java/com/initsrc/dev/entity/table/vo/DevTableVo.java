package com.initsrc.dev.entity.table.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.initsrc.common.enums.HtmlTypeEnum;
import com.initsrc.dev.entity.column.vo.DevColumnVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.*;

@Data
@ApiModel(value = "数据库表对象", description = "数据库表对象")
public class DevTableVo implements Serializable {
    private static final long serialVersionUID = 1L;

    //MB包路径
    private final Set<String> importMpPackages = new HashSet();

    //普通包路径
    private final Set<String> importPackages = new HashSet();

    //详情包路径
    private final Set<String> importDetailPackages = new HashSet();

    //列表包路径
    private final Set<String> importListPackages = new HashSet();

    //列表条件包路径
    private final Set<String> importQueryPackages = new HashSet();

    //保存包路径
    private final Set<String> importSavePackages = new HashSet();

    @ApiModelProperty(value = "数据库表主键")
    private String tableId;

    @ApiModelProperty(value = "主键JAVA类型")
    private String keyLabel;

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

    @ApiModelProperty(value = "左侧关联字段")
    private String columnKey;

    @ApiModelProperty(value = "左侧关联字段文本")
    private String columnLabel;

    @ApiModelProperty(value = "左边列表/树形关联字段")
    private String leftColumn;

    @ApiModelProperty(value = "列表页类型 1：正常列表 2：左侧列表")
    private String vueTableType;

    @ApiModelProperty(value = "编辑页面类型 （1：弹窗 2：抽屉 3：新页面）")
    private String vueEditType;

    @ApiModelProperty(value = "详情页面类型 （1：弹窗 2：抽屉 3：新页面）")
    private String vueDetailType;

    @ApiModelProperty(value = "菜单父类id")
    private String permId;

    @ApiModelProperty(value = "ID数组")
    private List<String> ids = new ArrayList<>();

    @ApiModelProperty(value = "正则方法名")
    private String verifyName;

    @ApiModelProperty(value = "是否查询列表")
    private String isSelect = "0";

    @ApiModelProperty(value = "日期")
    private Date date = new Date();

    @ApiModelProperty(value = "实体对象")
    private List<DevColumnVo> columns;

    @ApiModelProperty(value = "列表对象")
    private List<DevColumnVo> listColumns = new ArrayList<>();

    @ApiModelProperty(value = "详情对象")
    private List<DevColumnVo> detailColumns = new ArrayList<>();

    @ApiModelProperty(value = "新增和保存对象")
    private List<DevColumnVo> saveColumns = new ArrayList<>();

    @ApiModelProperty(value = "查询条件对象")
    private List<DevColumnVo> queryColumns = new ArrayList<>();

    @ApiModelProperty(value = "父类字段")
    private DevColumnVo parentObj;

    @ApiModelProperty(value = "主子表")
    private DevTableVo subTable;

    @ApiModelProperty(value = "字典数据")
    private Map<String,Object> dictMap;

    public DevTableVo setColumns(List<DevColumnVo> columns) {
        if (CollectionUtils.isNotEmpty(columns)) {
            this.columns = columns;
            for (DevColumnVo field : columns) {
                //查询列表
                if (field.getIsList().equals("1")) {
                    listColumns.add(field);
                    if (null != field.getJavaType()) {
                        if (StringUtils.isNotBlank(fromValue(field.getJavaType()))) {
                            this.importListPackages.add(fromValue(field.getJavaType()));
                        }
                    }
                }
                //编辑或保存列表
                if (field.getIsEdit().equals("1") || field.getIsInsert().equals("1")) {
                    saveColumns.add(field);
                    if (field.getIsRequired().equals("1")) {
                        this.importSavePackages.add("javax.validation.constraints.*;");
                    }
                    if (null != field.getJavaType()) {
                        if (StringUtils.isNotBlank(fromValue(field.getJavaType()))) {
                            this.importSavePackages.add(fromValue(field.getJavaType()));
                        }
                    }
                }
                //详情列表
                if (field.getIsDetail().equals("1")) {
                    detailColumns.add(field);
                    if (null != field.getJavaType()) {
                        if (StringUtils.isNotBlank(fromValue(field.getJavaType()))) {
                            this.importDetailPackages.add(fromValue(field.getJavaType()));
                        }
                    }
                }
                //列表条件列表
                if (field.getIsQuery().equals("1")) {
                    queryColumns.add(field);
                    if (null != field.getJavaType()) {
                        if (StringUtils.isNotBlank(fromValue(field.getJavaType()))) {
                            this.importQueryPackages.add(fromValue(field.getJavaType()));
                        }
                    }
                }
                //包名转换
                if (null != field.getJavaType()) {
                    if (StringUtils.isNotBlank(fromValue(field.getJavaType()))) {
                        this.importPackages.add(fromValue(field.getJavaType()));
                    }
                }
                //MB的包
                if (field.getIsKey().equals("1")) {
                    this.importMpPackages.add(TableId.class.getCanonicalName());
                    this.importMpPackages.add(IdType.class.getCanonicalName());
                }
                if (field.getIsTableLogic().equals("1")) {
                    this.importMpPackages.add(com.baomidou.mybatisplus.annotation.TableLogic.class.getCanonicalName());
                }
                if (field.getIsVersion().equals("1")) {
                    this.importMpPackages.add(com.baomidou.mybatisplus.annotation.Version.class.getCanonicalName());
                }
                if (field.getIsFillCreate().equals("1") || field.getIsFillUpdate().equals("1")) {
                    this.importMpPackages.add(com.baomidou.mybatisplus.annotation.TableField.class.getCanonicalName());
                    this.importMpPackages.add(com.baomidou.mybatisplus.annotation.FieldFill.class.getCanonicalName());
                }
                if (field.getIsTableLogic().equals("1")) {
                    this.importMpPackages.add(com.baomidou.mybatisplus.annotation.TableLogic.class.getCanonicalName());
                }
                //是否列表查询
                if(Integer.valueOf(field.getHtmlType()) >= Integer.valueOf(HtmlTypeEnum.SELECT.getValue()) && Integer.valueOf(field.getHtmlType()) <=Integer.valueOf(HtmlTypeEnum.RADIO.getValue())){
                    if(field.getIsSearch().equals("1")){
                        this.isSelect = "1";
                    }
                }
                //判断父类字段对象
                if(field.getColumnName().equals(this.parentKey)){
                    this.parentObj = field;
                }
                //主键JAVA字段
                if(field.getIsKey().equals("1")){
                    this.keyLabel = field.getJavaField();
                }
                ids.add(IdWorker.getIdStr());
                ids.add(IdWorker.getIdStr());
                ids.add(IdWorker.getIdStr());
                ids.add(IdWorker.getIdStr());
                ids.add(IdWorker.getIdStr());
                ids.add(IdWorker.getIdStr());
                ids.add(IdWorker.getIdStr());
            }
        }
        return this;
    }

    /**
     * 包名转换
     * @param value
     * @return
     */
    public static String fromValue(String value) {
        for (DbColumnType val : DbColumnType.values()) {
            if (value.equals("Date")) {
                return "java.util.Date;";
            } else if (val.getType().equals(value)) {
                if(StringUtils.isNotBlank(val.getPkg())){
                    return val.getPkg()+";";
                }
            }
        }
        return null;
    }
}
