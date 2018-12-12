package com.xujin.ad_sender.controller;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.xujin.ad_sender.entity.UserEntity;
import com.xujin.ad_sender.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * \* Created with IntelliJ IDEA.
 * \* Description: 登录控制层
 * \* User: xujin
 * \* Date: 2018/3/16
 * \* Time: 21:14
 * \
 */
@Controller
public class LoginController {
    private final UserService userService;

    /**
     * 自动装配变量
     *
     * @param userService
     */
    @Autowired
    public LoginController(UserService userService) {
        this.userService = userService;
    }

    /**
     * 跳转登录页面
     *
     * @return
     */
    @GetMapping({"/login", "/"})
    public String login() {
        return "ad_login";
    }

    /**
     * 跳转注册页面
     *
     * @return
     */
    @GetMapping("/ad_register")
    public String register() {
        return "ad_register";
    }

    /**
     * 跳转广告展示页面
     *
     * @return
     */
    @GetMapping("/ad_index")
    public String index() {
        return "ad_index";
    }

    /**
     * 跳转dsp页面
     *
     * @return
     */
    @GetMapping("/ad_dsp_index")
    public String ad_dsp_index() {
        return "ad_dsp_index";
    }

    /**
     * login结果
     *
     * @param userName
     * @param password
     * @return
     */
    @ResponseBody
    @PostMapping("/login")
    public Map<String, Object> login(String userName, String password, HttpSession session) {
        System.out.println("输入的用户名和密码：" + userName + password);
        Map<String, Object> map = new HashMap<>();
        UserEntity user = userService.searchUserByName(userName);
        System.out.println("数据库中的用户名和密码：" + user.getUserName() + user.getPassWord());
        if (user != null) {
            if (password.equals(user.getPassWord())) {
                if (userName.equals("admin")) {
//                    session.setAttribute(WebSecurityConfig.SESSION_KEY, userName);
                    map.put("success", true);
                    map.put("message", "login in successful");
                    map.put("code", "201");
                    return map;
                } else {
                    session.setAttribute(WebSecurityConfig.SESSION_KEY, userName);
                    map.put("success", true);
                    map.put("message", "login in successful");
                    map.put("code", "200");
                    return map;
                }
            } else {
                map.put("success", false);
                map.put("message", "Username or password false");
                map.put("code", "403");
                return map;
            }
        } else {
            map.put("success", false);
            map.put("message", "User not exist!");
            map.put("code", "404");
            return map;
        }
    }
}
