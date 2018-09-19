package com.yyfly.common.search;

import com.yyfly.common.constant.Constants;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import java.util.*;


/**
 * 构建全局搜索
 *
 * @author : yyfly / developer@yyfly.com
 * @date   : 2018-08-08
 */
public class GlobalSpecification {

    /**
     * Build search param specification.
     *
     * @param <T>          the type parameter
     * @param searchParams the search params
     * @param entityClazz  the entity clazz
     * @param connector    the connector
     * @return the specification
     * @author : yyfly / 2018-09-10
     */
    @SuppressWarnings("unchecked")
    public static <T> Specification<T> buildSearchParam(final List<SearchParam> searchParams, final Class<T> entityClazz, final String connector) {
        return (Specification<T>) (root, query, builder) -> {
            if (null != searchParams && searchParams.size() > 0) {

                List<Predicate> predicates = new ArrayList<>();
                for (SearchParam filter : searchParams) {

                    String[] names = StringUtils.split(filter.getFieldName(), ".");
                    Path expression = root.get(names[0]);
                    for (int i = 1; i < names.length; i++) {
                        expression = expression.get(names[i]);
                    }
                    if (!isNullOrEmpty(filter.getValue())) {
                        switch (filter.getOperator()) {
                            case EQ:
                                predicates.add(builder.equal(expression, filter.getValue()));
                                break;
                            case NO_EQ:
                                predicates.add(builder.notEqual(expression, filter.getValue()));
                                break;
                            case LIKE:
                                predicates.add(builder.like(expression, "%" + filter.getValue() + "%"));
                                break;
                            case NOT_LIKE:
                                predicates.add(builder.notLike(expression, "%" + filter.getValue() + "%"));
                                break;
                            case GT:
                                predicates.add(builder.greaterThan(expression, (Comparable) filter.getValue()));
                                break;
                            case LT:
                                predicates.add(builder.lessThan(expression, (Comparable) filter.getValue()));
                                break;
                            case GTE:
                                predicates.add(builder.greaterThanOrEqualTo(expression, (Comparable) filter.getValue()));
                                break;
                            case LTE:
                                predicates.add(builder.lessThanOrEqualTo(expression, (Comparable) filter.getValue()));
                                break;
                            case BETWEEN:
                                if (root.get(filter.getFieldName()).getJavaType() == Date.class) {
                                    predicates.add(builder.between(expression, (Date) filter.getValue(), (Date) filter.getValue1()));
                                }
                                break;
                            default:
                                break;
                        }
                    }
                }

                // 将所有条件用 and 联合起来
                if (!predicates.isEmpty()) {
                    if (StringUtils.isNotBlank(connector)) {
                        if (connector.equals(Constants.CONNECTOR_AND)) {
                            return builder.and(predicates.toArray(new Predicate[predicates.size()]));
                        } else if (connector.equals(Constants.CONNECTOR_OR)) {
                            return builder.or(predicates.toArray(new Predicate[predicates.size()]));
                        }
                    }
                }
            }

            return builder.conjunction();
        };
    }

    /**
     * 构造搜索参数
     *
     * @param <T>          the type parameter
     * @param searchParams the search params
     * @param entityClazz  the entity clazz
     * @return specification
     * @author : yyfly / 2018-09-10
     */
    public static <T> Specification<T> buildSearchParam(final List<SearchParam> searchParams, final Class<T> entityClazz) {
        return buildSearchParam(searchParams, entityClazz, "and");
    }

    public static boolean isNullOrEmpty(Object obj) {
        if (obj == null) {
            return true;
        }

        if (obj instanceof CharSequence) {
            return ((CharSequence) obj).length() == 0;
        }

        if (obj instanceof Collection) {
            return ((Collection) obj).isEmpty();
        }

        if (obj instanceof Map) {
            return ((Map) obj).isEmpty();
        }

        if (obj instanceof Object[]) {
            Object[] object = (Object[]) obj;
            if (object.length == 0) {
                return true;
            }
            boolean empty = true;
            for (int i = 0; i < object.length; i++) {
                if (!isNullOrEmpty(object[i])) {
                    empty = false;
                    break;
                }
            }
            return empty;
        }
        return false;
    }
}
