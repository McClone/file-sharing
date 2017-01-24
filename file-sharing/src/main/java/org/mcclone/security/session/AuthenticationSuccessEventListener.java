package org.mcclone.security.session;

import lombok.extern.slf4j.Slf4j;
import org.mcclone.security.SecurityUtils;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2017/1/24.
 */
@Component
@Slf4j
public class AuthenticationSuccessEventListener implements ApplicationListener<AuthenticationSuccessEvent> {

    @Resource(name = "redisUserSessionRepositoryService")
    private UserSessionRepositoryService sessionRepositoryService;

    @Override
    public void onApplicationEvent(AuthenticationSuccessEvent event) {
        sessionRepositoryService.save(SecurityUtils.getPrincipal(UserDetails.class).getUsername(), SecurityUtils.getPrincipal());
    }
}
