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
    private Integer code;
    private String msg;
    private T data;

    public Result() {
        this(ResultCode.SUCCESS, null);
    }

    public Result(T data) {
        this(ResultCode.SUCCESS, data);
    }

    public Result(ResultCode resultCode, T data) {
        this.code = resultCode.getCode();
        this.msg = resultCode.getMsg();
        this.data = data;
    }

    public Result(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static <T> Result<T> success(T data) {
        return new Result(ResultCode.SUCCESS, data);
    }

    public static <T> Result<T> failure(T data) {
        return new Result(ResultCode.SYSTEM_EXCEPTION, data);
    }

    public static <T> Result<T> failure(Integer code, String msg) {
        return new Result(code, msg, null);
    }

    public static <T> Result<T> failure(Integer code, String msg, T data) {
        return new Result(code, msg, data);
    }
}
