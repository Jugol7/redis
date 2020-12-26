package zlp.redis.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;
import zlp.redis.entity.People;

/**
 * @Author zlp
 * @Description:
 * @Date 14:47:33 2020/12/25/0025
 */
@Repository
public interface IPeopleDao extends BaseMapper<People> {
}
