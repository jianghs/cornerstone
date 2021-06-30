package com.jiuv.cornerstone.user;

import com.alibaba.cola.dto.MultiResponse;
import com.alibaba.cola.dto.PageResponse;
import com.alibaba.cola.dto.Response;
import com.alibaba.cola.dto.SingleResponse;
import com.jiuv.cornerstone.user.api.UserServiceI;
import com.jiuv.cornerstone.user.dto.*;
import com.jiuv.cornerstone.user.dto.clientobject.UserCO;

/**
 * @className: UserServiceImpl
 * @description:
 * @author: jianghs430
 * @createDate: 2021/6/29 15:50
 * @version: 1.0
 */
public class UserServiceImpl implements UserServiceI {


    @Override
    public SingleResponse<UserCO> getUserBy(UserGetQry qry) {
        return null;
    }

    @Override
    public MultiResponse<UserCO> listUserBy(UserListQry qry) {
        return null;
    }

    @Override
    public PageResponse<UserCO> pageUserBy(UserPageQry qry) {
        return null;
    }

    @Override
    public Response addUser(UserAddCmd cmd) {
        return null;
    }

    @Override
    public Response updateUser(UserUpdateCmd cmd) {
        return null;
    }
}
