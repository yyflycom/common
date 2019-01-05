package com.yyfly.common.exception;

import com.yyfly.common.http.HTTP;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 全局异常
 *
 * @author : yyfly / developer@yyfly.com
 * @version : 1.0
 * @date : 2018-08-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class GlobalException extends RuntimeException {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 5301233893782305510L;

    /**
     * 错误代码
     */
    private Integer code;

    /**
     * 错误信息
     */
    private String message;


    /**
     * Global exception.
     */
    public GlobalException() {
        super(HTTP.Status.ERROR.getReasonPhrase());
        this.code = HTTP.Status.ERROR.value();
        this.message = HTTP.Status.ERROR.getReasonPhrase();
    }

    /**
     * Global exception.
     *
     * @param code    the code
     * @param message the message
     */
    public GlobalException(Integer code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

    /**
     * Global exception.
     *
     * @param message the message
     */
    public GlobalException(String message) {
        super(message);
        this.code = HTTP.ERROR;
        this.message = message;
    }
}