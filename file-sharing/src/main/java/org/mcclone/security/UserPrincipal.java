package org.mcclone.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

/**
 * Created by mcclone on 17-1-14.
 */
public class UserPrincipal<E> extends User {
    private static final long serialVersionUID = 3739408594407047059L;

    private E principal;

    public UserPrincipal(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }

    public UserPrincipal(String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
    }

    public E getPrincipal() {
        return principal;
    }

    public void setPrincipal(E principal) {
        this.principal = principal;
    }
}
