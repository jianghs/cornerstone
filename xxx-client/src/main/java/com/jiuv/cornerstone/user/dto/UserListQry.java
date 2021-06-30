package com.jiuv.cornerstone.user.dto;

import com.alibaba.cola.dto.Query;
import lombok.Data;

/**
 * @className: UserListQry
 * @description:
 * @author: jianghs430
 * @createDate: 2021/6/30 13:32
 * @version: 1.0
 */
@Data
public class UserListQry extends Query {

    /**
     * 性别
     */
    private String sex;
}
