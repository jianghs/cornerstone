package com.jiuv.cornerstone.oauth.config;

import com.jiuv.cornerstone.oauth.filter.TestCFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
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
                .csrf().disable().cors().and()
                .addFilterBefore(new TestCFilter(), UsernamePasswordAuthenticationFilter.class)
                .authorizeRequests().antMatchers("/test/**").permitAll().anyRequest().authenticated().and()
                .formLogin().loginProcessingUrl("/process").successForwardUrl("/login/success").failureForwardUrl("/login/failure");
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
