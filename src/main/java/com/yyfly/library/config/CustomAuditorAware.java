package com.yyfly.library.config;

import com.yyfly.library.constant.Constants;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

/**
 * 自定义审计
 *
 * @author : yyfly / developer@yyfly.com
 * @date   : 2018-08-08
 */
public class CustomAuditorAware implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null ||
                !authentication.isAuthenticated() ||
                authentication instanceof AnonymousAuthenticationToken) {
            return Optional.empty();
        }

        if (Constants.ANONYMOUS_USER.equals(authentication.getPrincipal())) {
            return Optional.of(Constants.ANONYMOUS_USER);
        }

        return Optional.of(authentication.getPrincipal().toString());
    }
}