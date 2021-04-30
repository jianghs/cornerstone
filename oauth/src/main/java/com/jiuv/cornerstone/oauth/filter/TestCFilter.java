package com.jiuv.cornerstone.oauth.filter;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import java.io.IOException;
import java.time.LocalDateTime;

/**
 * @className: TestAFilter
 * @description:
 * @author: jianghs430
 * @createDate: 2021/4/28 10:57
 * @version: 1.0
 */

@Slf4j
public class TestCFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        log.info("{} 进入过滤器C", LocalDateTime.now());
        chain.doFilter(request, response);
        log.info("{} 退出过滤器C", LocalDateTime.now());
    }
}
