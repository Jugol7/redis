package zlp.redis.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author ：zlp
 * @date ：2022/9/6 18:30
 * @version: 1.0
 */
@Data
public class Product implements Serializable {

    private String productId;

    private String productName;

}
