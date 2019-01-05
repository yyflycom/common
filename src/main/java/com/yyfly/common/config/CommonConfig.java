package com.yyfly.common.config;

import com.yyfly.common.exception.DefaultGlobalExceptionHandler;
import com.yyfly.common.entity.DefaultJpaAuditorAware;
import com.yyfly.common.exception.GlobalExceptionHandler;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;


/**
 * common配置
 * @author : helsing / yjqhelsing@qq.com
 * @version : 1.0
 */
@Configuration
@EnableJpaAuditing
@ComponentScan({"com.yyfly.common.util"})
public class CommonConfig {


    /**
     * Auditor aware auditor aware.
     *
     * @return the auditor aware
     * @author : yjqhelsing / 2019-01-05
     */
    @Bean
    @ConditionalOnMissingBean(AuditorAware.class)
    public AuditorAware<String> auditorAware(){
        return new DefaultJpaAuditorAware();
    }


    /**
     * Global exception handler global exception handler.
     *
     * @return the global exception handler
     * @author : yjqhelsing / 2019-01-05
     */
    @Bean
    @ConditionalOnMissingBean(GlobalExceptionHandler.class)
    public GlobalExceptionHandler globalExceptionHandler(){
        return new DefaultGlobalExceptionHandler();
    }

}
