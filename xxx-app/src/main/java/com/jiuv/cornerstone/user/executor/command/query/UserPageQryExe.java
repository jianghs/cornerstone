package com.jiuv.cornerstone.user.executor.command.query;

import com.alibaba.cola.dto.PageResponse;
import com.jiuv.cornerstone.user.dto.UserPageQry;
import com.jiuv.cornerstone.user.dto.clientobject.UserCO;
import org.springframework.stereotype.Component;

/**
 * @className: UserPageQryExe
 * @description:
 * @author: jianghs430
 * @createDate: 2021/6/30 13:43
 * @version: 1.0
 */

@Component
public class UserPageQryExe {

    public PageResponse<UserCO> execute(UserPageQry qry) {
        return null;
    }
}
