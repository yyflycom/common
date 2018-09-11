package com.yyfly.common.search;

import com.yyfly.common.entity.ResponseData;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;


/**
 * 搜索响应
 *
 * @author : yyfly / developer@yyfly.com
 * @date : 2018-08-08
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class SearchResponseData<T> extends ResponseData {

    /**
     * 记录总数
     */
    private long total;

    /**
     * 传回的数据集合
     */
    private List<T> rows;

    public SearchResponseData() {
    }

    public SearchResponseData(long total, List<T> rows) {
        this.total = total;
        this.rows = rows;
    }
}
