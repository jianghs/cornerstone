package com.jiuv.cornerstone.user.dto;

import com.alibaba.cola.dto.Command;
import com.jiuv.cornerstone.user.dto.clientobject.UserCO;
import lombok.Data;

/**
 * @className: UserAddCmd
 * @description:
 * @author: jianghs430
 * @createDate: 2021/6/30 13:36
 * @version: 1.0
 */
@Data
public class UserAddCmd extends Command {

    private UserCO userCO;
}
