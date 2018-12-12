package com.xujin.ad_sender.dao;

import com.xujin.ad_sender.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

//标注数据访问组件
@Repository
//映射
@Mapper
public interface UserDao {

    /**
     * 查询用户信息
     *
     * @param username
     * @return
     */
    @Select("SELECT UserName,PassWord from register WHERE UserName=#{username}")
    UserEntity searchUserByName(String username);

}