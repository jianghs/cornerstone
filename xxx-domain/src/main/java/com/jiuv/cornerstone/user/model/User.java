package com.jiuv.cornerstone.user.model;

import com.alibaba.cola.extension.BizScenario;
import lombok.Data;

/**
 * @className: User
 * @description:
 * @author: jianghs430
 * @createDate: 2021/7/1 9:19
 * @version: 1.0
 */
@Data
public class User extends BizScenario {
    /**
     * 用户id
     */
    private Long id;

    /**
     * 用户姓名
     */
    private String name;

    /**
     * 用户性别编码
     */
    private Integer sexCode;

    /**
     * 用户性别
     */
    private String sex;

    /**
     * 用户手机
     */
    private String mobile;
}
