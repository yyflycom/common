package com.yyfly.library.entity;

import com.yyfly.library.util.I18NUtils;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 响应
 *
 * @author : yyfly / developer@yyfly.com
 * @date   : 2018-08-08
 */
@Data
public class ResponseData implements Serializable {

    private static final long serialVersionUID = -6936648847780505144L;
    /**
     * 是否成功
     */
    public Boolean success;

    /**
     * 状态码
     */
    @ApiModelProperty(value = "状态码")
    public Integer code;

    /**
     * 返回的消息
     */
    public String message;

    /**
     * 返回的数据
     */
    public Object data;

    public ResponseData() {
        super();
    }

    public ResponseData(Boolean success, String message) {
        super();
        this.success = success;
        this.message = I18NUtils.getMessage(message);
    }

    public ResponseData(Boolean success, String message, Object data) {
        super();
        this.success = success;
        this.message = I18NUtils.getMessage(message);
        this.data = data;
    }

    public ResponseData(Boolean success, Integer code, String message, Object data) {
        this.success = success;
        this.code = code;
        this.message = message;
        this.data = data;
    }

    @Override
    public String toString() {
        return "ResponseData{" +
                "success=" + success +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }

    public static ResponseData success() {
        return success("admin.common.success");
    }

    public static ResponseData error() {
        return error("admin.common.fail");
    }

    public static ResponseData error(String message) {
        return new ResponseData(false, message, null);
    }

    public static ResponseData error(String message, Object data) {
        return new ResponseData(false, message, data);
    }

    public static ResponseData success(String message) {
        return new ResponseData(true, message, null);
    }

    public static ResponseData success(String message, Object data) {
        return new ResponseData(true, message, data);
    }
}
