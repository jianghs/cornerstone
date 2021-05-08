package com.jiuv.cornerstone.oauth.beans;

import com.jiuv.cornerstone.base.entity.Result;
import com.jiuv.cornerstone.oauth.util.ResponseUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * @className: CustomLogoutHandler
 * @description: 自定义退出处理（如记录用户下线退出时间、IP）
 * @author: jianghs430
 * @createDate: 2021/5/6 9:36
 * @version: 1.0
 */
@Slf4j
@Component
public class CustomLogoutHandler implements LogoutHandler {
    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        if (Objects.isNull(authentication)) {
            ResponseUtil.responseJsonWriter(response, Result.failure(4001, "未登录"));
            return;
        }
        User user = (User) authentication.getPrincipal();
        String username = user.getUsername();

        SecurityContextHolder.clearContext();
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


}
