package com.initsrc.dev.util;

import cn.hutool.core.text.StrFormatter;
import com.initsrc.common.enums.ConversionTypeEnum;
import com.initsrc.common.enums.HtmlTypeEnum;
import com.initsrc.dev.entity.column.DevColumn;
import com.initsrc.dev.entity.table.vo.DevTableVo;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.*;

/**
 * <p>
 * 生成工具转换
 * </p>
 *
 * @author INITSRC
 * @since  2021-01-26 17:11:46
 */
public class GenUtils {
    /** 项目空间路径 */
    private static final String PROJECT_PATH = "main/java";

    /** mybatis空间路径 */
    private static final String MYBATIS_PATH = "main/resources/mapper";


    //驼峰命名
    public static String camelCaseName(String underscoreName) {
        StringBuilder result = new StringBuilder();
        if (underscoreName != null && underscoreName.length() > 0) {
            boolean flag = false;
            for (int i = 0; i < underscoreName.length(); i++) {
                char ch = underscoreName.charAt(i);
                if ("_".charAt(0) == ch) {
                    flag = true;
                } else {
                    if (flag) {
                        result.append(Character.toUpperCase(ch));
                        flag = false;
                    } else {
                        result.append(ch);
                    }
                }
            }
        }
        return result.toString();

    }

    //类型转换
    public static void ConversionType(DevColumn genColumn) {
        genColumn.setJavaType(GenUtils.fromValue(genColumn.getColumnType()));
        genColumn.setJavaField(GenUtils.camelCaseName(genColumn.getColumnName()));

        //默认文本框
        String htmlType = HtmlTypeEnum.INPUT.getValue();
        switch (genColumn.getColumnType()){
            case "bigint":
                htmlType = HtmlTypeEnum.INPUT.getValue();
                break;
            case "tinyint":
                htmlType = HtmlTypeEnum.RADIO.getValue();
                break;
            case "int":
                htmlType = HtmlTypeEnum.INPUTNUM.getValue();
                break;
            case "double":
                htmlType = HtmlTypeEnum.INPUTDOUBLE.getValue();
                break;
            case "float":
                htmlType = HtmlTypeEnum.INPUTDOUBLE.getValue();
                break;
            case "decimal":
                htmlType = HtmlTypeEnum.INPUTDOUBLE.getValue();
                break;
            case "date":
                htmlType = HtmlTypeEnum.DATEPICKER.getValue();
                break;
            case "time":
                htmlType = HtmlTypeEnum.DATEPICKER.getValue();
                break;
            case "datetime":
                htmlType = HtmlTypeEnum.DATEPICKER.getValue();
                break;
            case "text":
                htmlType = HtmlTypeEnum.TEXTAREA.getValue();
                break;
            case "longtext":
                htmlType = HtmlTypeEnum.TEXTAREA.getValue();
                break;
            case "mediumtext":
                htmlType = HtmlTypeEnum.TEXTAREA.getValue();
                break;
        }
        genColumn.setHtmlType(htmlType);
        if(genColumn.getJavaField().equals("dr")){
            genColumn.setIsTableLogic("1");
            genColumn.setHtmlType(HtmlTypeEnum.INPUT.getValue());
            genColumn.setIsInsert("0");
            genColumn.setIsEdit("0");
            genColumn.setIsList("0");
            genColumn.setIsDetail("0");
        }
        if(genColumn.getJavaField().equals("version")){
            genColumn.setVersion(0);
            genColumn.setIsVersion("1");
            genColumn.setIsInsert("0");
            genColumn.setIsEdit("0");
            genColumn.setIsList("0");
            genColumn.setIsDetail("0");
        }
        if(genColumn.getJavaField().equals("createTime") || genColumn.getJavaField().equals("createBy")){
            genColumn.setIsFillCreate("1");
            genColumn.setIsInsert("0");
            genColumn.setIsEdit("0");
            genColumn.setIsList("0");
            genColumn.setIsDetail("0");
        }
        if(genColumn.getJavaField().equals("updateTime") || genColumn.getJavaField().equals("updateBy")){
            genColumn.setIsFillUpdate("1");
            genColumn.setIsInsert("0");
            genColumn.setIsEdit("0");
            genColumn.setIsList("0");
            genColumn.setIsDetail("0");
        }
    }

    //sql类型转换java类型
    public static String fromValue(String value) {
        for (ConversionTypeEnum val : ConversionTypeEnum.values()) {
            if (val.getColumnType().equals(value)) {
                return val.getJavaType();
            }
        }
        return "String";
    }

    //生成模板
    public static Map<String, String> initTemplateByNormal(DevTableVo devTableVo) throws IOException, TemplateException {
        if(StringUtils.isNotBlank(devTableVo.getColumnKey())){
            devTableVo.setColumnLabel(GenUtils.camelCaseName(devTableVo.getColumnKey()));
        }
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_22);
        cfg.setDefaultEncoding("UTF-8");
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        cfg.setClassForTemplateLoading(GenUtils.class,"/template");
        Map<String, String> map = new LinkedHashMap<>();
        Map<String, Template> tmap = new LinkedHashMap<>();
        List<Template> templates = new ArrayList<>();
        tmap.put("controller.java",cfg.getTemplate("/java/controller.java.ftl"));
        tmap.put("service.java",cfg.getTemplate("/java/service.java.ftl"));
        tmap.put("serviceImpl.java",cfg.getTemplate("/java/serviceImpl.java.ftl"));
        tmap.put("entity.java",cfg.getTemplate("/java/entity.java.ftl"));
        tmap.put("list.java",cfg.getTemplate("/java/entityListVo.java.ftl"));
        tmap.put("detail.java",cfg.getTemplate("/java/entityDetailVo.java.ftl"));
        tmap.put("query.java",cfg.getTemplate("/java/entityQueryDto.java.ftl"));
        tmap.put("save.java",cfg.getTemplate("/java/entitySaveDto.java.ftl"));
        if(devTableVo.getIsSelect().equals("1")){
            tmap.put("select.java",cfg.getTemplate("/java/entitySelectVo.java.ftl"));
        }
        tmap.put("dao.java",cfg.getTemplate("/java/mapper.java.ftl"));
        tmap.put("mapper.xml",cfg.getTemplate("/xml/mapper.xml.ftl"));
        if(devTableVo.getVueTableType().equals("1")){
            tmap.put("index.vue",cfg.getTemplate("/vue/index.vue.ftl"));
        }else{
            tmap.put("index.vue",cfg.getTemplate("/vue/indexTree.vue.ftl"));
        }
        if(devTableVo.getVueEditType().equals("1")){
            tmap.put("indexEdit.vue",cfg.getTemplate("/vue/indexEdit.vue.ftl"));
        }else if(devTableVo.getVueEditType().equals("2")){
            tmap.put("indexEdit.vue",cfg.getTemplate("/vue/indexDEdit.vue.ftl"));
        }else{
            tmap.put("indexEdit.vue",cfg.getTemplate("/vue/indexPEdit.vue.ftl"));
        }
        if(devTableVo.getVueDetailType().equals("1")){
            tmap.put("indexDetail.vue",cfg.getTemplate("/vue/indexDetail.vue.ftl"));
        }else{
            tmap.put("indexDetail.vue",cfg.getTemplate("/vue/indexPDetail.vue.ftl"));
        }
        if(devTableVo.getIsExcel().equals("1")){
            tmap.put("indexExport.vue",cfg.getTemplate("/vue/indexExport.vue.ftl"));
            tmap.put("indexImport.vue",cfg.getTemplate("/vue/indexImport.vue.ftl"));
        }
        tmap.put("api.js",cfg.getTemplate("/js/api.js.ftl"));
        tmap.put("sql.sql",cfg.getTemplate("/sql/sql.ftl"));
        if (devTableVo.getIsRef().equals("1")) {
            tmap.put("subService.java", cfg.getTemplate("/java/subService.java.ftl"));
            tmap.put("subServiceImpl.java", cfg.getTemplate("/java/subServiceImpl.java.ftl"));
            tmap.put("subEntity.java", cfg.getTemplate("/java/subEntity.java.ftl"));
            tmap.put("subDao.java", cfg.getTemplate("/java/subMapper.java.ftl"));
        }
        for (Map.Entry<String,Template>  entry : tmap.entrySet()) {
            /* Merge data-model with template */
            Writer out = new StringWriter();
            if(entry.getKey().equals("subService.java") || entry.getKey().equals("subEntity.java") || entry.getKey().equals("subServiceImpl.java")
            || entry.getKey().equals("subDao.java")){
                entry.getValue().process(devTableVo.getSubTable(), out);
            }else{
                entry.getValue().process(devTableVo, out);
            }
            map.put(entry.getKey(),out.toString());
            out.flush();
            out.close();
        }
        return map;
    }

    /**
     * 获取文件名
     */
    public static String getFileName(String template, DevTableVo devTableVo){
        // 文件名称
        String fileName = "";
        // 包路径
        String packageName = devTableVo.getPackageName();
        // 模块名
        String moduleName = devTableVo.getModuleName();
        // 大写类名
        String className = devTableVo.getClassName();
        // 业务名称
        String businessName = devTableVo.getBizName();

        String javaPath = PROJECT_PATH + "/" + StringUtils.replace(packageName, ".", "/");
        String mybatisPath = MYBATIS_PATH + "/" + moduleName;
        String vuePath = "vue";
        if(template.equals("controller.java")){
            fileName = StrFormatter.format("{}/{}/controller/{}.java", javaPath,moduleName,className+"Controller");
        }
        if(template.equals("entity.java")){
            fileName = StrFormatter.format("{}/{}/entity/{}/{}.java", javaPath,moduleName,businessName,className);
        }
        if(template.equals("list.java")){
            fileName = StrFormatter.format("{}/{}/entity/{}/vo/{}.java", javaPath,moduleName,businessName,className+"ListVo");
        }
        if(template.equals("detail.java")){
            fileName = StrFormatter.format("{}/{}/entity/{}/vo/{}.java", javaPath,moduleName,businessName,className+"DetailVo");
        }
        if(template.equals("select.java")){
            fileName = StrFormatter.format("{}/{}/entity/{}/vo/{}.java", javaPath,moduleName,businessName,className+"SelectVo");
        }
        if(template.equals("query.java")){
            fileName = StrFormatter.format("{}/{}/entity/{}/dto/{}.java", javaPath,moduleName,businessName,className+"QueryDto");
        }
        if(template.equals("save.java")){
            fileName = StrFormatter.format("{}/{}/entity/{}/dto/{}.java", javaPath,moduleName,businessName,className+"SaveDto");
        }
        if(template.equals("dao.java")){
            fileName = StrFormatter.format("{}/{}/dao/{}.java", javaPath,moduleName,className+"Mapper");
        }
        if(template.equals("service.java")){
            fileName = StrFormatter.format("{}/{}/service/{}.java", javaPath,moduleName,className+"Service");
        }
        if(template.equals("serviceImpl.java")){
            fileName = StrFormatter.format("{}/{}/service/impl/{}.java", javaPath,moduleName,className+"ServiceImpl");
        }
        if(template.equals("mapper.xml")){
            fileName = StrFormatter.format("{}/{}Mapper.xml", mybatisPath, className);
        }
        if(template.equals("index.vue")){
            fileName = StrFormatter.format("{}/index.vue", vuePath);
        }
        if(template.equals("indexEdit.vue")){
            fileName = StrFormatter.format("{}/indexEdit.vue", vuePath);
        }
        if(template.equals("indexDetail.vue")){
            fileName = StrFormatter.format("{}/indexDetail.vue", vuePath);
        }
        if(template.equals("sql.sql")){
            fileName = StrFormatter.format("{}/sql.sql", "sql");
        }
        if(template.equals("api.js")){
            fileName = StrFormatter.format("{}/api.js", "js");
        }
       if(devTableVo.getSubTable() != null){
           String subJavaPath = PROJECT_PATH + "/" + StringUtils.replace(devTableVo.getSubTable().getPackageName(), ".", "/");
           String subMybatisPath = MYBATIS_PATH + "/" + devTableVo.getSubTable().getModuleName();
           if(template.equals("subEntity.java")){
               fileName = StrFormatter.format("{}/{}/entity/{}/{}.java", subJavaPath,devTableVo.getSubTable().getModuleName(),devTableVo.getSubTable().getBizName(),devTableVo.getSubTable().getClassName());
           }
           if(template.equals("subDao.java")){
               fileName = StrFormatter.format("{}/{}/dao/{}.java", subJavaPath,devTableVo.getSubTable().getModuleName(),devTableVo.getSubTable().getClassName()+"Mapper");
           }
           if(template.equals("subService.java")){
               fileName = StrFormatter.format("{}/{}/service/{}.java", subJavaPath,devTableVo.getSubTable().getModuleName(),devTableVo.getSubTable().getClassName()+"Service");
           }
           if(template.equals("subServiceImpl.java")){
               fileName = StrFormatter.format("{}/{}/service/impl/{}.java", subJavaPath,devTableVo.getSubTable().getModuleName(),devTableVo.getSubTable().getClassName()+"ServiceImpl");
           }
           if(template.equals("subMapper.xml")){
               fileName = StrFormatter.format("{}/{}Mapper.xml", subMybatisPath, devTableVo.getSubTable().getClassName());
           }
       }
        return fileName;
    }

}
