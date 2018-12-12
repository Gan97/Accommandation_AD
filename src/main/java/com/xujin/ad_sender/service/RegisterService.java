package com.xujin.ad_sender.service;

import com.xujin.ad_sender.entity.RegisterEntity;

import java.util.List;
import java.util.Map;

public interface RegisterService {
    /**
     * 注册
     *
     * @param registerEntity
     */
    void register(RegisterEntity registerEntity);

    Map<String, Object> getUserFeatures(String UserName, String keyword);
}