package org.mcclone.security;

import org.mcclone.domain.jpa.entity.User;
import org.mcclone.domain.jpa.repositories.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mcclone on 17-1-13.
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Resource
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("不存在此帐号");
        }
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority("USER");
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        grantedAuthorities.add(authority);
        UserPrincipal principal = new UserPrincipal(user.getUsername(), user.getPassword(), grantedAuthorities);
        principal.setUser(user);
        return principal;
    }
}
