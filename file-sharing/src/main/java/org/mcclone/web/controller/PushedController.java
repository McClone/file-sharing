package org.mcclone.web.controller;

import lombok.extern.slf4j.Slf4j;
import org.mcclone.domain.jpa.entity.PushedMessage;
import org.mcclone.domain.jpa.repositories.PushedMessageRepository;
import org.mcclone.message.push.PushEvent;
import org.mcclone.support.ApplicationEventPublisherHolder;
import org.mcclone.web.ui.EasyUIGenerator;
import org.mcclone.web.ui.EasyUIPageRequest;
import org.mcclone.web.ui.ViewMapper;
import org.mcclone.web.view.PushedMessageView;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author McClone
 */
@RestController
@RequestMapping("/pushed")
@Slf4j
public class PushedController {

    @Resource
    private ApplicationEventPublisherHolder applicationEventPublisher;

    @Resource
    private PushedMessageRepository repository;

    @Resource(name = "pushedMessageViewMapper")
    private ViewMapper<PushedMessage, PushedMessageView> pushedMessageViewMapper;

    @PostMapping
    public ResponseEntity push(String content) {
        PushedMessage message = new PushedMessage();
        message.setPushContent(content);
        repository.save(message);
        applicationEventPublisher.publishEvent(new PushEvent(this, content));
        return ResponseEntity.ok("ok");
    }

    @GetMapping
    public ResponseEntity findAll(EasyUIPageRequest pageRequest) {
        Page<PushedMessage> messagePage = repository.findAll(EasyUIGenerator.createPageable(pageRequest));
        return ResponseEntity.ok(EasyUIGenerator.createEasyUIPage(messagePage, pushedMessageViewMapper));
    }

}
