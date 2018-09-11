package com.yyfly.common.search;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

import java.io.Serializable;
import java.util.List;

/**
 * 基础搜索
 *
 * @author : yyfly / developer@yyfly.com
 * @date : 2018-08-08
 */
public abstract class BaseSearchCriteria implements Serializable {

    private static final long serialVersionUID = -578082466242268980L;
    /**
     * 开始行号
     */
    private int start = 0;
    /**
     * 每页多少条记录
     */
    private int limit = 10;

    /**
     * 每页多少条记录
     */
    private int pageIndex = 10;

    /**
     * 排序字段
     */
    private String field;
    /**
     * 排序规则
     */
    private String direction;

    /**
     * 构建搜索参数
     *
     * @return
     */
    public abstract List<SearchParam> buildSearchParams();

    /**
     * 创建分页请求.
     *
     * @return
     * @author : mingweigao / <mwgao@vip.qq.com> / 2016年1月19日
     */
    public PageRequest buildPageRequest() {
        Sort sort = null;
        if (StringUtils.isNoneEmpty(field) && StringUtils.isNoneEmpty(direction)) {
            sort = new Sort(Direction.fromString(direction), field);
        } else {
            //如果没传则默认使用 createDate 作为排序字段
            sort = new Sort(Direction.ASC, "createDate");
        }
        return PageRequest.of(start / limit, limit, sort);
    }

}
