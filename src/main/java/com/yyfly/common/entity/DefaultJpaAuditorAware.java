package com.yyfly.common.entity;

import com.yyfly.common.constant.Constants;
import org.springframework.data.domain.AuditorAware;

import java.util.Optional;


/**
 * 默认 entity 审计
 * @author : yjqhelsing / yjqhelsing@qq.com
 * @version : 1.0
 */
public class DefaultJpaAuditorAware implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        return Optional.of(Constants.ANONYMOUS_USER);
    }
}
