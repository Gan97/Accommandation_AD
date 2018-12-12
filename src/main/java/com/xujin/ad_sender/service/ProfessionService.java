package com.xujin.ad_sender.service;

import com.xujin.ad_sender.entity.ProfessionEntity;

import java.util.List;

public interface ProfessionService {
    List<ProfessionEntity> InitProfession();
    String selectProfessionNameByProfessionId(int professionid);

}