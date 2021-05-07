package com.jiuv.cornerstone.base.enums;

import lombok.Getter;

/**
 * @className: ResultCode
 * @description: 响应体枚举
 * @author: jianghs430
 * @createDate: 2020/8/18 10:12
 * @version: 1.0
 */
@Getter
public enum ResultCode {
    SUCCESS(1000, "操作成功"),
    FAILED(1001, "响应失败"),
    VALIDATE_FAILED(1002, "参数校验失败"),
    PARAM_CONVERT_FAILED(1003, "参数转换失败"),
    SYSTEM_EXCEPTION(5000, "业务异常"),
    ERROR(5001, "系统异常");
    private int code;

    private String msg;

    ResultCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
