package com.xujin.ad_sender.service;

import com.xujin.ad_sender.entity.ADInfoEntity;

import java.util.List;

public interface ADInfoService {
    List<ADInfoEntity> InitADInformation();

    void addADInfo(ADInfoEntity adInfoEntity);

    void updateADInfo(ADInfoEntity adInfoEntity);

    void deleteADInfo(Integer ADID);

    ADInfoEntity recommendAD(String UserName, String keyword);
}