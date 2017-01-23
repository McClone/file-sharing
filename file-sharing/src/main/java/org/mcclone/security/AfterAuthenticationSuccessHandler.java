package org.mcclone.security;

import lombok.extern.slf4j.Slf4j;
import org.mcclone.domain.entity.User;
import org.mcclone.utils.SecurityUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by McClone on 2017/1/23.
 */
@Slf4j
public class AfterAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    @Override
    protected void handle(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        super.handle(request, response, authentication);
        log.info(SecurityUtils.getUserHolder(User.class, authentication).getUser().toString());
    }
}
