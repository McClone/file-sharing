package org.mcclone.security.session;

import lombok.extern.slf4j.Slf4j;
import org.mcclone.security.SecurityUtils;
import org.mcclone.utils.ServletUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.session.FindByIndexNameSessionRepository;

/**
 * @author McClone
 */
@Slf4j
public class AuthenticationSuccessEventListener implements ApplicationListener<AuthenticationSuccessEvent> {

    private UserSessionRepositoryService sessionRepositoryService;

    @Override
    public void onApplicationEvent(AuthenticationSuccessEvent event) {
        Authentication authentication = event.getAuthentication();
        UserDetails userDetails = SecurityUtils.getPrincipal(authentication, UserDetails.class);
        ServletUtils.getCurrentSession().setAttribute(FindByIndexNameSessionRepository.PRINCIPAL_NAME_INDEX_NAME, userDetails.getUsername());
        sessionRepositoryService.save(userDetails.getUsername(), userDetails);
        log.info(userDetails.getUsername() + "登陆成功");
    }

    @Autowired
    public void setSessionRepositoryService(UserSessionRepositoryService sessionRepositoryService) {
        this.sessionRepositoryService = sessionRepositoryService;
    }
}
