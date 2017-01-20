package org.mcclone.web.controller;

import org.mcclone.domain.entity.User;
import org.mcclone.domain.repositories.UserRepository;
import org.mcclone.web.ui.EasyUIGenerator;
import org.mcclone.web.ui.EasyUIPageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by mcclone on 17-1-13.
 */
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping
    public ResponseEntity findAll(EasyUIPageRequest pageRequest) {
        Page<User> userPage = userRepository.findAll(EasyUIGenerator.createPageable(pageRequest));
        return ResponseEntity.ok(EasyUIGenerator.createEasyUIPage(userPage));
    }
}
