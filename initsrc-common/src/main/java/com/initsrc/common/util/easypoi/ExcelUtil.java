package com.initsrc.common.util.easypoi;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import com.initsrc.common.exception.BusinessException;
import com.initsrc.common.plugin.easypoi.ExcelExportStylerImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

/**
 * excel工具类
 */
@Slf4j
public class ExcelUtil {


    public static boolean isExcel2003(String filePath) {
        return filePath.matches("^.+\\.(?i)(xls)$");
    }

    public static boolean isExcel2007(String filePath) {
        return filePath.matches("^.+\\.(?i)(xlsx)$");
    }

    /**
     * <p>
     * 导出
     * </p>
     *
     * @param data     : 数据
     * @param fileName : 文件名称
     * @param clazz    : 数据类型
     * @param response :
     * @author java1
     * @date 2020/6/24 16:51
     */
    public static <T> void exoprtExcel(List<T> data, String fileName, Class<T> clazz, HttpServletResponse response) {
        ExportParams exportParams = new ExportParams();
        exportParams.setStyle(ExcelExportStylerImpl.class);
        Workbook workbook = ExcelExportUtil.exportExcel(exportParams, clazz, data);
        workbook.setSheetName(0, "Sheet1");

        if (workbook != null) {
            try {
                OutputStream outputStream = getOutputStream(fileName, response);
                workbook.write(outputStream);
            } catch (Exception e) {
                log.error(e.getMessage(), e);
                throw new BusinessException("导出异常");
            } finally {
                try {
                    workbook.close();
                } catch (IOException e) {
                    log.error(e.getMessage(), e);
                    throw new BusinessException("导出异常");
                }
            }
        }
    }

    /**
     * <p>
     * 导出
     * </p>
     *
     * @param workbook     : 数据
     * @param fileName : 文件名称
     * @param clazz    : 数据类型
     * @param response :
     * @author java1
     * @date 2020/6/24 16:51
     */
    public static <T> void exoprtExcel(Workbook workbook, String fileName, Class<T> clazz, HttpServletResponse response) {
        workbook.setSheetName(0, "Sheet1");

        if (workbook != null) {
            try {
                OutputStream outputStream = getOutputStream(fileName, response);
                workbook.write(outputStream);
            } catch (Exception e) {
                log.error(e.getMessage(), e);
                throw new BusinessException("导出异常");
            } finally {
                try {
                    workbook.close();
                } catch (IOException e) {
                    log.error(e.getMessage(), e);
                    throw new BusinessException("导出异常");
                }
            }
        }
    }


    /**
     * 导出文件时为Writer生成OutputStream
     */
    public static OutputStream getOutputStream(String fileName, HttpServletResponse response) throws Exception {
        try {
            fileName = URLEncoder.encode(fileName, "UTF-8");
//            response.setContentType("application/vnd.ms-excel");
            response.setCharacterEncoding("utf8");
            response.setHeader("Content-Disposition", "attachment; filename=" + fileName + ".xls");
            response.setHeader("Pragma", "public");
            response.setHeader("Cache-Control", "no-store");
            response.addHeader("Cache-Control", "max-age=0");
            return response.getOutputStream();
        } catch (IOException e) {
            throw new Exception("导出excel表格失败!", e);
        }
    }

    public static void exportExcel(List<?> list, String title, String sheetName, Class<?> pojoClass, String fileName, boolean isCreateHeader, HttpServletResponse response) {
        ExportParams exportParams = new ExportParams(title, sheetName);
        exportParams.setCreateHeadRows(isCreateHeader);
        defaultExport(list, pojoClass, fileName, response, exportParams);

    }

    public static void exportExcel(List<?> list, String title, String sheetName, Class<?> pojoClass, String fileName, HttpServletResponse response) {
        defaultExport(list, pojoClass, fileName, response, new ExportParams(title, sheetName));
    }

    public static void exportExcel(List<Map<String, Object>> list, String fileName, HttpServletResponse response) {
        defaultExport(list, fileName, response);
    }

    private static void defaultExport(List<?> list, Class<?> pojoClass, String fileName, HttpServletResponse response, ExportParams exportParams) {
        Workbook workbook = ExcelExportUtil.exportExcel(exportParams, pojoClass, list);
        if (workbook != null) ;
        downLoadExcel(fileName, response, workbook);
    }

    private static void downLoadExcel(String fileName, HttpServletResponse response, Workbook workbook) {
        try {
            response.setCharacterEncoding("UTF-8");
            response.setHeader("content-Type", "application/vnd.ms-excel");
            response.setHeader("Content-Disposition",
                    "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
            workbook.write(response.getOutputStream());
        } catch (IOException e) {
            throw new BusinessException(e.getMessage());
        }
    }

    private static void defaultExport(List<Map<String, Object>> list, String fileName, HttpServletResponse response) {
        Workbook workbook = ExcelExportUtil.exportExcel(list, ExcelType.HSSF);
        if (workbook != null) ;
        downLoadExcel(fileName, response, workbook);
    }

    public static <T> List<T> importExcel(String filePath, Integer titleRows, Integer headerRows, Class<T> pojoClass) {
        if (StringUtils.isBlank(filePath)) {
            return null;
        }
        ImportParams params = new ImportParams();
        params.setTitleRows(titleRows);
        params.setHeadRows(headerRows);
        List<T> list = null;
        try {
            list = ExcelImportUtil.importExcel(new File(filePath), pojoClass, params);
        } catch (NoSuchElementException e) {
            throw new BusinessException("模板不能为空");
        } catch (Exception e) {
            e.printStackTrace();
            throw new BusinessException(e.getMessage());
        }
        return list;
    }

    public static <T> List<T> importExcel(MultipartFile file, Integer titleRows, Integer headerRows, Class<T> pojoClass) {
        if (file == null) {
            return null;
        }
        ImportParams params = new ImportParams();
        params.setTitleRows(titleRows);
        params.setHeadRows(headerRows);
        List<T> list = null;
        try {
            list = ExcelImportUtil.importExcel(file.getInputStream(), pojoClass, params);
        } catch (NoSuchElementException e) {
            throw new BusinessException("excel文件不能为空");
        } catch (Exception e) {
            throw new BusinessException(e.getMessage());
        }
        return list;
    }

}
