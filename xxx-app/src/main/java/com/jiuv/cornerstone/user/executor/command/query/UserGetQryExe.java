package com.jiuv.cornerstone.user.executor.command.query;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.cola.dto.SingleResponse;
import com.alibaba.cola.exception.Assert;
import com.jiuv.cornerstone.user.dto.UserGetQry;
import com.jiuv.cornerstone.user.dto.clientobject.UserCO;
import com.jiuv.cornerstone.user.gatewayimpl.database.UserMapper;
import com.jiuv.cornerstone.user.gatewayimpl.database.dataobject.UserDO;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * @className: UserGetQryExe
 * @description:
 * @author: jianghs430
 * @createDate: 2021/6/30 13:42
 * @version: 1.0
 */
@Component
public class UserGetQryExe {
    @Resource
    private UserMapper userMapper;

    public SingleResponse<UserCO> execute(UserGetQry qry) {
        Assert.notNull(qry, "入参不得为空");
        Assert.isFalse(StrUtil.isBlank(qry.getUserName()), "用户名不得为空");

        UserDO userDO = userMapper.selectByName(qry.getUserName());
        if (Objects.isNull(userDO)) {
            return SingleResponse.of(new UserCO());
        }
        UserCO userCO = BeanUtil.copyProperties(userDO, UserCO.class);
        userCO.setSexCode(userDO.getSex());
        userCO.setSex(userDO.getSex() == 1 ? "男" : "女");
        return SingleResponse.of(userCO);
    }
}
