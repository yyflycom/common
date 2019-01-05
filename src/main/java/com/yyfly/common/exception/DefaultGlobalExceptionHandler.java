package com.yyfly.common.exception;

import com.yyfly.common.entity.ResponseData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;


/**
 * 全局异常处理
 * @author : yjqhelsing / yjqhelsing@qq.com
 * @version : 1.0
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
