package org.mcclone.message.push;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Created by McClone on 2017/1/22.
 */
@Component
@Slf4j
public class PushEventListener implements ApplicationListener<PushEvent> {

    @Resource
    private SimpMessagingTemplate template;

    @Override
    public void onApplicationEvent(PushEvent event) {
        template.convertAndSend("/topic/pushed", event.getContent());
    }
}
