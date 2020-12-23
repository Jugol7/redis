package zlp.redis.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import zlp.redis.entity.People;
import zlp.redis.service.PeopleServiceI;

import javax.annotation.Resource;

/**
 * @author zlp
 * @date 2019-11-06 18:50
 */
@RestController
@RequestMapping(value = "/people")
public class PeopleController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private PeopleServiceI peopleServiceI;

    @PostMapping(value = "add")
    public boolean addPeople(@RequestBody People people) {
        logger.info("/-/-/-/-/新增ing");
        return peopleServiceI.addPeople(people);
    }

    @PostMapping("/update")
    public boolean updatePeople(@RequestBody People people) {
        logger.info("/-/-/-/-/更新ing");
        return peopleServiceI.updatePeople(people);
    }

    @PostMapping("/delete")
    public boolean deletePeople(@RequestBody int[] ids) {
        logger.info("/-/-/-/-/更新ing");
        return peopleServiceI.deletePeople(ids);
    }

    @PostMapping("/selectById")
    public People selectPeopleById(@RequestBody int id) {
        logger.info("/-/-/-/-/更新ing");
        return peopleServiceI.selectPeopleById(id);
    }



}
