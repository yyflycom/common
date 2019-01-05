package com.yyfly.common.exception;

import com.yyfly.common.entity.ResponseData;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 全局异常处理
 */
@ControllerAdvice
@ResponseBody
public interface GlobalExceptionHandler {

    /**
     * 所有异常处理
     * @param exception
     * @return com.yyfly.common.entity.ResponseData
     * @Author: helsing
     * @Date: 2019/1/5 9:18
     */
    @ExceptionHandler
    ResponseData exceptionHandler(Exception exception);
}
