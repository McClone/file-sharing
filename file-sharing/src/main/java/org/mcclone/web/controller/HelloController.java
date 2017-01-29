package org.mcclone.web.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

/**
 * @author McClone
 */
@Controller
@Slf4j
public class HelloController {

    @MessageMapping("/hello")
    public String hello(String username) {
        log.info(username);
        return username;
    }
}
