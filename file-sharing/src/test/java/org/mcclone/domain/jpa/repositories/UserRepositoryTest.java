package org.mcclone.domain.jpa.repositories;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mcclone.domain.jpa.entity.User;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @author McClone
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
        System.out.println(user);
        this.userRepository.save(user);
    }

}