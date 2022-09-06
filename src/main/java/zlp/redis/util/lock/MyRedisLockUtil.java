package zlp.redis.util.lock;

import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * 分布式锁简单实现
 * 注意：在Redis集群多节点步数情况下，会出现不一致问题，导致锁并发安全
 * 解释：Redisson1 通过主节点lock到A资源，主节点出现宕机或者其他问题，不能即使将数据同步到从节点
 *      Redisson2 通过从节点也去lock到A资源，这是由于主节点故障，也可以对A资源进行上锁
 * 解决方案 RedLock （存在争议）
 * @author ：zlp
 * @date ：2022/9/4 22:47
 * @version: 1.0
 */
@Component
@Slf4j
public class MyRedisLockUtil {

    @Autowired
    private RedissonManager redissonManager;

    /**
     * 默认锁过期时间
     */
    public static final Long TIME_OUT = 10L;

    public void lock(String lockName) {
        RLock lock = redissonManager.getRedisClient().getLock(lockName);
        lock.lock(TIME_OUT, TimeUnit.SECONDS);
    }

    public void lock(String lockName, long time) {
        RLock lock = redissonManager.getRedisClient().getLock(lockName);
        lock.lock(time, TimeUnit.SECONDS);
    }

    /**
     * 加锁操作(tryLock锁，没有等待时间）
     *
     * @param lockName  锁名称
     * @param leaseTime 锁有效时间
     */
    public boolean tryLock(String lockName, long leaseTime) {

        RLock rLock = redissonManager.getRedisClient().getLock(lockName);
        boolean getLock = false;
        try {
            getLock = rLock.tryLock(leaseTime, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            log.error("获取Redisson分布式锁[异常]，lockName=" + lockName, e);
            e.printStackTrace();
            return false;
        }
        return getLock;
    }

    /**
     * 加锁操作(tryLock锁，有等待时间）
     *
     * @param lockName  锁名称
     * @param leaseTime 锁有效时间
     * @param waitTime  等待时间
     */
    public boolean tryLock(String lockName, long leaseTime, long waitTime) {

        RLock lock = redissonManager.getRedisClient().getLock(lockName);
        boolean getLock = false;
        try {
            getLock = lock.tryLock(waitTime, leaseTime, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            log.error("获取Redisson分布式锁失败，lockName=" + lockName, e);
            e.printStackTrace();
            return false;
        }
        return getLock;
    }

    /**
     * 解锁
     *
     * @param lockName 锁名称
     */
    public void unlock(String lockName) {
        redissonManager.getRedisClient().getLock(lockName).unlock();
    }

    /**
     * 判断该锁是否已经被线程持有
     *
     * @param lockName 锁名称
     */
    public boolean isLockByMe(String lockName) {
        RLock rLock = redissonManager.getRedisClient().getLock(lockName);
        return rLock.isLocked();
    }


    /**
     * 判断该线程是否持有当前锁
     *
     * @param lockName 锁名称
     */
    public boolean isHeldByCurrentThread(String lockName) {
        RLock rLock = redissonManager.getRedisClient().getLock(lockName);
        return rLock.isHeldByCurrentThread();
    }

}
