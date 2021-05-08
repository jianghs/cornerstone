package com.jiuv.cornerstone.base.entity;

import com.jiuv.cornerstone.base.enums.ResultCode;
import lombok.Data;

import java.io.Serializable;

/**
 * @className: ResultVO
 * @description:
 * @author: jianghs430
 * @createDate: 2020/8/18 9:43
 * @version: 1.0
 */
@Data
public class Result<T> implements Serializable {
    private Boolean status;
    private Integer code;
    private String msg;
    private T data;

    public Result(T data) {
        this(true, ResultCode.SUCCESS, data);
    }

    public Result(Boolean status, ResultCode resultCode, T data) {
        this.status = status;
        this.code = resultCode.getCode();
        this.msg = resultCode.getMsg();
        this.data = data;
    }

    public Result(Boolean status, Integer code, String msg, T data) {
        this.status = status;
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static <T> Result<T> success(T data) {
        return new Result(data);
    }

    public static <T> Result<T> failure(T data) {
        return new Result(false, ResultCode.SYSTEM_EXCEPTION, data);
    }

    public static <T> Result<T> failure(Integer code, String msg) {
        return new Result(false, code, msg, null);
    }

    public static <T> Result<T> failure(Integer code, String msg, T data) {
        return new Result(false, code, msg, data);
    }
}
