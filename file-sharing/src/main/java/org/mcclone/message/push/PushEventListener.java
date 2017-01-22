package org.mcclone.message.push;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * Created by McClone on 2017/1/22.
 */
@Component
@Slf4j
public class PushEventListener implements ApplicationListener<PushEvent> {

    @Override
    public void onApplicationEvent(PushEvent event) {
        log.info(event.getTitle());
    }
}
