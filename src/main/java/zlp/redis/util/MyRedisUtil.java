package zlp.redis.util;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.concurrent.TimeUnit;

/**
 * @author zlp
 * @date 2019-11-06 18:12
 */
@Slf4j
public class MyRedisUtil {

    private final Logger logger = LoggerFactory.getLogger(MyRedisUtil.class);

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 设置某个key的生效时间
     * @param key
     * @param time
     * @return
     */
    public boolean setExpire(String key, long time){
        try {
            if (time > 0) {
                redisTemplate.expire(key, time, TimeUnit.SECONDS);
                return true;
            }
        }catch (Exception e){
            logger.info("/-/-/-/-/-/-/-/-"+e.getMessage());
        }
        return false;
    }

    /**
     * 获取某个key的过期时间
     * @param key
     * @return
     */
    public long getExpire(String key){
        return redisTemplate.getExpire(key);
    }

    /**
     * 是否存在
     * @param key
     * @return
     */
    public boolean isExits(String key){
        return redisTemplate.hasKey(key);
    }


}
