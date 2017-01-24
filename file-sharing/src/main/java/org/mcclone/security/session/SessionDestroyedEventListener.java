package org.mcclone.security.session;

import org.mcclone.security.SecurityUtils;
import org.springframework.context.ApplicationListener;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.session.events.SessionDestroyedEvent;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2017/1/24.
 */
@Component
public class SessionDestroyedEventListener implements ApplicationListener<SessionDestroyedEvent> {

    @Resource(name = "redisUserSessionRepositoryService")
    private UserSessionRepositoryService sessionRepositoryService;

    @Override
    public void onApplicationEvent(SessionDestroyedEvent event) {
        String username = SecurityUtils.getPrincipal(UserDetails.class).getUsername();
        sessionRepositoryService.delete(username);
    }
}
