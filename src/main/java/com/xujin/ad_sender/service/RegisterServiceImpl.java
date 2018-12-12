package com.xujin.ad_sender.service;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.xujin.ad_sender.dao.ProfessionDao;
import com.xujin.ad_sender.dao.RegisterDao;
import com.xujin.ad_sender.entity.RegisterEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * \* Created with IntelliJ IDEA.
 * \* Description:
 * \* User: xujin
 * \* Date: 2018/3/16
 * \* Time: 21:50
 * \
 */
@Service
public class RegisterServiceImpl implements RegisterService {
    //
    @Autowired
    RegisterDao registerDao;
    @Autowired
    ProfessionDao professionDao;

    @Override
    public void register(RegisterEntity registerEntity) {
        registerDao.register(registerEntity);
    }

    @Override
    public Map<String, Object> getUserFeatures(String UserName, String keyword) {
        Map<String, Object> map = new HashMap<>();
        List<String> features = new ArrayList<>();
        RegisterEntity registerEntity = registerDao.getUserByUserName(UserName);
        String ProfessionName = professionDao.selectProfessionNameByProfessionId(registerEntity.getProfession());
        if (keyword != "") {
            features.add("\'" + keyword + "\'");
        }
        features.add("\'" + ProfessionName + "\'");
        JsonParser jsonParser = new JsonParser();
        JsonElement parse = jsonParser.parse(registerEntity.getHobby());
        JsonObject asJsonObject = parse.getAsJsonObject();
        JsonArray hobbySelected = asJsonObject.get("HobbySelected").getAsJsonArray();
        for (JsonElement i : hobbySelected) {
            features.add("\'" + i.getAsString() + "\'");
        }
        if (registerEntity.getAge() > 13 && registerEntity.getAge() <= 18) {
            map.put("SelectCrowd", "青少年(13~18)");
        } else if (registerEntity.getAge() > 18 && registerEntity.getAge() <= 30) {
            map.put("SelectCrowd", "青年(19~30)");
        } else if (registerEntity.getAge() > 30 && registerEntity.getAge() <= 50) {
            map.put("SelectCrowd", "中年(31~50)");
        } else {
            map.put("SelectCrowd", "老年(51~65)");
        }
        map.put("features", features);
        map.put("sex", registerEntity.getSex());
        return map;
    }
}