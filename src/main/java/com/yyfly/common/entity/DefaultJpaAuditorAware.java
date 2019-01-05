package com.yyfly.common.entity;

import com.yyfly.common.constant.Constants;
import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

/**
 * 审计
 */
public class DefaultJpaAuditorAware implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        return Optional.of(Constants.ANONYMOUS_USER);
    }
}
