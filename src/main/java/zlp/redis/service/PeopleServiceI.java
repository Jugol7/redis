package zlp.redis.service;

import zlp.redis.entity.People;

/**
 * @author zlp
 * @date 2019-11-06 18:42
 */
public interface PeopleServiceI {
    /**
     * 新增
     * @param people
     * @return
     */
    boolean addPeople(People people);

    /**
     * 更新
     * @param people
     * @return
     */
    boolean updatePeople(People people);

    /**
     * 删除
     * @param ids
     * @return
     */
    boolean deletePeople(int[] ids);

    /**
     * 据id查询
     * @param id
     * @return
     */
    People selectPeopleById(int id);
}