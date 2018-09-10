package com.yyfly.library.entity;

import com.yyfly.library.util.IdUtils;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.io.Serializable;

/**
 * 主键生成策略
 *
 * @author : yyfly / developer@yyfly.com
 * @date   : 2018-08-08
 */
public class TwitterIdGenerator implements IdentifierGenerator {

    @Override
    public Serializable generate(SharedSessionContractImplementor sharedSessionContractImplementor, Object o) throws HibernateException {
        if (o == null) {
            throw new IllegalArgumentException("实体对象不能为空！");
        }
        if (!(o instanceof BaseEntity)) {
            throw new IllegalArgumentException("实体对象必须要继承BaseEntity对象！");
        }

        BaseEntity baseEntity = (BaseEntity) o;
        if (StringUtils.isEmpty(baseEntity.getId())) {
            //新增
            return IdUtils.nextId();
        } else {
            //更新
            return baseEntity.getId();
        }
    }
}