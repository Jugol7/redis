package zlp.redis.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import zlp.redis.dao.PeopleDaoI;
import zlp.redis.entity.People;
import zlp.redis.service.PeopleServiceI;

import javax.annotation.Resource;

/**
 * @author zlp
 * @date 2019-11-06 18:42
 */
@Service
public class PeopleServiceImpl implements PeopleServiceI {

    private  final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private PeopleDaoI peopleDaoI;

    @Override
    public boolean addPeople(People people) {
        try {
            if (people != null) {
                peopleDaoI.addPeople(people);
                return true;
            }
        }catch (Exception e){
            logger.info("新增失败！"+e);
        }
        return false;
    }

    @Override
    public boolean updatePeople(People people) {
        try{
            peopleDaoI.updatePeople(people);
            return true;
        }catch (Exception e){
            logger.info("更新失败！"+e);
        }
        return false;
    }

    @Override
    public boolean deletePeople(int[] ids) {
        try{
            peopleDaoI.deletePeople(ids);
            return true;
        }catch (Exception e){
            logger.info("修改失败！"+e);
        }
        return false;
    }

    @Override
    public People selectPeopleById(int id) {
        try{
            People people = peopleDaoI.selectPeopleById(id);
            return people;
        }catch (Exception e){
            logger.info("修改失败！"+e);
        }
        return null;
    }
}
