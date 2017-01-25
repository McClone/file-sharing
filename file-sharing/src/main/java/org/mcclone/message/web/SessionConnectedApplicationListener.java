package org.mcclone.message.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;

/**
 * @author McClone
 */
@Slf4j
@Component
public class SessionConnectedApplicationListener implements ApplicationListener<SessionConnectedEvent> {

    @Override
    public void onApplicationEvent(SessionConnectedEvent event) {
        // TODO: 2017/1/22 可以知道登陆用户
        log.info("WebSocker连接成功.");
    }
}
