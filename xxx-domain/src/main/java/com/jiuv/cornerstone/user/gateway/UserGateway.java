package com.jiuv.cornerstone.user.gateway;

import com.jiuv.cornerstone.user.model.User;

/**
 * @className: UserGateway
 * @description:
 * @author: jianghs430
 * @createDate: 2021/7/1 9:17
 * @version: 1.0
 */
public interface UserGateway {

    /**
     * 创建用户
     * @param user
     */
    void create(User user);

    /**
     * 更新
     * @param user
     */
    void update(User user);
}
