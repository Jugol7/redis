package zlp.redis.config.enums;

import lombok.Getter;

/**
 * 异常类型枚举
 *
 * @Author zlp
 * @Description:
 * @Date 15:21:56 2020/12/25/0025
 */
@Getter
public enum ExceptionEnum {
    /**
     * 业务相关异常 10000-20000
     */
    SUCCESS("0", "操作成功"),
    BASE_ERROR("99999", "处理异常"),
    DATA_NOTFOUND_ERROR("10003", "未请求到相关数据"),
    DATA_REPEAT_ERROR("10004", "数据已经存在"),
    NULL_PARAMS("10005", "参数为空"),
    TRAN_PARAMS_ERROR("10006", "参数转换异常"),
    NOT_BINDING_ERROR("10007", "未开通"),
    /**
     * 系统相关异常 20000-30000
     */
    PARAMS_ERROR("20001", "参数异常"),
    TOKEN_NOTFOUND_ERROR("20002", "token不存在"),
    TOKEN_ERROR("20003", "token不合法"),
    METHOD_NOT_SUPPORTED_ERROR("20004", "请求方式不支持"),
    UNKNOWN_ERROR("20005", "服务器异常"),
    NOTFOUND_ERROR("20006", "资源请求未找到"),
    REQUEST_URL_ERROR("20007", "请求不合法"),
    DATATYPE_ERROR("20008", "数据类型未找到"),
    PARAMS_LENGTH_ERROR("20009", "参数长度不得少于4个字符"),
    DICT_XML_ERROR("20020", "字典项xml异常"),
    IOE_ERROR("20021", "IO异常"),
    JSON_CONVERT_ERROR("20022", "JSON转换异常"),

    METHOD_NOT_ABLE("20030", "方法未实现"),

    /**
     * 第三方接口异常信息
     */
    UN_DATA("300000", "无数据"),
    REQUEST_FAIL("300001", "请求失败"),
    CODE_300002("300002", "账号失效"),
    CODE_300003("300003", "账号过期"),
    CODE_300004("300004", "访问频率过快"),
    CODE_300005("300005", "无权限访问此api"),
    CODE_300006("300006", "余额不足"),
    CODE_300007("300007", "剩余次数不足"),
    CODE_300008("300008", "缺少必要参数"),
    CODE_300009("300009", "账号信息有误"),
    CODE_300010("300010", "URL不存在");

    private final String code;
    private final String message;

    ExceptionEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
