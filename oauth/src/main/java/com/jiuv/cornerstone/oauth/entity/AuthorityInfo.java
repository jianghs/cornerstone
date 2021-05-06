package com.jiuv.cornerstone.oauth.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @className: AuthorityInfo
 * @description:
 * @author: jianghs430
 * @createDate: 2021/5/6 16:38
 * @version: 1.0
 */
@Data
public class AuthorityInfo implements Serializable {

    /**
     * 权限ID
     */
    private Long id;

    /**
     * 权限编码
     */
    private String authorityCode;

    /**
     * 权限名称
     */
    private String authorityName;
}
