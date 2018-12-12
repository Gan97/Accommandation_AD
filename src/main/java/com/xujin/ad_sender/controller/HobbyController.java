package com.xujin.ad_sender.controller;

import com.xujin.ad_sender.entity.HobbyEntity;
import com.xujin.ad_sender.service.HobbyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/hobby")
public class HobbyController {
    @Autowired
    HobbyService hobbyService;

    /**
     * 初始化hobby框
     *
     * @return
     */
    @GetMapping("/inithobby")
    public List<HobbyEntity> inithobby() {
        return hobbyService.InitHobby();
    }
}

