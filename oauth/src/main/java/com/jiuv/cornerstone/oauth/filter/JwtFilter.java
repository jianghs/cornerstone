package com.jiuv.cornerstone.oauth.filter;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.jiuv.cornerstone.base.entity.Result;
import com.jiuv.cornerstone.oauth.entity.AuthorityInfo;
import com.jiuv.cornerstone.oauth.entity.UserInfo;
import com.jiuv.cornerstone.oauth.jwt.JwtUtil;
import com.jiuv.cornerstone.oauth.util.ResponseUtil;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @className: TestAFilter
 * @description:
 * @author: jianghs430
 * @createDate: 2021/4/28 10:57
 * @version: 1.0
 */

@Slf4j
@Component
public class JwtFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // 登录和退出不解析token
        if (StrUtil.equals(request.getRequestURI(), "/login") || StrUtil.equals(request.getRequestURI(), "/logout")) {
            filterChain.doFilter(request, response);
            return;
        }
        // 如果已经通过认证
        if (SecurityContextHolder.getContext().getAuthentication() != null) {
            filterChain.doFilter(request, response);
            return;
        }

        String jwtToken = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (StrUtil.isBlank(jwtToken)) {
            log.error("token为空");
            ResponseUtil.responseJsonWriter(response, Result.failure(HttpStatus.UNAUTHORIZED.value(), "token为空"));
        }

        try {
            this.authenticationTokenHandle(jwtToken, request, response);
        } catch (AuthenticationException e) {
            log.error("认证异常", e);
            ResponseUtil.responseJsonWriter(response, Result.failure(HttpStatus.UNAUTHORIZED.value(), "认证异常"));
        }
        filterChain.doFilter(request, response);
    }

    private void authenticationTokenHandle(String jwtToken, HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

        Result<Claims> result =  JwtUtil.validateJWT(jwtToken);
        if (!result.getStatus()) {
            ResponseUtil.responseJsonWriter(response, Result.failure(500, "token解析异常"));
        }
        Claims claims = result.getData();
        String username = claims.getSubject();
        String userStr = JSONUtil.toJsonStr(claims.get("userInfo"));
        log.info("当前登录用户：{}", userStr);
        UserInfo userInfo = JSONUtil.toBean(userStr, UserInfo.class);

        // 配置权限
        List<AuthorityInfo> authorityInfos = userInfo.getAuthorityInfoList();
        List<String> authorityCodes = authorityInfos.stream().map(AuthorityInfo::getAuthorityCode).collect(Collectors.toList());
        String roles = Convert.toStr(authorityCodes);
        List<GrantedAuthority> authorities = AuthorityUtils.commaSeparatedStringToAuthorityList(roles);

        User user = new User(username, "[PROTECTED]", authorities);
        // 构建用户认证token
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(user, null, authorities);
        usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        // 放入安全上下文中
        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
    }
}
