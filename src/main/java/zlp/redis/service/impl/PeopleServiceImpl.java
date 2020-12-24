package zlp.redis.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import zlp.redis.entity.People;
import zlp.redis.service.PeopleServiceI;

import javax.annotation.Resource;

/**
 * @author zlp
 * @date 2019-11-06 18:42
 */
@Service
public class PeopleServiceImpl implements PeopleServiceI {

    private static final Logger LOGGER = LoggerFactory.getLogger(PeopleServiceImpl.class);

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public boolean addPeople(People people) {
        try {
            if (people != null) {
                //这里要 people.toString()
                redisTemplate.opsForValue().set(String.valueOf(people.getId()), people);
                return true;
            }
        } catch (Exception e) {
            LOGGER.info("新增失败！{}", e.getMessage());
        }
        return false;
    }

    @Override
    public boolean updatePeople(People people) {
        try {
            //这里要 people.toString()
            redisTemplate.opsForValue().set(String.valueOf(people.getId()), people);
            return true;
        } catch (Exception e) {
            LOGGER.info("更新失败！{}", e.getMessage());
        }
        return false;
    }

    @Override
    public boolean deletePeople(int[] ids) {
        try {
            redisTemplate.delete(CollectionUtils.arrayToList(ids));
            return true;
        } catch (Exception e) {
            LOGGER.info("修改失败！{}", e.getMessage());
        }
        return false;
    }

    @Override
    public People selectPeopleById(int id) {
        try {
            return (People) redisTemplate.opsForValue().get(id);
        } catch (Exception e) {
            LOGGER.info("修改失败！{}", e.getMessage());
        }
        return null;
    }
}
