package org.mcclone.domain.repositories;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mcclone.domain.entity.User;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * Created by McClone on 2017/1/21.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTest {

    @Resource
    private UserRepository userRepository;

    @Resource
    private PasswordEncoder passwordEncoder;

    @Test
    public void save() throws Exception {
        User user = new User();
        user.setUsername("admin");
        user.setPassword(passwordEncoder.encode("123"));
        this.userRepository.save(user);
    }

}