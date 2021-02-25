package zlp.redis.config.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import zlp.redis.config.enums.ExceptionEnum;

/**
 * @Author zlp
 * @Description:
 * @Date 15:12:20 2020/12/25/0025
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class BaseException extends RuntimeException {

    private static final long serialVersionUID = -2555287068293283359L;
    private final String code;
    private final String msg;
    private final String method;
    private final Boolean flag;

    public BaseException(ExceptionEnum constant) {
        this.code = constant.getCode();
        this.msg = constant.getMessage();
        this.method = null;
        this.flag = false;
    }

}
