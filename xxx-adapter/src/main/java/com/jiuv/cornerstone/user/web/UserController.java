package com.jiuv.cornerstone.user.web;

import com.alibaba.cola.dto.MultiResponse;
import com.alibaba.cola.dto.Response;
import com.alibaba.cola.dto.SingleResponse;
import com.alibaba.cola.exception.Assert;
import com.jiuv.cornerstone.user.api.UserServiceI;
import com.jiuv.cornerstone.user.dto.UserAddCmd;
import com.jiuv.cornerstone.user.dto.UserGetQry;
import com.jiuv.cornerstone.user.dto.UserListQry;
import com.jiuv.cornerstone.user.dto.UserUpdateCmd;
import com.jiuv.cornerstone.user.dto.clientobject.UserCO;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Objects;

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

    @PutMapping(value = "/user")
    private Response updateUser(@RequestBody UserUpdateCmd userUpdateCmd) {
        long id = userUpdateCmd.getUserCO().getId();
        Assert.isFalse(Objects.isNull(id), "id不得为空" );
        return userServiceI.updateUser(userUpdateCmd);
    }

    @GetMapping(value = "/users")
    private MultiResponse<UserCO> listUserBySex(@RequestParam Integer sex) {
        UserListQry qry = new UserListQry();
        qry.setSex(sex);
        return userServiceI.listUserBy(qry);
    }

    @GetMapping(value = "/user")
    private SingleResponse<UserCO> getUserBySex(@RequestParam String name) {
        UserGetQry qry = new UserGetQry();
        qry.setUserName(name);
        return userServiceI.getUserBy(qry);
    }
}
