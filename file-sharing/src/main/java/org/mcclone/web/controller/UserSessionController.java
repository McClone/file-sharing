package org.mcclone.web.controller;

import lombok.extern.slf4j.Slf4j;
import org.mcclone.security.session.UserSessionRepositoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Administrator on 2017/1/24.
 */
@RestController
@RequestMapping("/sessions")
@Slf4j
public class UserSessionController {

    @Resource(name = "redisUserSessionRepositoryService")
    private UserSessionRepositoryService sessionRepositoryService;

    @GetMapping
    public ResponseEntity findAll() {
        List<Object> usernames = sessionRepositoryService.findAll();
        return ResponseEntity.ok(usernames);
    }

    @DeleteMapping("/{username}")
    @ResponseStatus(HttpStatus.OK)
    public void killUser(@PathVariable String username) {
        sessionRepositoryService.kill(username);
        log.info(username + "被踢人");
    }
}
