package zlp.redis.dao;

import org.apache.ibatis.annotations.Mapper;
import zlp.redis.entity.People;

/**
 * @author zlp
 * @date 2019-11-06 18:25
 */
public interface PeopleDaoI {

    /**
     * 新增
     * @param people
     * @return
     */
    void addPeople(People people);

    /**
     * 更新
     * @param people
     * @return
     */
    void updatePeople(People people);

    /**
     * 删除
     * @param ids
     * @return
     */
    void deletePeople(int[] ids);

    /**
     * 据id查询
     * @param id
     * @return
     */
    People selectPeopleById(int id);
}
