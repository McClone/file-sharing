package org.mcclone.security.session;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Administrator on 2017/1/24.
 */
@Service("redisUserSessionRepositoryService")
public class RedisUserSessionRepositoryService implements UserSessionRepositoryService {

    public static final String key = RedisUserSessionRepositoryService.class.getName().concat(".ONLINE_USER");

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public void save(String userKey, Object principal) {
        redisTemplate.opsForHash().put(key, userKey, principal);
    }

    @Override
    public void delete(String userKey) {
        redisTemplate.opsForHash().delete(key, userKey);
    }

    @Override
    public List<Object> findAll() {
        return redisTemplate.opsForHash().values(key);
    }

}
