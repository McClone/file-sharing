package org.mcclone.web.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Administrator on 2017/1/24.
 */
@RestController
@RequestMapping("/sessions")
@Slf4j
public class UserSessionController {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

//    @Resource(name = "sessionRepository")
//    private FindByIndexNameSessionRepository<Session> sessionRepository;

    @GetMapping
    public ResponseEntity findAll() {
        List<Object> usernames = redisTemplate.opsForHash().values("online_user");
        return ResponseEntity.ok(usernames);
//        Map<String, Session> sessionIdToSession = this.sessionRepository.findByIndexNameAndIndexValue(FindByIndexNameSessionRepository.PRINCIPAL_NAME_INDEX_NAME, "admin");
//        log.info(sessionIdToSession.toString());
    }
}
