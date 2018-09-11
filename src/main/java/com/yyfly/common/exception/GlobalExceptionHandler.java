package com.yyfly.common.exception;

import com.yyfly.common.entity.ResponseData;
import com.yyfly.common.util.SpringContextUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 全局异常处理
 *
 * @author : yyfly / developer@yyfly.com
 * @date : 2018-08-08
 */
@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {
    private static Logger logger = LoggerFactory.getLogger(SpringContextUtils.class);

    /**
     * 所有异常报错
     *
     * @param exception
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    public ResponseData exceptionHandler(Exception exception) {
        logger.error("程序异常：", exception);
        return ResponseData.error(exception.getMessage());
    }

}
