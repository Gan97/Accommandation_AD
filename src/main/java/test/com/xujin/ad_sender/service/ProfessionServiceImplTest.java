package test.com.xujin.ad_sender.service;

import com.xujin.ad_sender.AdSenderApplication;
import com.xujin.ad_sender.service.ProfessionService;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * ProfessionServiceImpl Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>三月 30, 2018</pre>
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AdSenderApplication.class)
public class ProfessionServiceImplTest {

    @Autowired
    ProfessionService professionService;

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: InitProfession()
     */
    @Test
    public void testInitProfession() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: selectProfessionNameByProfessionId(int professionid)
     */
    @Test
    public void testSelectProfessionNameByProfessionId() throws Exception {
//TODO: Test goes here...
        System.out.println(professionService.selectProfessionNameByProfessionId(1));
    }


} 
