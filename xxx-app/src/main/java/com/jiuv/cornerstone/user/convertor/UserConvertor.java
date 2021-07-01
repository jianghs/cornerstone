package com.jiuv.cornerstone.user.convertor;

import com.jiuv.cornerstone.user.dto.clientobject.UserCO;
import com.jiuv.cornerstone.user.model.User;
import org.springframework.beans.BeanUtils;

/**
 * @className: UserConvertor
 * @description:
 * @author: jianghs430
 * @createDate: 2021/7/1 9:35
 * @version: 1.0
 */
public class UserConvertor {

    public static User toEntity(UserCO userCO) {
        User user = new User();
        BeanUtils.copyProperties(userCO, user);
        return user;
    }
}
