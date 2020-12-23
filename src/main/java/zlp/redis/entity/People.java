package zlp.redis.entity;

import com.alibaba.fastjson.JSONObject;

/**
 * @author zlp
 * @date 2019-11-06 18:10
 */
public class People {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    /** 编号 */
    private int id;
    /** 姓名 */
    private String name;

    /** 年龄 */
    private int age;

    public People(){
    }


    /**
     * 获取编号
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     * 设置编号
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * 获取姓名
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * 设置姓名
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * 获取年龄
     * @return  age
     */
    public int getAge() {
        return age;
    }
    /**
     * 设置年龄
     * @param int age
     */
    public void setAge(int age) {
        this.age = age;
    }
    /**
     *
     */
    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }


}
