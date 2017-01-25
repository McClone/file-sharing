package org.mcclone.security.session;

import java.util.List;

/**
 * @author McClone
 */
public interface UserSessionRepositoryService {

    void save(String userKey, Object principal);

    void delete(String userKey);

    List<Object> findAll();

    void kill(String username);
}
