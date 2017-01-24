package org.mcclone.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.mcclone.domain.jpa.entity.User;
import org.mcclone.domain.jpa.repositories.UserRepository;
import org.mcclone.service.UserService;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;

/**
 * Created by mcclone on 17-1-15.
 */
@Service
@Slf4j
@Transactional
public class UserServiceImpl implements UserService {

    @Resource
    private UserRepository userRepository;

    @Resource
    private StandardPasswordEncoder passwordEncoder;

    @Override
    public void createUser(String userName, String password) {
        User user = new User();
        user.setUsername(userName);
        user.setPassword(passwordEncoder.encode(password));
        this.userRepository.save(user);
        log.info("创建用户：{}", userName);
    }
}
