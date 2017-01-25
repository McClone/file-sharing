package org.mcclone.security;

import org.mcclone.domain.jpa.entity.User;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * @author McClone
 */
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
