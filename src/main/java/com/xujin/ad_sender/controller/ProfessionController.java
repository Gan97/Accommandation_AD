package com.xujin.ad_sender.controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.xujin.ad_sender.entity.ProfessionEntity;
import com.xujin.ad_sender.service.ProfessionService;
import jdk.nashorn.internal.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/profession")
public class ProfessionController {
    @Autowired
    ProfessionService professionService;

    /**
     * 初始化profession框
     *
     * @return
     */
    @GetMapping("/initprofession")
    public List<ProfessionEntity> initprofession() {
        return professionService.InitProfession();
    }

//    小程序后台测试代码
//    @PostMapping("/user")
//    public String user(@RequestBody String data) {
//        Gson gson = new Gson();
//        JsonObject object = gson.fromJson(data, JsonObject.class);
//        System.out.println(object);
//        System.out.println("收到了");
//        return "收到了";
//    }
}

