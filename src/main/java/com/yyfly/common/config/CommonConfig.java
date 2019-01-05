package com.yyfly.common.config;

import com.yyfly.common.entity.DefaultGlobalExceptionHandler;
import com.yyfly.common.entity.DefaultJpaAuditorAware;
import com.yyfly.common.exception.GlobalExceptionHandler;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * common模块自动配置
 *
 * @author : yangjunqing / yangjunqing@seerbigdata.com
 * @version : 1.0
 * @Author : helsing
 * @Date : 16:58 2019/1/4
 */
@Configuration
@EnableJpaAuditing
@ComponentScan({"com.yyfly.common.util"})
public class CommonConfig {


    /**
     * Auditor aware auditor aware.
     *
     * @return the auditor aware
     * @author : yangjunqing / 2019-01-05
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
     * @author : yangjunqing / 2019-01-05
     */
    @Bean
    @ConditionalOnMissingBean(GlobalExceptionHandler.class)
    public GlobalExceptionHandler globalExceptionHandler(){
        return new DefaultGlobalExceptionHandler();
    }

}
