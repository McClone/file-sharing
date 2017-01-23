package org.mcclone.web.controller;

import lombok.extern.slf4j.Slf4j;
import org.mcclone.message.push.PushEvent;
import org.mcclone.support.ApplicationEventPublisherHolder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by McClone on 2017/1/22.
 */
@RestController
@RequestMapping("/pushed")
@Slf4j
public class PushedController {

    @Resource
    private ApplicationEventPublisherHolder applicationEventPublisher;

    @PostMapping
    public ResponseEntity push(String content) {
        // TODO: 2017/1/23 save to database
        applicationEventPublisher.publishEvent(new PushEvent(this, content));
        return ResponseEntity.ok("ok");
    }

    @GetMapping
    public void findAll() {
        // TODO: 2017/1/23 findAll
    }

}
