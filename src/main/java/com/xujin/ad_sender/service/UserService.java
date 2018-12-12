package com.xujin.ad_sender.service;

import com.xujin.ad_sender.entity.UserEntity;

public interface UserService {
    /**
     * 查询用户
     *
     * @param username
     * @return
     */
    UserEntity searchUserByName(String username);
}