package org.mcclone.config;

import org.mcclone.domain.entity.User;
import org.mcclone.security.UserPrincipal;
import org.mcclone.utils.SecurityUtils;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * Created by mcclone on 17-1-12.
 */
@Configuration
public class SimpleAuditorAwareBean implements AuditorAware<User> {

    @Override
    public User getCurrentAuditor() {
        UserDetails userDetails = SecurityUtils.getUserDetails();
        if (userDetails instanceof UserPrincipal)
            return (User) ((UserPrincipal) userDetails).getPrincipal();
        return null;
    }
}
