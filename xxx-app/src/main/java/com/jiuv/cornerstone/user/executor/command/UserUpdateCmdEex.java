package com.jiuv.cornerstone.user.executor.command;

import com.alibaba.cola.dto.Response;
import com.jiuv.cornerstone.user.convertor.UserConvertor;
import com.jiuv.cornerstone.user.dto.UserUpdateCmd;
import com.jiuv.cornerstone.user.gateway.UserGateway;
import com.jiuv.cornerstone.user.model.User;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @className: UserUpdateCmdEex
 * @description:
 * @author: jianghs430
 * @createDate: 2021/6/30 13:44
 * @version: 1.0
 */
@Component
public class UserUpdateCmdEex {
    @Resource
    private UserGateway userGateway;

    public Response execute(UserUpdateCmd cmd) {
        User user = UserConvertor.toEntity(cmd.getUserCO());
        userGateway.update(user);
        return Response.buildSuccess();
    }
}
