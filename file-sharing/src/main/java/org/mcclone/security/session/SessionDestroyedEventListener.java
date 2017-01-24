package org.mcclone.security.session;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.session.FindByIndexNameSessionRepository;
import org.springframework.session.events.SessionDestroyedEvent;

/**
 * @author McClone
 */
@Slf4j
public class SessionDestroyedEventListener implements ApplicationListener<SessionDestroyedEvent> {

    private UserSessionRepositoryService sessionRepositoryService;

    @Override
    public void onApplicationEvent(SessionDestroyedEvent event) {
        String username = event.getSession().getAttribute(FindByIndexNameSessionRepository.PRINCIPAL_NAME_INDEX_NAME);
        log.info(username + "登出并删除session");
        sessionRepositoryService.delete(username);
    }

    @Autowired
    public void setSessionRepositoryService(UserSessionRepositoryService sessionRepositoryService) {
        this.sessionRepositoryService = sessionRepositoryService;
    }
}
