package com.xujin.ad_sender.controller;

import com.xujin.ad_sender.entity.RegisterEntity;
import com.xujin.ad_sender.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * \* Created with IntelliJ IDEA.
 * \* Description: 注册控制层
 * \* User: xujin
 * \* Date: 2018/3/23
 * \* Time: 10:15
 * \
 */
@RestController
@RequestMapping("/Register")
public class RegisterController {

    private final RegisterService registerService;

    @Autowired
    public RegisterController(RegisterService registerService) {
        this.registerService = registerService;
    }

    /**
     * register结果
     *
     * @param registerEntity
     * @return
     */
    @PostMapping("/register")
    public Map<String, Object> register(RegisterEntity registerEntity) {
        Map<String, Object> map = new HashMap<>();
        System.out.println(registerEntity.toString());
        if (registerEntity != null) {
            registerService.register(registerEntity);
            map.put("success", true);
            map.put("message", "register successful");
            map.put("code", "200");
        } else {
            map.put("success", false);
            map.put("message", "register failed!");
            map.put("code", "505");
        }
        return map;
    }
}