package com.jiuv.cornerstone.oauth.beans;

import com.jiuv.cornerstone.base.entity.Result;
import com.jiuv.cornerstone.oauth.util.ResponseUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

/**
 * @className: CustomLogoutSuccessHandler
 * @description: 自定义退出成功处理 可以转发到其它控制器。重定向到登录页面，也可以自行实现其它 MediaType ,可以是 json 或者页面
 * @author: jianghs430
 * @createDate: 2021/5/6 9:39
 * @version: 1.0
 */
@Slf4j
@Component
public class CustomLogoutSuccessHandler implements LogoutSuccessHandler {

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        if (Objects.isNull(authentication)) {
            ResponseUtil.responseJsonWriter(response, Result.failure(4001, "未登录"));
            return;
        }
        User user = (User) authentication.getPrincipal();
        String username = user.getUsername();
        log.info("username: {} 退出成功", username);

        ResponseUtil.responseJsonWriter(response, Result.success(username + "退出成功"));
    }
}