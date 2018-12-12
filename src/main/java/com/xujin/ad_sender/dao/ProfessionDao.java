package com.xujin.ad_sender.dao;

import com.xujin.ad_sender.entity.ProfessionEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.junit.Test;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface ProfessionDao {
    @Select("SELECT * from profession")
    List<ProfessionEntity> InitProfession();

    @Select("select ProfessionName from profession where ProfessionId=#{professionid}")
    String selectProfessionNameByProfessionId(int professionid);

}