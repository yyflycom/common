package com.yyfly.library.service.impl;

import com.yyfly.library.constant.Constants;
import com.yyfly.library.dao.BaseDao;
import com.yyfly.library.entity.BaseEntity;
import com.yyfly.library.search.BaseSearchCriteria;
import com.yyfly.library.search.GlobalSpecification;
import com.yyfly.library.search.SearchResponseData;
import com.yyfly.library.service.BaseService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 基础 service 实现
 *
 * @param <T> the type parameter
 * @author : yyfly / developer@yyfly.com
 * @date : 2018-08-08
 */
@Transactional
@Service
public abstract class BaseServiceImpl<T extends BaseEntity> implements BaseService<T> {

    /**
     * 获取 DAO
     *
     * @return the base dao
     * @author : yyfly / 2018-08-08
     */
    abstract protected BaseDao<T> getDao();

    @Override
    public <S extends T> S save(S entity) {
        return getDao().save(entity);
    }

    @Override
    public T saveAndFlush(T entity) {
        return getDao().saveAndFlush(entity);
    }

    @Override
    public <S extends T> List<S> save(Iterable<S> entities) {
        return getDao().saveAll(entities);
    }

    @Override
    public boolean exists(String id) {
        return getDao().existsById(id);
    }

    @Override
    public SearchResponseData<T> findAll(BaseSearchCriteria searchCriteria, Class<T> clazz) {
        return findAllBySearchParams(searchCriteria, clazz);
    }

    @Override
    public SearchResponseData<T> findAllBySearchParams(BaseSearchCriteria searchCriteria, Class<T> clazz) {
        Specification<T> spec = GlobalSpecification.buildSearchParam(searchCriteria.buildSearchParams(), clazz);
        PageRequest pageRequest = searchCriteria.buildPageRequest();
        Page<T> page = findAll(spec, pageRequest);
        return new SearchResponseData<T>(page.getTotalElements(), page.getContent());
    }

    @Override
    public List<T> findAll() {

        if (Constants.DELETED_SWITCH) {
            return getDao().findAll();
        } else {
            Specification<T> spec = (root, query, cb) -> {

                //获得查询属性
                Path<String> sPath = root.get("deleted");

                //设置属性查询类型，如like ge gt...
                Predicate notEqualPredicate = cb.notEqual(sPath, Constants.DELETED_NORMAL);

                return cb.and(notEqualPredicate);
            };

            return getDao().findAll(spec);
        }
    }

    @Override
    public List<T> findAll(Iterable<String> ids) {
        if (Constants.DELETED_SWITCH) {
            return getDao().findAll();
        } else {
            return getDao().findAllById(ids)
                    .stream()
                    .filter(entity -> !entity.getDeleted().equals(Constants.DELETED_DELETE))
                    .collect(Collectors.toList());
        }
    }

    @Override
    public List<T> findAll(Sort sort) {
        if (Constants.DELETED_SWITCH) {
            return getDao().findAll(sort);
        } else {
            return getDao().findAll(sort)
                    .stream()
                    .filter(entity -> !entity.getDeleted().equals(Constants.DELETED_DELETE))
                    .collect(Collectors.toList());
        }
    }

    @Override
    public List<T> findAll(Specification<T> spec) {
        if (Constants.DELETED_SWITCH) {
            return getDao().findAll(spec);
        } else {
            return getDao().findAll(spec)
                    .stream()
                    .filter(entity -> !entity.getDeleted().equals(Constants.DELETED_DELETE))
                    .collect(Collectors.toList());
        }

    }

    @Override
    public List<T> findAll(Specification<T> spec, Sort sort) {
        if (Constants.DELETED_SWITCH) {
            return getDao().findAll(spec, sort);
        } else {
            return getDao().findAll(spec, sort)
                    .stream()
                    .filter(entity -> !entity.getDeleted().equals(Constants.DELETED_DELETE))
                    .collect(Collectors.toList());
        }
    }

    @Override
    public Page<T> findAll(Pageable pageable) {
        if (Constants.DELETED_SWITCH) {
            return getDao().findAll(pageable);
        }
        Specification<T> spec = (root, query, cb) -> {

            //获得查询属性
            Path<String> sPath = root.get("deleted");

            //设置属性查询类型，如like ge gt...
            Predicate notEqualPredicate = cb.notEqual(sPath, Constants.DELETED_NORMAL);

            return cb.and(notEqualPredicate);
        };

        return getDao().findAll(spec, pageable);
    }

    @Override
    public Page<T> findAll(Specification<T> spec, Pageable pageable) {

        if (Constants.DELETED_SWITCH) {
            return getDao().findAll(spec, pageable);
        }

        return getDao().findAll(buildSpecification(spec), pageable);
    }

    /**
     * 构建 Specification 增加删除
     *
     * @param spec the spec
     * @return the specification
     * @author : mingweigao / 2018-09-10
     */
    private Specification<T> buildSpecification(Specification<T> spec) {
        Specification<T> specDeleted = (root, query, cb) -> {

            //获得查询属性
            Path<String> sPath = root.get("deleted");

            //设置属性查询类型，如like ge gt...
            Predicate notEqualPredicate = cb.notEqual(sPath, Constants.DELETED_NORMAL);

            return cb.and(notEqualPredicate);
        };

        if (null == spec) {
            spec = specDeleted;
        } else {
            spec.and(specDeleted);
        }
        return spec;
    }

    @Override
    public T findById(String id) {
        if (Constants.DELETED_SWITCH) {
            return getDao().findById(id).get();
        } else {
            return getDao().findById(id).filter(entity -> !entity.getDeleted().equals(Constants.DELETED_DELETE)).get();
        }
    }

    @Override
    public T findBySpecification(Specification<T> spec) {
        if (Constants.DELETED_SWITCH) {
            return getDao().findOne(spec).get();
        } else {
            return getDao().findOne(buildSpecification(spec)).get();
        }
    }

    @Override
    public void deleteById(String id) {
        deleteById(id, Constants.DELETED_SWITCH);
    }

    @Override
    public void delete(T entity) {
        delete(entity, Constants.DELETED_SWITCH);
    }

    @Override
    public void deleteInBatch(Iterable<T> entities) {
        deleteInBatch(entities, Constants.DELETED_SWITCH);
    }

    @Override
    public void deleteAll(Iterable<? extends T> entities) {
        deleteAll(entities, Constants.DELETED_SWITCH);
    }

    @Override
    public void deleteAll() {
        deleteAll(Constants.DELETED_SWITCH);
    }

    @Override
    public void deleteById(String id, Boolean flag) {
        if (flag) {
            getDao().deleteById(id);
        } else {
            T entity = findById(id);
            entity.setDeleted(Constants.DELETED_DELETE);
            getDao().save(entity);
        }
    }

    @Override
    public void delete(T entity, Boolean flag) {
        if (flag) {
            getDao().delete(entity);
        } else {
            entity.setDeleted(Constants.DELETED_DELETE);
            getDao().save(entity);
        }
    }

    @Override
    public void deleteInBatch(Iterable<T> entities, Boolean flag) {
        if (flag) {
            getDao().deleteInBatch(entities);
        } else {
            for (T entity : entities) {
                entity.setDeleted(Constants.DELETED_DELETE);
            }
            getDao().saveAll(entities);
        }
    }

    @Override
    public void deleteAll(Iterable<? extends T> entities, Boolean flag) {
        if (flag) {
            getDao().deleteAll(entities);
        } else {
            for (T entity : entities) {
                entity.setDeleted(Constants.DELETED_DELETE);
            }
            getDao().saveAll(entities);
        }
    }

    @Override
    public void deleteAll(Boolean flag) {
        if (flag) {
            getDao().deleteAll();
        } else {
            List<T> list = getDao().findAll();
            for (T entity : list) {
                entity.setDeleted(Constants.DELETED_DELETE);
            }
            getDao().saveAll(list);
        }
    }

    @Override
    public void flush() {
        getDao().flush();
    }

    @Override
    public long count() {
        return getDao().count();
    }

    @Override
    public long count(Specification<T> spec) {
        return getDao().count(spec);
    }

}
