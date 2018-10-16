package com.yyfly.common.util;

import com.yyfly.common.http.MimeTypes;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 返回工具类
 *
 * @author : yyfly / developer@yyfly.com
 * @date   : 2018-08-08
 */
public class ResponseUtils {

    /**
     * 纯文本
     *
     * @param response the response
     * @param object   the object
     * @author : yyfly / 2018-09-12
     */
    public static void writerPlain(HttpServletResponse response, Object object) {
        writer(response, MimeTypes.TEXT_PLAIN, object);
    }

    /**
     * 浏览器 html 解析器对文件处理后内容
     *
     * @param response the response
     * @param object   the object
     * @author : yyfly / 2018-09-12
     */
    public static void writerHtml(HttpServletResponse response, Object object) {
        writer(response, MimeTypes.TEXT_HTML, object);
    }

    /**
     * XML
     *
     * @param response the response
     * @param object   the object
     * @author : yyfly / 2018-09-12
     */
    public static void writerXml(HttpServletResponse response, Object object) {
        writer(response, MimeTypes.TEXT_XML, object);
    }

    /**
     * json data.
     *
     * @param response the response
     * @param object   the object
     * @author : yyfly / 2018-09-12
     */
    public static void writerJson(HttpServletResponse response, Object object) {
        writer(response, MimeTypes.APPLICATION_JSON, object);
    }

    /**
     * Response data.
     *
     * @param response the response
     * @param object   the object
     * @author : yyfly / 2018-09-12
     */
    public static void writer(HttpServletResponse response, String mimeTypes, Object object) {
        response.setContentType(mimeTypes);
        response.setCharacterEncoding("UTF-8");
        try {
            response.getWriter().print(JacksonMapper.getInstance().toJson(object));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
