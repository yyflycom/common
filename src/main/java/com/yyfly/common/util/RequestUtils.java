package com.yyfly.common.util;

import javax.servlet.http.HttpServletRequest;

/**
 * 请求工具类
 *
 * @author : yyfly / mwgao@vip.qq.com
 * @date   : 2018-08-08
 */
public class RequestUtils {

    private static final String XML_HTTP_REQUEST = "XMLHttpRequest";
    private static final String X_REQUESTED_WITH = "X-Requested-With";

    private static final String CONTENT_TYPE = "Content-type";
    private static final String CONTENT_TYPE_JSON = "application/json";

    /**
     * 是否 ajax 请求
     *
     * @param request the request
     * @return the boolean
     * @author : yyfly / 2018-09-14
     */
    public static boolean isAjax(HttpServletRequest request) {
        return XML_HTTP_REQUEST.equals(request.getHeader(X_REQUESTED_WITH));
    }

    /**
     * 是否 json
     *
     * @param request the request
     * @return the boolean
     * @author : yyfly / 2018-09-14
     */
    public static boolean isContentTypeJson(HttpServletRequest request) {
        return CONTENT_TYPE_JSON.equals(request.getHeader(CONTENT_TYPE));
    }

}
