package org.mcclone.web.controller;

import lombok.extern.slf4j.Slf4j;
import org.mcclone.domain.jpa.entity.User;
import org.mcclone.domain.jpa.repositories.UserRepository;
import org.mcclone.web.ui.EasyUIGenerator;
import org.mcclone.web.ui.EasyUIPageRequest;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author McClone
 */
@RestController
@RequestMapping("/users")
@Slf4j
public class UserController {

    @Resource
    private UserRepository userRepository;

    @GetMapping
    public ResponseEntity findAll(EasyUIPageRequest pageRequest) {
        Page<User> userPage = userRepository.findAll(EasyUIGenerator.createPageable(pageRequest));
        return ResponseEntity.ok(EasyUIGenerator.createEasyUIPage(userPage));
    }


}
