package org.mcclone.security;

import org.mcclone.domain.entity.User;
import org.mcclone.utils.SecurityUtils;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * Created by mcclone on 17-1-12.
 */
@ConditionalOnProperty(prefix = "spring.jpa.audit", value = "enabled", havingValue = "true")
@EnableJpaAuditing
@Configuration
public class SimpleAuditorAwareBean implements AuditorAware<User> {

    @Override
    public User getCurrentAuditor() {
        UserDetails userDetails = SecurityUtils.getUserDetails();
        if (userDetails instanceof UserPrincipal)
            return (User) ((UserPrincipal) userDetails).getUser();
        return null;
    }
}
