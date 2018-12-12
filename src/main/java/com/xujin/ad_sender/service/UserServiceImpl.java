package com.xujin.ad_sender.service;

import com.xujin.ad_sender.dao.UserDao;
import com.xujin.ad_sender.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * \* Created with IntelliJ IDEA.
 * \* Description:
 * \* User: xujin
 * \* Date: 2018/3/22
 * \* Time: 16:49
 * \
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;

    @Override
    public UserEntity searchUserByName(String username) {
        return userDao.searchUserByName(username);
    }
}
