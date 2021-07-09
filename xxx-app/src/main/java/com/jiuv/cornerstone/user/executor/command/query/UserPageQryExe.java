package com.jiuv.cornerstone.user.executor.command.query;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import com.alibaba.cola.dto.PageResponse;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jiuv.cornerstone.user.dto.UserPageQry;
import com.jiuv.cornerstone.user.dto.clientobject.UserCO;
import com.jiuv.cornerstone.user.gatewayimpl.database.UserMapper;
import com.jiuv.cornerstone.user.gatewayimpl.database.dataobject.UserDO;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @className: UserPageQryExe
 * @description:
 * @author: jianghs430
 * @createDate: 2021/6/30 13:43
 * @version: 1.0
 */

@Component
public class UserPageQryExe {
    @Resource
    private UserMapper userMapper;

    public PageResponse<UserCO> execute(UserPageQry qry) {

        PageInfo<UserDO> pageInfo =PageHelper
                .startPage(qry.getPageIndex(), qry.getPageSize())
//                .setOrderBy("id desc")
                .doSelectPageInfo(() -> this.userMapper.selectByCondition(this.buildUserDO(qry)));

        return PageResponse.of(this.convertUserDOToCO(pageInfo.getList()), this.longToInt(pageInfo.getTotal()), pageInfo.getPageSize(), pageInfo.getPageNum());
    }

    private UserDO buildUserDO(UserPageQry qry) {
        UserDO userDO = new UserDO();
        if (Objects.isNull(qry)) {
            return userDO;
        }
        return BeanUtil.copyProperties(qry, UserDO.class);
    }

    private int longToInt(long num) {
        return new Long(num).intValue();
    }

    private List<UserCO> convertUserDOToCO(List<UserDO> userDOList) {
        if (CollectionUtil.isEmpty(userDOList)) {
            return new ArrayList<>();
        }
        return userDOList.stream().map(x -> {
            UserCO userCO = BeanUtil.copyProperties(x, UserCO.class);
            userCO.setSexCode(x.getSex());
            userCO.setSex(x.getSex() == 1 ? "男" : "女");
            return userCO;
        }).collect(Collectors.toList());
    }
}
