package com.jiuv.cornerstone.oauth.config;

import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @className: CustomLogoutSuccessHandler
 * @description: 自定义退出成功处理 可以转发到其它控制器。重定向到登录页面，也可以自行实现其它 MediaType ,可以是 json 或者页面
 * @author: jianghs430
 * @createDate: 2021/5/6 9:39
 * @version: 1.0
 */
@Slf4j
public class CustomLogoutSuccessHandler implements LogoutSuccessHandler {

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        if (Objects.isNull(authentication)) {
            Map<String, Object> result = new HashMap<>(2);
            result.put("code", "4001");
            result.put("message", "未登录");
            responseJsonWriter(response, result);
            return;
        }
        User user = (User) authentication.getPrincipal();
        String username = user.getUsername();
        log.info("username: {} 退出成功", username);

        Map<String, Object> result = new HashMap<>(2);
        result.put("code", HttpStatus.OK.value());
        result.put("message", username + "退出成功");
        responseJsonWriter(response, result);
    }

    private static void responseJsonWriter(HttpServletResponse response, Map<String, Object> result) {
        response.setStatus(HttpServletResponse.SC_OK);
        response.setCharacterEncoding("utf-8");
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        PrintWriter printWriter = null;
        try {
            printWriter = response.getWriter();
            printWriter.print(JSONUtil.toJsonStr(result));
        } catch (IOException e) {
            log.error("responseJsonWriter异常", e);
        } finally {
            printWriter.flush();
            printWriter.close();
        }
    }
}