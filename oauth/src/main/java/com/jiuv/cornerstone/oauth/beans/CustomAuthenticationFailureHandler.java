package com.jiuv.cornerstone.oauth.beans;

import com.jiuv.cornerstone.base.entity.Result;
import com.jiuv.cornerstone.oauth.util.ResponseUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @className: CustomAuthenticationFailureHandler
 * @description:
 * @author: jianghs430
 * @createDate: 2021/5/8 10:18
 * @version: 1.0
 */
@Component
@Slf4j
public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        if (response.isCommitted()) {
            log.debug("Response has already been committed");
            return;
        }
        ResponseUtil.responseJsonWriter(response, Result.failure(HttpStatus.UNAUTHORIZED.value(), "认证失败"));
    }
}
