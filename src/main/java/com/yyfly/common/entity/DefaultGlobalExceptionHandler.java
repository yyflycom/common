package com.yyfly.common.entity;

import com.yyfly.common.exception.GlobalException;
import com.yyfly.common.exception.GlobalExceptionHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;

/**
 * DefaultGlobalExceptionHandler
 *
 * @Author : helsing
 * @Date : 9:50 2019/1/5
 */
public class DefaultGlobalExceptionHandler implements GlobalExceptionHandler {

    private static Logger logger = LoggerFactory.getLogger(DefaultGlobalExceptionHandler.class);

    @Override
    public ResponseData exceptionHandler(Exception exception) {
        logger.error("程序异常：", exception);

        if (exception instanceof GlobalException){
            GlobalException e = (GlobalException) exception;
            return ResponseData.error(e.getCode(), e.getMessage());
        }

        return ResponseData.error(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
    }
}
