package com.xujin.ad_sender.controller;

import com.xujin.ad_sender.entity.ADInfoEntity;
import com.xujin.ad_sender.entity.ConfigEntity;
import com.xujin.ad_sender.service.ADInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.sound.midi.Soundbank;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * \* Created with IntelliJ IDEA.
 * \* Description: 主页控制层
 * \* User: xujin
 * \* Date: 2018/1/30
 * \* Time: 11:19
 * \
 */
@RestController
@RequestMapping("/index")
public class IndexController {
    @Autowired
    ADInfoService adInfoService;
    @Autowired
    private ConfigEntity configEntity;

    @PostMapping("/get_search")
    public List<String> get_search(String keyword, String PrePageNum) {
        try {
            String[] args = new String[]{"python3.6", configEntity.getController_config().get("searchpath"), keyword, PrePageNum};
//            String[] args = new String[]{"python3.5", configEntity.getController_config().get("searchpath"), keyword, PrePageNum};
            System.out.println("start_search.................");
            Process pr = Runtime.getRuntime().exec(args);
//            解决Windows乱码问题
//            InputStreamReader inputStreamReader = new InputStreamReader(pr.getInputStream(), "GBK");
            InputStreamReader inputStreamReader = new InputStreamReader(pr.getInputStream());
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String t;
            StringBuffer str = new StringBuffer();
            while ((t = bufferedReader.readLine()) != null) {
                str.append(t);
            }
            bufferedReader.close();
            inputStreamReader.close();
            pr.waitFor();
            System.out.println("end_search.................");
            return splitStr(str.toString());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private List<String> splitStr(String str) {
        Pattern r = Pattern.compile("(^\\[)(.*)(]$)");
        Matcher matcher = r.matcher(str);
        String[] aa = new String[0];
        List<String> search_list = new ArrayList<>();
        if (matcher.find()) {
            aa = matcher.group(2).split(", ");
        }
        for (String tt : aa) {
            search_list.add(tt.replaceAll("\'", "\""));
        }
        return search_list;
    }

    @GetMapping("/getRecommendAD")
    public Map<String, Object> getADimg(HttpSession session, String keyword) {
        Map<String, Object> map = new HashMap<>();
        String username = session.getAttribute(WebSecurityConfig.SESSION_KEY).toString();
        ADInfoEntity recomAD = adInfoService.recommendAD(username, keyword);
        map.put("picture", recomAD.getUploadPicture());
        map.put("describe", recomAD.getADDescribe());
        return map;
    }
}

