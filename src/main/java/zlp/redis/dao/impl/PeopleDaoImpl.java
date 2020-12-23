package zlp.redis.dao.impl;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;
import zlp.redis.dao.PeopleDaoI;
import zlp.redis.entity.People;

import javax.annotation.Resource;

/**
 * @author zlp
 * @date 2019-11-06 18:28
 */
@Slf4j
@Repository
public class PeopleDaoImpl implements PeopleDaoI {

    private final Logger logger = LoggerFactory.getLogger(PeopleDaoImpl.class);

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public void addPeople(People people) {
        try {
            //这里要 people.toString()
            redisTemplate.opsForValue().set(String.valueOf(people.getId()), people);
        } catch (Exception e) {
            logger.info(e.getMessage());
        }
    }

    @Override
    public void updatePeople(People people) {
        try {
            //这里要 people.toString()
            redisTemplate.opsForValue().set(String.valueOf(people.getId()), people);
        } catch (Exception e) {
            logger.info(e.getMessage());
        }
    }

    @Override
    public void deletePeople(int[] ids) {
        try {
            redisTemplate.delete(CollectionUtils.arrayToList(ids));
        } catch (Exception e) {
            logger.info(e.getMessage());
        }
    }

    @Override
    public People selectPeopleById(int id) {
        try {
            Object object = redisTemplate.opsForValue().get(id);
            return ((People) object);
        } catch (Exception e) {
            logger.info(e.getMessage());
        }
        return null;
    }
}
