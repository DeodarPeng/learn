import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cc.learn.config.RootConfig;
import cc.learn.service.UserService;

/**
 * @Description:
 * @author: C
 * @date: 2019年1月14日 上午11:44:39
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {RootConfig.class})
@ActiveProfiles("prod")
public class UserTest {

    @Autowired
    private UserService userService;
    @Autowired
    private Environment env;

    @Test
    public void listAllUserTest() {
        System.out.println(userService.listAllUser());
    }

    @Test
    public void envTest() {
        String url = env.getProperty("jdbc.url");
        System.out.println(url);
    }

    @Test
    public void profileTest() {
        System.out.println(Arrays.toString(env.getActiveProfiles()));
    }
}
