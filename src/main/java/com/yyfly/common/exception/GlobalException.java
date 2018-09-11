package com.yyfly.common.exception;

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
    private String code;

    /**
     * 错误信息
     */
    private String message;
}