package com.yyfly.common.constant;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 项目属性
 *
 * @author : yyfly / developer@yyfly.com
 * @date   : 2018-08-08
 */
@Data
@Component
@ConfigurationProperties(prefix = "company")
public class CompanyProperties {
    private String name;
    private String website;
    private String email;
}
