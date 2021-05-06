package com.jiuv.cornerstone.oauth.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @className: UserInfo
 * @description:
 * @author: jianghs430
 * @createDate: 2021/5/6 16:35
 * @version: 1.0
 */
@Data
public class UserInfo implements Serializable {

    /**
     * 用户ID
     */
    private Long id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 权限集合
     */
    private List<AuthorityInfo> authorityInfoList;

}
