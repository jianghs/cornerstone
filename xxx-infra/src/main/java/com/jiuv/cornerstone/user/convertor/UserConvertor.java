package com.jiuv.cornerstone.user.convertor;

import cn.hutool.core.bean.BeanUtil;
import com.jiuv.cornerstone.user.dto.clientobject.UserCO;
import com.jiuv.cornerstone.user.gatewayimpl.database.dataobject.UserDO;
import com.jiuv.cornerstone.user.model.User;

/**
 * @className: UserConvertor
 * @description:
 * @author: jianghs430
 * @createDate: 2021/7/1 9:35
 * @version: 1.0
 */
public class UserConvertor {

    public static User toEntity(UserCO userCO) {
        return BeanUtil.copyProperties(userCO, User.class);
    }

    public static User toEntity(UserDO userDO) {
        return BeanUtil.copyProperties(userDO, User.class);
    }

    public static UserDO toDataObject(User user) {
        UserDO userDO = BeanUtil.copyProperties(user, UserDO.class);
        userDO.setSex(user.getSexCode());
        return userDO;
    }

}
