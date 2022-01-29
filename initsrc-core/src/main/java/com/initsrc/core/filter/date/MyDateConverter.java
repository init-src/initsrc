package com.initsrc.core.filter.date;

import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

/**
 * 自定义格式转换器
 * Stirng 转为Date
 */
@Component
public class MyDateConverter implements Converter<String, Date> {
    private static final List<String> formarts = new ArrayList<>(4);

    static {
        formarts.add("yyyy-MM");
        formarts.add("yyyy-MM-dd");
        formarts.add("yyyy-MM-dd HH:mm");
        formarts.add("yyyy-MM-dd HH:mm:ss");
        formarts.add("yyyy/MM");
        formarts.add("yyyy/MM/dd");
        formarts.add("yyyy/MM/dd HH:mm");
        formarts.add("yyyy/MM/dd HH:mm:ss");
    }

    @Override
    public Date convert(String dateStr) {
        String source = dateStr.trim();
        if ("".equals(source)) {
            return null;
        }
        if (source.matches("^\\d{4}-\\d{1,2}$")) {
            return parseDate(source, formarts.get(0));
        } else if (source.matches("^\\d{4}-\\d{1,2}-\\d{1,2}$")) {
            return parseDate(source, formarts.get(1));
        } else if (source.matches("^\\d{4}-\\d{1,2}-\\d{1,2} {1}\\d{1,2}:\\d{1,2}$")) {
            return parseDate(source, formarts.get(2));
        } else if (source.matches("^\\d{4}-\\d{1,2}-\\d{1,2} {1}\\d{1,2}:\\d{1,2}:\\d{1,2}$")) {
            return parseDate(source, formarts.get(3));
        } else if (source.matches("^\\d{4}/\\d{1,2}$")) {
            return parseDate(source, formarts.get(4));
        } else if (source.matches("^\\d{4}/\\d{1,2}/\\d{1,2}$")) {
            return parseDate(source, formarts.get(5));
        } else if (source.matches("^\\d{4}/\\d{1,2}/\\d{1,2} {1}\\d{1,2}:\\d{1,2}$")) {
            return parseDate(source, formarts.get(6));
        } else if (source.matches("^\\d{4}/\\d{1,2}/\\d{1,2} {1}\\d{1,2}:\\d{1,2}:\\d{1,2}$")) {
            return parseDate(source, formarts.get(7));
        }else if(isNumber(source)){
            long lt = new Long(source);
            Date date = new Date(lt);
            return date;
        } else {
            throw new IllegalArgumentException("Invalid boolean value '" + source + "'");
        }
    }

    /**
     * 格式化日期
     *
     * @param dateStr String 字符型日期
     * @param format  String 格式
     * @return Date 日期
     */
    private Date parseDate(String dateStr, String format) {
        try {
            DateTimeFormatter formatter = DateTimeFormat.forPattern(format);
            return formatter.parseDateTime(dateStr).toDate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 判断是否为数字格式不限制位数
     *
     * @param o 待校验参数
     * @return 如果全为数字，返回true；否则，返回false
     */
    public static boolean isNumber(Object o) {
        return (Pattern.compile("[0-9]*")).matcher(String.valueOf(o)).matches();
    }

}

