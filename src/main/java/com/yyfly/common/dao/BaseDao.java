package com.yyfly.common.dao;

import com.yyfly.common.entity.BaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * 基础 DAO 默认使用String 类型作为主键
 *
 * @param <T> the type parameter
 * @author : yyfly / developer@yyfly.com
 * @date   : 2018-08-08
 */
@NoRepositoryBean
public interface BaseDao<T extends BaseEntity> extends JpaRepository<T, String>, JpaSpecificationExecutor<T> {
}
