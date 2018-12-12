package com.xujin.ad_sender.controller;

import com.google.gson.Gson;
import com.xujin.ad_sender.entity.ADInfoEntity;
import com.xujin.ad_sender.entity.ConfigEntity;
import com.xujin.ad_sender.service.ADInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

/**
 * \* Created with IntelliJ IDEA.
 * \* Description: DSP主页控制层
 * \* User: xujin
 * \* Date: 2018/3/24
 * \* Time: 18:04
 * \
 */
@RestController
@RequestMapping("/dsp")
public class DspIndexController {
    @Autowired
    ADInfoService adInfoService;
    @Autowired
    private ConfigEntity configEntity;

    @GetMapping("/InitADInformation")
    public List<ADInfoEntity> InitADInformation() {
        return adInfoService.InitADInformation();
    }

    /**
     * 提交图片
     *
     * @param file
     * @return
     */
    @PostMapping("/upload")
    public Map<String, Object> upload(MultipartFile file) {
        Map<String, Object> map = new HashMap<>();
        String path = configEntity.getController_config().get("uploadpath");
        String fileName = file.getOriginalFilename();
        try {
            new FileWriter(path + fileName);
            file.transferTo(new File(path + fileName));
            map.put("state", 1);
            map.put("message", "upload successful");
            map.put("picturePath", path + fileName);
        } catch (IOException e) {
            e.printStackTrace();
            map.put("state", 0);
        }
        return map;
    }

    //    广告路径 images/upload_images/

    /**
     * 添加广告
     *
     * @param adInfo
     * @return
     */
    @PostMapping("/addADInfo")
    public Map<String, Object> addADInfo(@RequestBody String adInfo) {
        String url = "upload_images/";
        ADInfoEntity adInfoEntity = new Gson().fromJson(adInfo, ADInfoEntity.class);
        Map<String, Object> map = new HashMap<>();
        System.out.println(adInfoEntity.getADTitle());
        System.out.println(adInfoEntity.getADClasses());
        adInfoEntity.setUploadPicture(url + adInfoEntity.getUploadPicture());
        adInfoService.addADInfo(adInfoEntity);
        map.put("success", true);
        map.put("message", "ADD successful");
        map.put("code", "200");
        return map;
    }

    /**
     * 更新广告信息
     *
     * @param adInfo
     */
    @PostMapping("/updateADInfo")
    public Map<String, Object> updateADInfo(@RequestBody String adInfo) {
        Map<String, Object> map = new HashMap<>();
        ADInfoEntity updateAdInfo = new Gson().fromJson(adInfo, ADInfoEntity.class);
        System.out.println(updateAdInfo.toString());
        adInfoService.updateADInfo(updateAdInfo);
        map.put("success", true);
        map.put("message", "Update successful");
        map.put("code", "200");
        return map;
    }

    /**
     * 删除广告信息
     *
     * @param ADID
     */
    @PostMapping("/deleteADInfo")
    public Map<String, Object> deleteADInfo(String ADID) {
        Map<String, Object> map = new HashMap<>();
        adInfoService.deleteADInfo(Integer.valueOf(ADID));
        map.put("success", true);
        map.put("message", "Delete successful");
        map.put("code", "200");
        return map;
    }
}
