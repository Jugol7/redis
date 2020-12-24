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

    private static final Logger LOGGER = LoggerFactory.getLogger(PeopleController.class);

    @Resource
    private PeopleServiceI peopleServiceI;

    @PostMapping(value = "add")
    public boolean addPeople(@RequestBody People people) {
        LOGGER.info("/-/-/-/-/新增ing");
        return peopleServiceI.addPeople(people);
    }

    @PostMapping("/update")
    public boolean updatePeople(@RequestBody People people) {
        LOGGER.info("/-/-/-/-/更新ing");
        return peopleServiceI.updatePeople(people);
    }

    @PostMapping("/delete")
    public boolean deletePeople(@RequestParam int[] ids) {
        LOGGER.info("/-/-/-/-/删除ing");
        return peopleServiceI.deletePeople(ids);
    }

    @GetMapping("/selectById")
    public People selectPeopleById(@RequestParam Integer id) {
        LOGGER.info("/-/-/-/-/查询ing");
        return peopleServiceI.selectPeopleById(id);
    }



}
