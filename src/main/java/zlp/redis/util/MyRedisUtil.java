package zlp.redis.util;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

/**
 * @author zlp
 * @date 2019-11-06 18:12
 */
@Slf4j
@Component
public class MyRedisUtil<K, V> {

    private static final Logger LOGGER = LoggerFactory.getLogger(MyRedisUtil.class);

    @Autowired
    private RedisTemplate<K, V> redisTemplate;

    @PostConstruct
    private void init() {
        // 初始化
        if (!Optional.ofNullable(redisTemplate).isPresent()) {
            redisTemplate = new RedisTemplate<>();
        }
    }

    /**
     * 设置某个key的生效时间
     *
     * @param key
     * @param time
     * @return
     */
    public Boolean setExpire(K key, long time) {
        try {
            if (time > 0) {
                redisTemplate.expire(key, time, TimeUnit.SECONDS);
                return true;
            }
        } catch (Exception e) {
            LOGGER.info("/-/-/-/-/-/-/-/-{}", e.getMessage());
        }
        return false;
    }

    /**
     * 获取某个key的过期时间
     *
     * @param key
     * @return
     */
    public Long getExpire(K key) {
        return redisTemplate.getExpire(key);
    }

    /**
     * 是否存在
     *
     * @param key
     * @return
     */
    public Boolean isExits(K key) {
        return redisTemplate.hasKey(key);
    }


}
