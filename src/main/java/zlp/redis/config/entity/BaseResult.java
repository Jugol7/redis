package zlp.redis.config.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 返回基本对象
 *
 * @Author zlp
 * @Description:
 * @Date 15:12:43 2020/12/25/0025
 */
@Data
public class BaseResult<T extends Serializable> implements Serializable {

    private Integer code;
    private String message;
    private T data;

}
