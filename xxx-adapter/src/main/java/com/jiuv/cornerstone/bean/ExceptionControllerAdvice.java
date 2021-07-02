package com.jiuv.cornerstone.bean;

import com.alibaba.cola.dto.Response;
import com.alibaba.cola.exception.BaseException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @className: ExceptionControllerAdvice
 * @description:
 * @author: jianghs430
 * @createDate: 2021/7/2 17:28
 * @version: 1.0
 */
@Slf4j
@RestControllerAdvice
public class ExceptionControllerAdvice {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Response MethodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
        // 从异常对象中拿到ObjectError对象
        ObjectError objectError = e.getBindingResult().getAllErrors().get(0);
        // 然后提取错误提示信息进行返回
        log.error(objectError.getDefaultMessage());
        return Response.buildFailure("", objectError.getDefaultMessage());
    }

    @ExceptionHandler(BaseException.class)
    public Response BaseExceptionHandler(BaseException e) {
        log.error(e.getMessage());
        return Response.buildFailure(e.getErrCode(), e.getMessage());
    }
}
