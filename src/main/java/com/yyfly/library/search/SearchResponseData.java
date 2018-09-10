package com.yyfly.library.search;

import com.yyfly.library.entity.ResponseData;
import lombok.Data;

import java.util.List;


/**
 * 搜索响应
 *
 * @author : yyfly / developer@yyfly.com
 * @date : 2018-08-08
 */
@Data
public class SearchResponseData<T> extends ResponseData {

    /**
     * 记录总数
     */
    private long total;

    /**
     * 传回的数据集合
     */
    private List<T> rows;

    public SearchResponseData(long total, List<T> rows) {
        this.total = total;
        this.rows = rows;
    }
}
