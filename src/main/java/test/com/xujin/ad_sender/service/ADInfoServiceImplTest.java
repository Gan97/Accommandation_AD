package test.com.xujin.ad_sender.service;

import com.xujin.ad_sender.AdSenderApplication;
import com.xujin.ad_sender.dao.ADInfoDao;
import com.xujin.ad_sender.entity.ADInfoEntity;
import com.xujin.ad_sender.entity.ConfigEntity;
import com.xujin.ad_sender.entity.RecommendEntity;
import com.xujin.ad_sender.service.ADInfoServiceImpl;
import com.xujin.ad_sender.service.RegisterService;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * ADInfoServiceImpl Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>三月 30, 2018</pre>
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AdSenderApplication.class)
public class ADInfoServiceImplTest {

    @Autowired
    ADInfoDao adInfoDao;
    @Autowired
    RegisterService registerService;
    @Autowired
    ADInfoServiceImpl adInfoServiceimpl;
    @Autowired
    private ConfigEntity configEntity;

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: InitADInformation()
     */
    @Test
    public void testInitADInformation() throws Exception {
//TODO: Test goes here...
        System.out.println(configEntity.getController_config().get("uploadpath"));
    }

    /**
     * Method: addADInfo(ADInfoEntity adInfoEntity)
     */
    @Test
    public void testAddADInfo() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: updateADInfo(ADInfoEntity adInfoEntity)
     */
    @Test
    public void testUpdateADInfo() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: deleteADInfo(Integer ADID)
     */
    @Test
    public void testDeleteADInfo() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: recommendAD(String UserName)
     */
    @Test
    public void testRecommendAD() throws Exception {
//TODO: Test goes here...

    }

    /**
     * Method: getsimiliraty(String str1, String str2)
     */
    @Test
    public void testGetsimiliraty() throws Exception {
//TODO: Test goes here...
        try {
//            "['计算机软件', '篮球', '电影', '排球']"   "['计算机软件', 'IT服务']"
            String[] args = new String[]{"python3.6", "../ad_sender/src/main/java/com/xujin/ad_sender/py/SimilarityCalculate.py", "['计算机软件', '篮球', '电影', '排球']", "['计算机软件', 'IT服务']"};
            System.out.println("start_calculate.................");
            for (String i : args) {
                System.out.println(i);
            }
            Process pr = Runtime.getRuntime().exec(args);
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
            System.out.println("end_calculate.................");
            System.out.println(str.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Method: ADsort(List<RecommendEntity> recommendEntities)
     */
    @Test
    public void testADsort() throws Exception {
        String UserName = "xu", keyword = "spring";
        List<RecommendEntity> recommendEntities = new ArrayList<>();
        Map<String, Object> userFeatures = registerService.getUserFeatures(UserName, keyword);
        List<ADInfoEntity> adList = adInfoDao.RecommendADList(userFeatures.get("sex").toString());
        System.out.println(adList);
        for (ADInfoEntity adInfoEntity : adList) {
            RecommendEntity recommendEntity = new RecommendEntity();
            recommendEntity.setAdInfoEntity(adInfoEntity);
            recommendEntity.setSimiler((float) 1);
            recommendEntities.add(recommendEntity);
        }
        System.out.println(userFeatures);
        System.out.println(recommendEntities);
        System.out.println("----------------------");
        System.out.println(adInfoServiceimpl.ADsort(recommendEntities));

//TODO: Test goes here... 
    }


} 
