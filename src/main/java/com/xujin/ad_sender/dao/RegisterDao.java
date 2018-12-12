package com.xujin.ad_sender.dao;

import com.xujin.ad_sender.entity.RegisterEntity;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface RegisterDao {
    /**
     * register information
     */
    @Insert("INSERT INTO register (UserName, PassWord, Profession, Sex, Age, Hobby) " +
            "VALUES (#{UserName},#{PassWord},#{Profession},#{Sex},#{Age},#{Hobby})")
    void register(RegisterEntity registerEntity);
    @Select("select * from register where UserName=#{UserName}")
    RegisterEntity getUserByUserName(String UserName);
}