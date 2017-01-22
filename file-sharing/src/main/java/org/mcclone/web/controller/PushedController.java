package org.mcclone.web.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * Created by McClone on 2017/1/22.
 */
@Controller
@RequestMapping("/pushed")
@Slf4j
public class PushedController {

    @Resource
    private SimpMessagingTemplate template;

    @PostMapping
    @ResponseBody
    public ResponseEntity push(String title, String content) {
        String text = String.format("title: %s,content= %s .", title, content);
        log.info(text);
        template.convertAndSend("/topic/greetings", text);
        return ResponseEntity.ok("ok");
    }
}
