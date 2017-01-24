package org.mcclone.security;

import lombok.extern.slf4j.Slf4j;
import org.mcclone.utils.SecurityUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.session.FindByIndexNameSessionRepository;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by McClone on 2017/1/23.
 */
@Slf4j
public class AfterAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    protected void handle(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        super.handle(request, response, authentication);
        String username = SecurityUtils.getUserDetails(authentication).getUsername();
        log.info(username + "登陆成功");
        request.getSession().setAttribute(FindByIndexNameSessionRepository.PRINCIPAL_NAME_INDEX_NAME, username);
        redisTemplate.opsForHash().put("online_user", username, authentication.getPrincipal());
    }
}
