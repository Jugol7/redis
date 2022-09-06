package zlp.redis.util.lock;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.config.SingleServerConfig;
import org.springframework.stereotype.Component;

/**
 * @author ：zlp
 * @date ：2022/9/4 23:21
 * @version: 1.0
 */
@Component
public class RedissonManager {

    public RedissonClient getRedisClient(){
        // 实际项目中可通过读取配置文件
        Config config = new Config();
        SingleServerConfig singleServerConfig = config.useSingleServer().setAddress("redis://192.168.0.100:6379");
        singleServerConfig.setPassword("1234");
        return Redisson.create(config);
    }

}
