package com.yyfly.common.exception;

import com.yyfly.common.entity.ResponseData;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * 全局异常处理接口
 * @author : yjqhelsing / yjqhelsing@qq.com
 * @version : 1.0
 */
@ControllerAdvice
@ResponseBody
public interface GlobalExceptionHandler {

    /**
     * 异常处理
     *
     * @param exception the exception
     * @return the response data
     * @author : yjqhelsing / 2019-01-05
     */
    @ExceptionHandler
    ResponseData exceptionHandler(Exception exception);
}
