package top.luoren.common.api.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * 接口返回数据的格式
 *
 * @author luoren
 * @date 2019/9/3 16:39
 */
@Data
public class Result implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final String INTERNAL_SERVER_ERROR_500 = "500";
    public static final String OK_200 = "200";

    /**
     * 状态码，标识请求成功与否，如 [1:成功；-1:失败]
     */
    private int status = 1;

    /**
     * 状态码
     */
    private String code = "";

    /**
     * 消息
     */
    private String message = "";

    /**
     * 返回结果，通常是 Bean 对象对应的 JSON 数据, 通常为了应对不同返回值类型，将其声明为泛型类型
     */
    private Object resultBody;

    /**
     * 时间戳
     */
    private long timestamp = System.currentTimeMillis();

    public Result() {

    }

    public Result error500(String message) {
        this.status = -1;
        this.code = INTERNAL_SERVER_ERROR_500;
        this.message = "内部服务器错误，请联系管理员";
        return this;
    }

    public Result success(String message) {
        this.status = 1;
        this.code = OK_200;
        this.message = "成功";
        return this;
    }

    public static Result ok() {
        Result r = new Result();
        r.status = 1;
        r.code = OK_200;
        r.message = "成功";
        return r;
    }

    public static Result ok(String message) {
        Result r = new Result();
        r.status = 1;
        r.code = OK_200;
        r.message = message;
        return r;
    }

    public static Result ok(Object data) {
        Result r = new Result();
        r.status = 1;
        r.code = OK_200;
        r.message = "成功";
        r.resultBody = data;
        return r;
    }

    public static Result error(String message) {
        Result r = new Result();
        r.status = -1;
        r.code = INTERNAL_SERVER_ERROR_500;
        r.message = message;
        return r;
    }

    public static Result error(String code, String message) {
        Result r = new Result();
        r.status = -1;
        r.code = code;
        r.message = message;
        return r;
    }
}
