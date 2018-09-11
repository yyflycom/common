package com.yyfly.common.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 基础实体
 *
 * @author : yyfly / developer@yyfly.com
 * @date   : 2018-08-08
 */
@Data
@MappedSuperclass
@EntityListeners({AuditingEntityListener.class,EntityListener.class})
public class BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 唯一ID
     */
    @Id
    @GenericGenerator(name = "twitter-id", strategy = "com.yyfly.common.entity.TwitterIdGenerator")
    @GeneratedValue(generator = "twitter-id")
    private String id;

    /**
     * 创建人
     */
    @CreatedBy
    @Column(updatable = false)
    private String createBy;

    /**
     * 创建时间
     */
    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(updatable = false)
    private Date createDate;

    /**
     * 最后一次修改人
     */
    @LastModifiedBy
    private String updateBy;

    /**
     * 最后一次修改时间
     */
    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateDate;

    /**
     * 版本
     */
    @Version
    private Long version;

    /**
     * 删除状态
     */
    private String deleted;

}