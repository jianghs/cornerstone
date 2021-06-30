package com.jiuv.cornerstone.user.dto;

import com.alibaba.cola.dto.Query;
import lombok.Data;

/**
 * @className: UserGetQry
 * @description:
 * @author: jianghs430
 * @createDate: 2021/6/30 11:13
 * @version: 1.0
 */
@Data
public class UserGetQry extends Query {

    /**
     * 用户姓名
     */
    private String userName;
}
