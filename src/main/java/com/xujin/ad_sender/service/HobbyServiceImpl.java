package com.xujin.ad_sender.service;

import com.xujin.ad_sender.dao.HobbyDao;
import com.xujin.ad_sender.entity.HobbyEntity;
//import com.xujin.ad_sender.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HobbyServiceImpl implements HobbyService {
    @Autowired
    HobbyDao hobbyDao;

    /**
     * InitHobby Service
     *
     * @return
     */
    @Override
    public List<HobbyEntity> InitHobby() {
        return hobbyDao.InitHobby();
    }
}
