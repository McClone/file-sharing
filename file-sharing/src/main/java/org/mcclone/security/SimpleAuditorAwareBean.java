package org.mcclone.security;

import org.mcclone.domain.jpa.entity.User;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * Created by mcclone on 17-1-12.
 */
@ConditionalOnProperty(prefix = "spring.jpa.audit", value = "enabled", havingValue = "true")
@EnableJpaAuditing
@Configuration
public class SimpleAuditorAwareBean implements AuditorAware<User> {

    @Override
    public User getCurrentAuditor() {
        UserPrincipal principal = SecurityUtils.getPrincipal(UserPrincipal.class);
        Object user = principal.getUser();
        if (user instanceof User)
            return (User) user;
        return null;
    }
}
