package com.xujin.ad_sender.dao;

import com.xujin.ad_sender.entity.ADInfoEntity;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface ADInfoDao {
    @Select("select * from ad_information")
    List<ADInfoEntity> InitADInformation();

    @Insert("insert into ad_information(ADTitle,SelectCrowd,SelectGender,UploadPicture,ADClasses,ADDescribe,RTBPrice)" + " " +
            "values(#{ADTitle},#{SelectCrowd},#{SelectGender},#{UploadPicture},#{ADClasses},#{ADDescribe},#{RTBPrice})")
    void addADInfo(ADInfoEntity adInfoEntity);

    @Update("update ad_information set ADTitle=#{ADTitle},ADClasses=#{ADClasses},ADDescribe=#{ADDescribe},RTBPrice=#{RTBPrice} where ADID=#{ADID}")
    void updateADInfo(ADInfoEntity adInfoEntity);

    @Delete("delete from ad_information where ADID=#{ADID}")
    void deleteADInfo(Integer ADID);

    @Select("SELECT * from ad_information WHERE SelectGender=#{Sex}")
    List<ADInfoEntity> RecommendADList(String Sex);

}
