package com.yyfly.common.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 基础实体 DTO
 *
 * @author : yyfly / developer@yyfly.com
 * @date   : 2018-08-08
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BaseDTO {
    /**
     * 唯一ID
     */
    private String id;

    /**
     * 创建人
     */
    private String createBy;

    /**
     * 创建时间
     */
    private Date createDate;

    /**
     * 最后一次修改人
     */
    private String updateBy;

    /**
     * 最后一次修改时间
     */
    private Date updateDate;

    /**
     * 版本
     */
    private Long version;

    /**
     * 删除状态
     */
    private String deleted;
}
