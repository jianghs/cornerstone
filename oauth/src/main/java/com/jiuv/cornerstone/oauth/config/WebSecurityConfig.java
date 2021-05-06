package com.jiuv.cornerstone.oauth.config;

import com.jiuv.cornerstone.oauth.filter.JwtFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @className: WebSecurityConfig
 * @description:
 * @author: jianghs430
 * @createDate: 2021/4/27 16:50
 * @version: 1.0
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * 核心过滤器，较少配置，过滤静态资源
     * @param web
     */
    @Override
    public void configure(WebSecurity web) {
        web
                .ignoring().antMatchers("/css/**", "/images/**", "/js/**");
    }

    /**
     * 安全过滤器链
     * loginProcessingUrl: 是登录时对用户名密码的校验，无需自定义controller
     *
     *
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                // 关闭跨域
                .csrf().disable().cors().and()
                // 前后端分离设置为无状态
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                // 自定义过滤器
                .addFilterBefore(new JwtFilter(), UsernamePasswordAuthenticationFilter.class)
                // 认证
                .authorizeRequests().antMatchers("/login", "/process").permitAll().anyRequest().authenticated().and()
                // form表单登录配置，前后端分离项目可以忽略
                .formLogin().loginProcessingUrl("/process").successForwardUrl("/login/success").failureForwardUrl("/login/failure").and()
                // 自定义退出处理 默认情况下清除认证信息 （invalidateHttpSession），和Session 失效（invalidateHttpSession） 已经由内置的SecurityContextLogoutHandler 来完成
                .logout().addLogoutHandler(new CustomLogoutHandler()).logoutSuccessHandler(new CustomLogoutSuccessHandler());
    }


    /**
     * 认证管理器
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .passwordEncoder(new BCryptPasswordEncoder())
                .withUser("user1").password(new BCryptPasswordEncoder().encode("123456")).roles("USER1").and()
                .withUser("user2").password(new BCryptPasswordEncoder().encode("123456")).roles("USER2").and()
                .withUser("user3").password(new BCryptPasswordEncoder().encode("123456")).roles("USER3");
    }
}
