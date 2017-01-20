package org.mcclone.utils;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * Created by mcclone on 17-1-14.
 */
public abstract class SecurityUtils {

    public static Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    public static boolean isAuthenticated() {
        Authentication authentication = getAuthentication();
        return authentication != null && authentication.isAuthenticated();
    }

    public static Object getPrincipal() {
        return getAuthentication().getPrincipal();
    }

    public static UserDetails getUserDetails() {
        if (isAuthenticated() && getPrincipal() instanceof UserDetails) {
            return (UserDetails) getPrincipal();
        }
        return null;
    }
}
