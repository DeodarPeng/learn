package cc.learn.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * @Description:
 * @author: C
 * @date: 2018年11月14日 上午9:28:42
 */
@Configuration
@ComponentScan("cc.learn.controller")
@EnableWebMvc
public class WebConfig {

}
