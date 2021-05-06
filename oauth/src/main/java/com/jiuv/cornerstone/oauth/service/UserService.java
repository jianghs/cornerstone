package com.jiuv.cornerstone.oauth.service;

import com.jiuv.cornerstone.oauth.entity.UserInfo;

/**
 * @className: UserService
 * @description:
 * @author: jianghs430
 * @createDate: 2021/5/6 16:41
 * @version: 1.0
 */
public interface UserService {

    /**
     * 根据用户名查询用户信息
     * @param userName 用户名
     * @return 用户信息
     */
    UserInfo getUserInfoByName(String userName);
}
