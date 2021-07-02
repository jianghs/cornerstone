package com.jiuv.cornerstone.user.web;

import com.alibaba.cola.dto.MultiResponse;
import com.alibaba.cola.dto.Response;
import com.jiuv.cornerstone.user.api.UserServiceI;
import com.jiuv.cornerstone.user.dto.UserAddCmd;
import com.jiuv.cornerstone.user.dto.UserListQry;
import com.jiuv.cornerstone.user.dto.clientobject.UserCO;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @className: UserController
 * @description:
 * @author: jianghs430
 * @createDate: 2021/7/1 17:08
 * @version: 1.0
 */
@RestController
@RequestMapping(value = "cornerstone")
public class UserController {

    @Resource
    private UserServiceI userServiceI;

    @PostMapping(value = "/user")
    private Response addUser(@RequestBody UserAddCmd userAddCmd) {
        return userServiceI.addUser(userAddCmd);
    }

    @GetMapping(value = "/user")
    private MultiResponse<UserCO> listUserBySex(@RequestParam Integer sex) {
        UserListQry qry = new UserListQry();
        qry.setSex(sex);
        return userServiceI.listUserBy(qry);
    }
}
