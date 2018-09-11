package com.yyfly.common.search;

import lombok.Data;

/**
 * 搜索参数实体
 *
 * @author : yyfly / developer@yyfly.com
 * @date   : 2018-08-08
 */
@Data
public class SearchParam {

    public enum Operator {
        /**
         * 连接操作
         */
        EQ, NO_EQ, LIKE, NOT_LIKE, GT, LT, GTE, LTE, BETWEEN
    }

    private String fieldName;
    private Operator operator;
    private Object value;
    private Object value1;

}
