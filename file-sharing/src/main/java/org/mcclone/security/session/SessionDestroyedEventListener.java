package org.mcclone.security.session;

import org.springframework.context.ApplicationListener;
import org.springframework.session.FindByIndexNameSessionRepository;
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
        String username = event.getSession().getAttribute(FindByIndexNameSessionRepository.PRINCIPAL_NAME_INDEX_NAME);
        sessionRepositoryService.delete(username);
    }
}
