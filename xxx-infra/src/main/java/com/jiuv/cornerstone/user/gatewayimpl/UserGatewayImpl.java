package com.jiuv.cornerstone.user.gatewayimpl;

import com.jiuv.cornerstone.user.convertor.UserConvertor;
import com.jiuv.cornerstone.user.gateway.UserGateway;
import com.jiuv.cornerstone.user.gatewayimpl.database.UserMapper;
import com.jiuv.cornerstone.user.gatewayimpl.database.dataobject.UserDO;
import com.jiuv.cornerstone.user.model.User;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * @className: UserGatewayImpl
 * @description:
 * @author: jianghs430
 * @createDate: 2021/7/1 15:00
 * @version: 1.0
 */
@Repository
public class UserGatewayImpl implements UserGateway {
    @Resource
    private UserMapper userMapper;

    @Override
    public void create(User user) {
        UserDO userDO = UserConvertor.toDataObject(user);
        userMapper.insert(userDO);
    }

    @Override
    public void update(User user) {
        UserDO userDO = UserConvertor.toDataObject(user);
        userMapper.updateByPrimaryKey(userDO);
    }

    @Override
    public User getUserByName(String userName) {
        UserDO userDO = userMapper.selectByName(userName);
        if (Objects.isNull(userDO)) {
            return new User();
        }
        return UserConvertor.toEntity(userDO);
    }
}
