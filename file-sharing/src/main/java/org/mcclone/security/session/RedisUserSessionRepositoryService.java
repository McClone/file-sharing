package org.mcclone.security.session;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.session.FindByIndexNameSessionRepository;
import org.springframework.session.Session;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author McClone
 */
@Slf4j
public class RedisUserSessionRepositoryService implements UserSessionRepositoryService {

    public static final String key = RedisUserSessionRepositoryService.class.getName().concat(".ONLINE_USER");

    private FindByIndexNameSessionRepository<Session> sessionRepository;

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

    @Override
    public void kill(String username) {
        Map<String, Session> session = sessionRepository.findByIndexNameAndIndexValue(FindByIndexNameSessionRepository.PRINCIPAL_NAME_INDEX_NAME, username);
        session.keySet().forEach(id -> sessionRepository.delete(id));
    }

    @Resource
    public void setRedisTemplate(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Resource
    public void setSessionRepository(FindByIndexNameSessionRepository<Session> sessionRepository) {
        this.sessionRepository = sessionRepository;
    }
}
