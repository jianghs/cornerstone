package com.jiuv.cornerstone.oauth.controller;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @className: LoginController
 * @description:
 * @author: jianghs430
 * @createDate: 2021/4/30 15:32
 * @version: 1.0
 */
@RestController
@RequestMapping("/login")
public class LoginController {
    /**
     * 登录失败返回 401 以及提示信息.
     *
     * @return the rest
     */
    @PostMapping("/failure")
    public Map<String, Object> loginFailure() {
        Map<String, Object> result = new HashMap<>(2);
        result.put("code", HttpStatus.UNAUTHORIZED.value());
        result.put("message", "登录失败");
        return result;
    }

    /**
     * 登录成功后拿到个人信息.
     *
     * @return the rest
     */
    @PostMapping("/success")
    public Map<String, Object> loginSuccess() {
        // 登录成功后用户的认证信息 UserDetails会存在 安全上下文寄存器 SecurityContextHolder 中
        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = principal.getUsername();

        Map<String, Object> result = new HashMap<>(2);
        result.put("code", HttpStatus.OK.value());
        result.put("message", username + "登录成功");
        return result;
    }
}
