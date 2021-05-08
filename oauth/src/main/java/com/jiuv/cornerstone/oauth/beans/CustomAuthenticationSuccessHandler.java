package com.jiuv.cornerstone.oauth.beans;

import cn.hutool.core.collection.CollectionUtil;
import com.jiuv.cornerstone.base.entity.Result;
import com.jiuv.cornerstone.oauth.entity.UserInfo;
import com.jiuv.cornerstone.oauth.jwt.JwtUtil;
import com.jiuv.cornerstone.oauth.service.UserService;
import com.jiuv.cornerstone.oauth.util.ResponseUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

/**
 * @className: CustomAuthenticationSuccessHandler
 * @description:
 * @author: jianghs430
 * @createDate: 2021/5/8 10:06
 * @version: 1.0
 */
@Slf4j
@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    /**
     * 过期时间 8 小时
     */
    private static long EXPIRE_TIME = 1000 * 60 * 60 * 8;

    private UserService userService;

    @Autowired
    public void userService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        if (response.isCommitted()) {
            log.debug("Response has already been committed");
            return;
        }

        User principal = (User) authentication.getPrincipal();
        String username = principal.getUsername();
        // 根据用户名拿到用户信息
        UserInfo userInfo = userService.getUserInfoByName(username);
        if (Objects.isNull(userInfo)) {
            log.error("查询用户信息失败!");
            ResponseUtil.responseJsonWriter(response, Result.failure(HttpStatus.INTERNAL_SERVER_ERROR.value(), "查询用户信息失败"));
        }
        String token = JwtUtil.createJWT(String.valueOf(userInfo.getId()), userInfo.getUsername(), userInfo, EXPIRE_TIME);
        userInfo.setToken(token);
        ResponseUtil.responseJsonWriter(response, Result.success(userInfo));
    }
}
