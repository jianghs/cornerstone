package com.jiuv.cornerstone.oauth.service.impl;

import cn.hutool.core.util.StrUtil;
import com.jiuv.cornerstone.oauth.entity.AuthorityInfo;
import com.jiuv.cornerstone.oauth.entity.UserInfo;
import com.jiuv.cornerstone.oauth.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @className: UserServiceImpl
 * @description:
 * @author: jianghs430
 * @createDate: 2021/5/6 16:43
 * @version: 1.0
 */
@Service
public class UserServiceImpl implements UserService {
    @Override
    public UserInfo getUserInfoByName(String userName) {
        if (StrUtil.equals("user1", userName)) {
            UserInfo userInfo = new UserInfo();
            userInfo.setId(1L);
            userInfo.setUsername(userName);
            userInfo.setNickName("张三");
            userInfo.setEmail("111@123.com");

            List<AuthorityInfo> authorityInfos = new ArrayList<>();
            AuthorityInfo authorityInfo1 = new AuthorityInfo();
            authorityInfo1.setId(1L);
            authorityInfo1.setAuthorityCode("edit");
            authorityInfo1.setAuthorityName("编辑");
            authorityInfos.add(authorityInfo1);

            AuthorityInfo authorityInfo2 = new AuthorityInfo();
            authorityInfo2.setId(2L);
            authorityInfo2.setAuthorityCode("add");
            authorityInfo2.setAuthorityName("新增");
            authorityInfos.add(authorityInfo2);

            userInfo.setAuthorityInfoList(authorityInfos);
            return userInfo;
        }

        if (StrUtil.equals("user2", userName)) {
            UserInfo userInfo = new UserInfo();
            userInfo.setId(2L);
            userInfo.setUsername(userName);
            userInfo.setNickName("李四");
            userInfo.setEmail("11231@123.com");

            List<AuthorityInfo> authorityInfos = new ArrayList<>();
            AuthorityInfo authorityInfo1 = new AuthorityInfo();
            authorityInfo1.setId(1L);
            authorityInfo1.setAuthorityCode("edit");
            authorityInfo1.setAuthorityName("编辑");
            authorityInfos.add(authorityInfo1);

            AuthorityInfo authorityInfo2 = new AuthorityInfo();
            authorityInfo2.setId(2L);
            authorityInfo2.setAuthorityCode("add");
            authorityInfo2.setAuthorityName("新增");
            authorityInfos.add(authorityInfo2);

            AuthorityInfo authorityInfo3 = new AuthorityInfo();
            authorityInfo3.setId(3L);
            authorityInfo3.setAuthorityCode("query");
            authorityInfo3.setAuthorityName("查看");
            authorityInfos.add(authorityInfo3);

            userInfo.setAuthorityInfoList(authorityInfos);
            return userInfo;
        }

        if (StrUtil.equals("user3", userName)) {
            UserInfo userInfo = new UserInfo();
            userInfo.setId(3L);
            userInfo.setUsername(userName);
            userInfo.setNickName("王五");
            userInfo.setEmail("111dd@123.com");

            List<AuthorityInfo> authorityInfos = new ArrayList<>();
            AuthorityInfo authorityInfo1 = new AuthorityInfo();
            authorityInfo1.setId(1L);
            authorityInfo1.setAuthorityCode("edit");
            authorityInfo1.setAuthorityName("编辑");
            authorityInfos.add(authorityInfo1);

            userInfo.setAuthorityInfoList(authorityInfos);
            return userInfo;
        }
        return null;
    }
}
