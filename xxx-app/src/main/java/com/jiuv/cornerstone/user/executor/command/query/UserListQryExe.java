package com.jiuv.cornerstone.user.executor.command.query;

import cn.hutool.core.bean.BeanUtil;
import com.alibaba.cola.dto.MultiResponse;
import com.alibaba.cola.exception.Assert;
import com.jiuv.cornerstone.user.dto.UserListQry;
import com.jiuv.cornerstone.user.dto.clientobject.UserCO;
import com.jiuv.cornerstone.user.gatewayimpl.database.UserMapper;
import com.jiuv.cornerstone.user.gatewayimpl.database.dataobject.UserDO;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @className: UserListQryExe
 * @description:
 * @author: jianghs430
 * @createDate: 2021/6/30 13:42
 * @version: 1.0
 */
@Component
public class UserListQryExe {
    @Resource
    private UserMapper userMapper;

    public MultiResponse<UserCO> execute(UserListQry qry) {
        Assert.notNull(qry, "入参不得为空");
        List<UserDO> userDOList = userMapper.selectByCondition(this.buildUserDO(qry));

        List<UserCO> userCOList = new ArrayList<>();
        userDOList.forEach(userDO -> {
            UserCO userCO = BeanUtil.copyProperties(userDO, UserCO.class);
            userCO.setSexCode(userDO.getSex());
            userCO.setSex(userDO.getSex() == 1 ? "男" : "女");
            userCOList.add(userCO);
        });
        return MultiResponse.of(userCOList);
    }

    private UserDO buildUserDO(UserListQry qry) {
        UserDO userDO = new UserDO();
        if (Objects.isNull(qry)) {
            return userDO;
        }
        return BeanUtil.copyProperties(qry, UserDO.class);
    }
}
