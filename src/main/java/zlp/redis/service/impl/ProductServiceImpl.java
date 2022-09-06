package zlp.redis.service.impl;

import org.redisson.api.RLock;
import org.redisson.api.RReadWriteLock;
import org.springframework.beans.factory.annotation.Autowired;
import zlp.redis.entity.Product;
import zlp.redis.util.MyRedisUtil;
import zlp.redis.util.lock.RedissonManager;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @author ：zlp
 * @date ：2022/9/6 18:29
 * @version: 1.0
 */
public class ProductServiceImpl {

    public static final String CACHE_KEY = "HOT_PRODUCT_CACHE_KEY";

    public static final String HOT_CACHE_KEY_LOCK_NAME = "lock:hotCacheProduct";

    public static final String UPDATE_CACHE_KEY_LOCK_NAME = "lock:updateCacheProduct";

    public static final Integer PRODUCT_CACHE_TIMEOUT = 60 * 60 * 24;

    /**
     * JVM级别的缓存  也就是内存
     * 性能是非常高的
     * 缺陷：
     * 1. 是单个JVM的，容量有限
     * 2. 分布式不适用
     */
    private static Map<String, Product> cacheMap = new HashMap<>();

    @Autowired
    private RedissonManager redissonManager;

    @Autowired
    private MyRedisUtil<String, Product> myRedisUtil;

    public Product getProduct(String productId) throws InterruptedException {
        String hotProductCacheKey = CACHE_KEY + productId;
        // 查缓存
        Product product = getCache(hotProductCacheKey);
        if (Optional.ofNullable(product).isPresent()) {
            return product;
        }
        // 加锁
        RLock hotCacheProductLock = redissonManager.getRedisClient().getLock(HOT_CACHE_KEY_LOCK_NAME + productId);
        // hotCacheProductLock.lock(); // 底层也是 setnx
        // tryLock 性能更好(串行转并行)，大概意思是 在等待具体时间后，拿不到锁就做其他操作（降级）
        // 这个时间需要认真考虑，要保证拿到锁的线程能在此事件内，重建缓存
        if (!hotCacheProductLock.tryLock(2, TimeUnit.SECONDS)) {
            product = getCache(hotProductCacheKey);
            if (Optional.ofNullable(product).isPresent()) {
                return product;
            }
            // 在等待时间内没有成功重建缓存，需要其他处理方式
            return new Product();
        }
        try {
            // 重查缓存，保证有效请求到数据库  设计思想：DCL
            product = getCache(hotProductCacheKey);
            if (Optional.ofNullable(product).isPresent()) {
                return product;
            }
            // 会出现数据库缓存不一致现象，两线程  一读一写，会导致脏缓存  加读写锁(比普通锁性能更好)  读读不会阻塞  写会
            // 同样的在更新商品的地方加上 写锁
            RReadWriteLock readWriteLock = redissonManager.getRedisClient().getReadWriteLock(UPDATE_CACHE_KEY_LOCK_NAME + productId);
            RLock lock = readWriteLock.readLock();
            lock.lock();
            try {
                // 模拟查库
                product = new Product();
                product.setProductId(productId);
                product.setProductName("测试商品");
                // 可能查不到
                if (Optional.ofNullable(product).isPresent()) {
                    myRedisUtil.set(hotProductCacheKey, product, getProductCacheTimeOut());
                    // 将数据放入JVM缓存中  更新的时候也放
                    cacheMap.put(hotProductCacheKey, product);
                } else {
                    // TODO 可在此设置空缓存
                    myRedisUtil.set(hotProductCacheKey, product, getProductCacheTimeOut());
                }
            } finally {
                lock.unlock();
            }

        } finally {
            hotCacheProductLock.unlock();
        }
        return product;
    }

    public Integer getProductCacheTimeOut() {
        return PRODUCT_CACHE_TIMEOUT + new Random().nextInt(5) * 60 * 60;
    }

    public Product getCache(String hotProductCacheKey) {
        // 查jvm缓存
        Product product = cacheMap.get(hotProductCacheKey);
        if (Optional.ofNullable(product).isPresent()) {
            return product;
        }
        // 查缓存
        product = myRedisUtil.get(hotProductCacheKey);
        if (Optional.ofNullable(product).isPresent()) {
            // 读延期  加随机时间  防止缓存同一时间失效，防止缓存击穿
            myRedisUtil.setExpire(hotProductCacheKey, getProductCacheTimeOut());
        }
        return product;
    }

}
