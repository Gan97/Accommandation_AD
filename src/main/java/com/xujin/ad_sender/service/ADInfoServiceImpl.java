package com.xujin.ad_sender.service;

import com.google.gson.*;
import com.xujin.ad_sender.dao.ADInfoDao;
import com.xujin.ad_sender.entity.ADInfoEntity;
import com.xujin.ad_sender.entity.ConfigEntity;
import com.xujin.ad_sender.entity.RecommendEntity;
import org.apache.tomcat.util.http.fileupload.util.Streams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.util.*;

/**
 * \* Created with IntelliJ IDEA.
 * \* Description:
 * \* User: xujin
 * \* Date: 2018/3/24
 * \* Time: 18:25
 * \
 */
@Service
public class ADInfoServiceImpl implements ADInfoService {
    @Autowired
    ADInfoDao adInfoDao;
    @Autowired
    RegisterService registerService;
    @Autowired
    private ConfigEntity configEntity;

    @Override
    public List<ADInfoEntity> InitADInformation() {
        return adInfoDao.InitADInformation();
    }

    @Override
    public void addADInfo(ADInfoEntity adInfoEntity) {
        adInfoDao.addADInfo(adInfoEntity);
    }

    @Override
    public void updateADInfo(ADInfoEntity adInfoEntity) {
        adInfoDao.updateADInfo(adInfoEntity);
    }

    @Override
    public void deleteADInfo(Integer ADID) {
        adInfoDao.deleteADInfo(ADID);
    }

    /**
     * 获取推荐广告
     *
     * @param UserName
     * @return
     */
    @Override
    public ADInfoEntity recommendAD(String UserName, String keyword) {
        List<RecommendEntity> recommendEntities = new ArrayList<>();
        Map<String, Object> userFeatures = registerService.getUserFeatures(UserName, keyword);
        System.out.println("userFeatures: " + userFeatures);
        //先跟据性别取出初始化的推荐列表
        List<ADInfoEntity> adList = adInfoDao.RecommendADList(userFeatures.get("sex").toString());
        for (ADInfoEntity adInfoEntity : adList) {
            if (adInfoEntity.getSelectCrowd().equals(userFeatures.get("SelectCrowd"))) {
                RecommendEntity recommendEntity = new RecommendEntity();
                recommendEntity.setAdInfoEntity(adInfoEntity);
                recommendEntities.add(recommendEntity);
            }
        }
        System.out.println("recommendEntities.size:" + recommendEntities.size());
        for (RecommendEntity recommendEntity : recommendEntities) {
            JsonParser jsonParser = new JsonParser();
            JsonElement parse = jsonParser.parse(recommendEntity.getAdInfoEntity().getADClasses());
            JsonObject asJsonObject = parse.getAsJsonObject();
            JsonArray ADClassesSelected = asJsonObject.get("ADClassesSelected").getAsJsonArray();
            //计算相似度 similiraty
            List<String> list = new ArrayList<>();
            for (JsonElement i : ADClassesSelected) {
                list.add("\'" + i.getAsString() + "\'");
            }
            String similiraty = getsimiliraty(userFeatures.get("features").toString(), list.toString());
            System.out.println("similiraty:" + similiraty);
            recommendEntity.setSimiler(Float.valueOf(similiraty));
        }
        recommendEntities = ADsort(recommendEntities);
        System.out.println(recommendEntities);
        return recommendEntities.get(0).getAdInfoEntity();
    }

    /**
     * 计算相似度函数
     *
     * @param str1
     * @param str2
     * @return
     */
    public String getsimiliraty(String str1, String str2) {
        try {
            String[] args = new String[]{"python3.6", configEntity.getService_config().get("similiratypath"), str1, str2};
//            String[] args = new String[]{"python3.5", configEntity.getService_config().get("similiratypath"), str1, str2};
            System.out.println("start_calculate.................");
            Process pr = Runtime.getRuntime().exec(args);
            InputStreamReader inputStreamReader = new InputStreamReader(pr.getInputStream());
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String t;
            StringBuffer str = new StringBuffer();
            while ((t = bufferedReader.readLine()) != null) {
                str.append(t);
//                System.out.println(t);
            }
            bufferedReader.close();
            inputStreamReader.close();
            pr.waitFor();
            System.out.println("end_calculate.................");
            return str.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    /**
     * 排序，先根据相似度排序，相似度相同再根据价格排序
     *
     * @param recommendEntities
     * @return
     */
    public List<RecommendEntity> ADsort(List<RecommendEntity> recommendEntities) {
        Collections.sort(recommendEntities, (o1, o2) -> {
            if (o1.getSimiler() - o2.getSimiler() > 0) {
                return -1;
            } else if (o1.getSimiler() - o2.getSimiler() == 0) {
                Integer x = o1.getAdInfoEntity().getRTBPrice();
                Integer y = o2.getAdInfoEntity().getRTBPrice();
                return (x > y) ? -1 : ((x == y) ? 0 : 1);
            } else {
                return 1;
            }
        });
        return recommendEntities;
    }
}