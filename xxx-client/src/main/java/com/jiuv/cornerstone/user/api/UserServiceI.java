package com.jiuv.cornerstone.user.api;

import com.alibaba.cola.dto.MultiResponse;
import com.alibaba.cola.dto.PageResponse;
import com.alibaba.cola.dto.Response;
import com.alibaba.cola.dto.SingleResponse;
import com.jiuv.cornerstone.user.dto.*;
import com.jiuv.cornerstone.user.dto.clientobject.UserCO;

/**
 * @className: UserServiceI
 * @description:
 * @author: jianghs430
 * @createDate: 2021/6/29 15:43
 * @version: 1.0
 */
public interface UserServiceI {

    /**
     * 查询单个用户
     * @param qry
     * @return
     */
    SingleResponse<UserCO> getUserBy(UserGetQry qry);

    /**
     * 查询用户列表
     * @param qry
     * @return
     */
    MultiResponse<UserCO> listUserBy(UserListQry qry);

    /**
     * 查询用户分页列表
     * @param qry
     * @return
     */
    PageResponse<UserCO> pageUserBy(UserPageQry qry);

    /**
     * 新增用户
     * @param cmd
     * @return
     */
    Response addUser(UserAddCmd cmd);

    /**
     * 更新用户
     * @param cmd
     * @return
     */
    Response updateUser(UserUpdateCmd cmd);



}
