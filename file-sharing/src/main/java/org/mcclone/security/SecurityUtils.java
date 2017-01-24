package org.mcclone.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * Created by mcclone on 17-1-14.
 */
public abstract class SecurityUtils {

    public static Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    public static Object getPrincipal() {
        return getAuthentication().getPrincipal();
    }

    public static Object getPrincipal(Authentication authentication) {
        return authentication.getPrincipal();
    }

    public static <T> T getPrincipal(Authentication authentication, Class<T> t) {
        Object principal = authentication.getPrincipal();
        if (principal != null)
            return t.cast(principal);
        else
            throw new IllegalArgumentException();
    }

    public static <T> T getPrincipal(Class<T> t) {
        Authentication authentication = getAuthentication();
        Object principal = authentication.getPrincipal();
        if (principal != null)
            return t.cast(principal);
        else
            throw new IllegalArgumentException();
    }

}
