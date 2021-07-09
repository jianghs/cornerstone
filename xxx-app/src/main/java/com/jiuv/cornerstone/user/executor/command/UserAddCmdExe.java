package com.jiuv.cornerstone.user.executor.command;

import com.alibaba.cola.dto.Response;
import com.alibaba.cola.exception.Assert;
import com.jiuv.cornerstone.user.convertor.UserConvertor;
import com.jiuv.cornerstone.user.dto.UserAddCmd;
import com.jiuv.cornerstone.user.gateway.UserGateway;
import com.jiuv.cornerstone.user.model.User;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * @className: UserAddCmdExe
 * @description:
 * @author: jianghs430
 * @createDate: 2021/6/30 13:43
 * @version: 1.0
 */
@Component
public class UserAddCmdExe {

    @Resource
    private UserGateway userGateway;

    public Response execute(UserAddCmd cmd) {
        String userName = cmd.getUserCO().getName();
        User existsUser = this.getUserByName(userName);
        Assert.isFalse(Objects.nonNull(existsUser) && Objects.nonNull(existsUser.getName()), userName + "已存在！");

        // 新增用户
        User user = UserConvertor.toEntity(cmd.getUserCO());
        userGateway.create(user);
        return Response.buildSuccess();
    }

    /**
     * 根据用户名查询用户信息
     * @param userName
     * @return
     */
    private User getUserByName(String userName) {
        return userGateway.getUserByName(userName);
    }
}
