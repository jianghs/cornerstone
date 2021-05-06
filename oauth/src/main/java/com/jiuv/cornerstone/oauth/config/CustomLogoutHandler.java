package com.jiuv.cornerstone.oauth.config;

import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.logout.LogoutHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @className: CustomLogoutHandler
 * @description: 自定义退出处理（如记录用户下线退出时间、IP）
 * @author: jianghs430
 * @createDate: 2021/5/6 9:36
 * @version: 1.0
 */
@Slf4j
public class CustomLogoutHandler implements LogoutHandler {
    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        if (Objects.isNull(authentication)) {
            Map<String, Object> result = new HashMap<>(2);
            result.put("code", "4001");
            result.put("message", "未登录");
            responseJsonWriter(response, result);
            return;
        }
        User user = (User) authentication.getPrincipal();
        String username = user.getUsername();

        // 记录用户下线退出时间、IP
        String ip = "";
        try {
            InetAddress addr = InetAddress.getLocalHost();
            ip = addr.getHostAddress();
        } catch (UnknownHostException e) {
            log.error("获取IP地址异常", e);
            e.printStackTrace();
        }

        log.info("用户：{}，退出时间：{}，IP:{}", username, LocalDateTime.now(), ip);
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
