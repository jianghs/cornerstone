package com.jiuv.cornerstone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @className: Application
 * @description:
 * @author: jianghs430
 * @createDate: 2021/7/1 17:23
 * @version: 1.0
 */
@SpringBootApplication(scanBasePackages = {"com.jiuv.cornerstone"})
//@MapperScan("com.jiuv.cornerstone.gatewayimpl")
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
