package com.xujin.ad_sender.service;

import com.xujin.ad_sender.dao.ProfessionDao;
import com.xujin.ad_sender.entity.ProfessionEntity;
import com.xujin.ad_sender.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfessionServiceImpl implements ProfessionService {

    @Autowired
    ProfessionDao professionDao;

    /**
     * InitProfession Service
     *
     * @return
     */
    @Override
    public List<ProfessionEntity> InitProfession() {
        return professionDao.InitProfession();
    }

    @Override
    public String selectProfessionNameByProfessionId(int professionid) {
        return professionDao.selectProfessionNameByProfessionId(professionid) ;
    }
}