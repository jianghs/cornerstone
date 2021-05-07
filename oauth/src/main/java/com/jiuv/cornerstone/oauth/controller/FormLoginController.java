package com.jiuv.cornerstone.oauth.controller;

import com.jiuv.cornerstone.base.entity.Result;
import com.jiuv.cornerstone.oauth.entity.UserInfo;
import com.jiuv.cornerstone.oauth.jwt.JwtUtil;
import com.jiuv.cornerstone.oauth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @className: FormLoginController
 * @description: 表单登录
 * @author: jianghs430
 * @createDate: 2021/4/30 15:32
 * @version: 1.0
 */
@RestController
@RequestMapping("/login")
public class FormLoginController {
    /**
     * 过期时间 8 小时
     */
    private static long EXPIRE_TIME = 1000 * 60 * 60 * 8;
    @Autowired
    private UserService userService;

    /**
     * 登录失败返回 401 以及提示信息.
     *
     * @return the rest
     */
    @PostMapping("/failure")
    public Result<String> loginFailure() {
        return Result.failure(HttpStatus.UNAUTHORIZED.value(), "登录失败");
    }

    /**
     * 登录成功后拿到个人信息.
     *
     * @return the rest
     */
    @PostMapping("/success")
    public Result<Map<String, String>> loginSuccess() {
        // 登录成功后用户的认证信息 UserDetails会存在 安全上下文寄存器 SecurityContextHolder 中
        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = principal.getUsername();

        // 根据用户名拿到用户信息
        UserInfo userInfo = userService.getUserInfoByName(username);
        if (Objects.isNull(userInfo)) {
            return Result.failure(HttpStatus.INTERNAL_SERVER_ERROR.value(), "查询用户信息失败");
        }

        String token = JwtUtil.createJWT(String.valueOf(userInfo.getId()), userInfo.getUsername(), userInfo, EXPIRE_TIME);

        Map<String, String> result = new HashMap<>(1);
        result.put("token", token);

        return Result.failure(HttpStatus.OK.value(), username + "登录成功", result);
    }
}
