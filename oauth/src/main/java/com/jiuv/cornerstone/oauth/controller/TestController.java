package com.jiuv.cornerstone.oauth.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @className: TestController
 * @description:
 * @author: jianghs430
 * @createDate: 2021/4/27 14:07
 * @version: 1.0
 */
@RestController
@RequestMapping("test")
public class TestController {

    @GetMapping("query/{name}")
    public String test(@PathVariable("name") String name) {
        return "hello: " + name;
    }
}
