package test.com.xujin.ad_sender.service;

import com.xujin.ad_sender.AdSenderApplication;
import com.xujin.ad_sender.service.RegisterService;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;

/**
 * RegisterServiceImpl Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>三月 30, 2018</pre>
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AdSenderApplication.class)
public class RegisterServiceImplTest {

    @Autowired
    RegisterService registerService;

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: register(RegisterEntity registerEntity)
     */
    @Test
    public void testRegister() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: getUserFeatures(String UserName)
     */
    @Test
    public void testGetUserFeatures() throws Exception {
        String user = "xu", keyword = "spring";
        Map<String, Object> map = registerService.getUserFeatures(user, keyword);
        System.out.println(map);
//TODO: Test goes here... 
    }


} 
