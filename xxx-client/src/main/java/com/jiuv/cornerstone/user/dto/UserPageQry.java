package com.jiuv.cornerstone.user.dto;

import com.alibaba.cola.dto.PageQuery;
import lombok.Data;

/**
 * @className: UserPageQry
 * @description:
 * @author: jianghs430
 * @createDate: 2021/6/30 13:34
 * @version: 1.0
 */
@Data
public class UserPageQry extends PageQuery {

    /**
     * 性别
     */
    private String sex;
}
