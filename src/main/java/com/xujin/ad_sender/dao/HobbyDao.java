package com.xujin.ad_sender.dao;

import com.xujin.ad_sender.entity.HobbyEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface HobbyDao {
    @Select("SELECT  * from hobby")
    List<HobbyEntity> InitHobby();
}