package com.jiuv.cornerstone.user.executor.command;

import com.alibaba.cola.dto.Response;
import com.jiuv.cornerstone.user.convertor.UserConvertor;
import com.jiuv.cornerstone.user.dto.UserAddCmd;
import com.jiuv.cornerstone.user.gateway.UserGateway;
import com.jiuv.cornerstone.user.model.User;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

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
        User user = UserConvertor.toEntity(cmd.getUserCO());
        userGateway.create(user);
        return Response.buildSuccess();
    }
}
