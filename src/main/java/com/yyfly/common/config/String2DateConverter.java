package com.yyfly.common.config;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * 自定义 String Date 转换
 *
 * @author : yyfly / developer@yyfly.com
 * @date   : 2018-08-08
 */
@Component
public class String2DateConverter implements Converter<String, Date> {

    private static final List<String> DATE_FORMAT = new ArrayList<>();
    static {
        DATE_FORMAT.add("yyyy-MM");
        DATE_FORMAT.add("yyyy-MM-dd");
        DATE_FORMAT.add("yyyy-MM-dd HH:mm");
        DATE_FORMAT.add("yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 格式化日期
     *
     * @param dateString the date string
     * @param format     String 格式
     * @return Date 日期
     * @author : yyfly / 2018-08-08
     */
    public  Date parseDate(String dateString, String format) {
        Date date=null;
        try {
            DateFormat dateFormat = new SimpleDateFormat(format);
            date = dateFormat.parse(dateString);
        } catch (Exception e) {

        }
        return date;
    }

    @Override
    public Date convert(String source) {
        String value = source.trim();
        if ("".equals(value)){
            return null;
        }

        if("^\\d{4}-\\d{1,2}$".matches(source)){
            return parseDate(source, DATE_FORMAT.get(0));
        }else if("^\\d{4}-\\d{1,2}-\\d{1,2}$".matches(source)){
            return parseDate(source, DATE_FORMAT.get(1));
        }else if("^\\d{4}-\\d{1,2}-\\d{1,2} \\d{1,2}:\\d{1,2}$".matches(source)){
            return parseDate(source, DATE_FORMAT.get(2));
        }else if("^\\d{4}-\\d{1,2}-\\d{1,2} \\d{1,2}:\\d{1,2}:\\d{1,2}$".matches(source)){
            return parseDate(source, DATE_FORMAT.get(3));
        }else {
            throw new IllegalArgumentException("Invalid boolean value '" + source + "'");
        }
    }
}
