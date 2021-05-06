package com.jiuv.cornerstone.oauth.filter;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.jiuv.cornerstone.oauth.entity.UserInfo;
import com.jiuv.cornerstone.oauth.jwt.JwtUtil;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @className: TestAFilter
 * @description:
 * @author: jianghs430
 * @createDate: 2021/4/28 10:57
 * @version: 1.0
 */

@Slf4j
public class JwtFilter extends GenericFilter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        // 登录和退出不解析token
        if (StrUtil.equals(req.getRequestURI(), "/process") || StrUtil.equals(req.getRequestURI(), "/logout")) {
            chain.doFilter(request, response);
            return;
        }

        // 获取 token ，注意获取方式要跟前台传的方式保持一致 这里请求时注意认证方式选择 Bearer Token，会用 header 传递
        String token = req.getHeader("token");
        if (StrUtil.isBlank(token)) {
            Map<String, Object> result = new HashMap<>(2);
            result.put("code", "4001");
            result.put("message", "未登录");
            responseJsonWriter((HttpServletResponse)response, result);
            return;
        }
        Claims claims = JwtUtil.parseJWT(token);
        if (Objects.isNull(claims)) {
            Map<String, Object> result = new HashMap<>(2);
            result.put("code", "500");
            result.put("message", "解析token失败");
            responseJsonWriter((HttpServletResponse)response, result);
            return;
        }
        String userStr = JSONUtil.toJsonStr(claims.get("userInfo"));
        log.info("当前登录用户：{}", userStr);
        UserInfo user = JSONUtil.toBean(userStr, UserInfo.class);
        chain.doFilter(request, response);
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
