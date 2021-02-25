package zlp.redis.config.entity;

import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 数据分页返回对象
 *
 * @Author zlp
 * @Description:
 * @Date 15:12:58 2020/12/25/0025
 */
@Data
public class PageBaseResult<T extends Serializable> implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 开始页
     */
    private Long start;

    /**
     * 每页显示条数
     */
    private Long limit;

    /**
     * 总条数
     */
    private Long total;

    /**
     * 总页数
     */
    private Long pages;

    /**
     * 集合数据
     */
    private List<T> rows;

    /**
     * 将Mybatis中的Ipage转换成自定义分页对象
     *
     * @param list
     */
    public PageBaseResult(IPage<T> list) {
        if (list != null) {
            this.start = list.getCurrent();
            this.limit = list.getSize();
            this.total = list.getTotal();
            this.pages = list.getPages();
            this.rows = list.getRecords();
        }
    }

}
