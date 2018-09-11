package com.yyfly.common.entity;

import com.yyfly.common.constant.Constants;

import javax.persistence.PrePersist;

/**
 * EntityListener
 *
 * @author : yyfly / developer@yyfly.com
 * @version : 1.0
 */
public class EntityListener {

    /**
     * 保存前处理
     *
     * @param entity the entity
     * @author : yyfly / 2017-04-04
     */
    @PrePersist
    public void prePersist(BaseEntity entity) {

        if(null == entity.getDeleted()){
            entity.setDeleted(Constants.DELETED_NORMAL);
        }

    }
}
