package com.yyfly.common.util;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Locale;

/**
 * I18N 工具类
 *
 * @author : yyfly / developer@yyfly.com
 * @date   : 2018-08-08
 */
@Component
public class I18NUtils {

    @Resource
    private static MessageSource messageSource;

    /**
     * 获取国际化信息
     *
     * @param code :对应 messages 配置的 key.
     * @return string
     * @author : yyfly / 2018-08-08
     */
    public static String getMessage(String code) {
        return getMessage(code, null);
    }


    /**
     * 获取国际化信息
     *
     * @param code : 对应 messages 配置的 key.
     * @param args : 数组参数.
     * @return string
     * @author : yyfly / 2018-08-08
     */
    public static String getMessage(String code, Object[] args) {
        return getMessage(code, args, "");
    }


    /**
     * 获取国际化信息
     *
     * @param code           : 对应 messages 配置的 key.
     * @param args           : 数组参数.
     * @param defaultMessage : 没有设置 key 的时候的默认值.
     * @return string
     * @author : yyfly / 2018-08-08
     */
    public static String getMessage(String code, Object[] args, String defaultMessage) {
        Locale locale = LocaleContextHolder.getLocale();
        return messageSource.getMessage(code, args, defaultMessage, locale);
    }
}
