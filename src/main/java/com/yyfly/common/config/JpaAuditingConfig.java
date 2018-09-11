package com.yyfly.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * 实现 JPA 审计
 *
 * @author : yyfly / developer@yyfly.com
 * @date   : 2018-08-08
 */
@Configuration
@EnableJpaAuditing
public class JpaAuditingConfig {

    @Bean
    public AuditorAware<String> auditorProvider(){
        return new CustomAuditorAware();
    }


}
