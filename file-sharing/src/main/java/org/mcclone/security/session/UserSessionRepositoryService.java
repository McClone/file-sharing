package org.mcclone.security.session;

import java.util.List;

/**
 * Created by Administrator on 2017/1/24.
 */
public interface UserSessionRepositoryService {

    void save(String userKey, Object principal);

    void delete(String userKey);

    List<Object> findAll();

}
