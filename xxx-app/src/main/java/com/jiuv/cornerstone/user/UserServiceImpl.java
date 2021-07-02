package com.jiuv.cornerstone.user;

import com.alibaba.cola.dto.MultiResponse;
import com.alibaba.cola.dto.PageResponse;
import com.alibaba.cola.dto.Response;
import com.alibaba.cola.dto.SingleResponse;
import com.jiuv.cornerstone.user.api.UserServiceI;
import com.jiuv.cornerstone.user.dto.*;
import com.jiuv.cornerstone.user.dto.clientobject.UserCO;
import com.jiuv.cornerstone.user.executor.command.UserAddCmdExe;
import com.jiuv.cornerstone.user.executor.command.UserUpdateCmdEex;
import com.jiuv.cornerstone.user.executor.command.query.UserGetQryExe;
import com.jiuv.cornerstone.user.executor.command.query.UserListQryExe;
import com.jiuv.cornerstone.user.executor.command.query.UserPageQryExe;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @className: UserServiceImpl
 * @description:
 * @author: jianghs430
 * @createDate: 2021/6/29 15:50
 * @version: 1.0
 */
@Service
public class UserServiceImpl implements UserServiceI {
    @Resource
    private UserGetQryExe userGetQryExe;

    @Resource
    private UserListQryExe userListQryExe;

    @Resource
    private UserPageQryExe userPageQryExe;

    @Resource
    private UserAddCmdExe userAddCmdExe;

    @Resource
    private UserUpdateCmdEex userUpdateCmdEex;


    @Override
    public SingleResponse<UserCO> getUserBy(UserGetQry qry) {
        return userGetQryExe.execute(qry);
    }

    @Override
    public MultiResponse<UserCO> listUserBy(UserListQry qry) {
        return userListQryExe.execute(qry);
    }

    @Override
    public PageResponse<UserCO> pageUserBy(UserPageQry qry) {
        return userPageQryExe.execute(qry);
    }

    @Override
    public Response addUser(UserAddCmd cmd) {
        return userAddCmdExe.execute(cmd);
    }

    @Override
    public Response updateUser(UserUpdateCmd cmd) {
        return userUpdateCmdEex.execute(cmd);
    }
}
