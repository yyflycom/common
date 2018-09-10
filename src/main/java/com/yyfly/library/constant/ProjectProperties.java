package com.yyfly.library.constant;

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
@ConfigurationProperties(prefix = "project")
public class ProjectProperties {
    private String name;
    private String type;
    private String version;
    private String description;
    private String copyright;
    private String license;
    private String licenseUrl;
}
