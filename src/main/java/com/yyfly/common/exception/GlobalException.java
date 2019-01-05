package com.yyfly.common.exception;

import com.yyfly.common.constant.ResponseCode;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 全局异常
 *
 * @author : yyfly / developer@yyfly.com
 * @date   : 2018-08-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class GlobalException extends RuntimeException {

    private static final long serialVersionUID = 5301233893782305510L;

    /**
     * 错误代码
     */
    private Integer code;

    /**
     * 错误信息
     */
    private String message;


    public GlobalException(Integer code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

    public GlobalException(String message) {
        super(message);
        this.code = ResponseCode.ERROR.getCode();
        this.message = message;
    }

    public GlobalException(ResponseCode responseCode) {
        super(responseCode.getMessage());
        this.code = responseCode.getCode();
        this.message = responseCode.getMessage();
    }
}