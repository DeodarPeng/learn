import java.util.Arrays;

import cc.learn.config.WebConfig;
import cc.learn.controller.PublicController;
import cc.learn.controller.UserController;
import cc.learn.service.impl.UserServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cc.learn.config.RootConfig;
import cc.learn.service.UserService;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.test.web.servlet.setup.StandaloneMockMvcBuilder;
import sun.security.util.Length;


/**
 * @Description:
 * @author: C
 * @date: 2019年1月14日 上午11:44:39
 */
//@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {RootConfig.class, WebConfig.class})
@ActiveProfiles("prod")
public class UserTest {

    @Autowired
    private UserService userService;
    @Autowired
    private Environment env;

   // @Test
    public void envTest() {
        String url = env.getProperty("jdbc.url");
        System.out.println(url);
    }

    //@Test
    public void profileTest() {
        System.out.println(Arrays.toString(env.getActiveProfiles()));
    }


}
