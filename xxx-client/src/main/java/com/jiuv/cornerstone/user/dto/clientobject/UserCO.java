package com.jiuv.cornerstone.user.dto.clientobject;

import com.alibaba.cola.dto.DTO;
import lombok.Data;

/**
 * @className: UserCO
 * @description:
 * @author: jianghs430
 * @createDate: 2021/6/30 11:02
 * @version: 1.0
 */
@Data
public class UserCO extends DTO {

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
