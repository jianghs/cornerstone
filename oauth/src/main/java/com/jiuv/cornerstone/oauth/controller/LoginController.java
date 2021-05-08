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
 * @className: LoginController
 * @description: Restful登录
 * @author: jianghs430
 * @createDate: 2021/4/30 15:32
 * @version: 1.0
 */
@RestController
@RequestMapping("/user")
public class LoginController {
    /**
     * 过期时间 8 小时
     */
    private static long EXPIRE_TIME = 1000 * 60 * 60 * 8;

    private UserService userService;

    @Autowired
    public void userService(UserService userService) {
        this.userService = userService;
    }

    /**
     * 登录
     * @return the rest
     */
    @PostMapping("/login")
    public Result<Map<String, String>> login() {
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

        return Result.success(result);
    }


}
