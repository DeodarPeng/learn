package cc.learn.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * @Description:
 * @author: C
 * @date: 2018年11月14日 上午9:28:55
 */
@Configuration
@ComponentScan(basePackages = { "cc.learn" }, excludeFilters = {
        @ComponentScan.Filter(type = FilterType.ANNOTATION, value = EnableWebMvc.class) })
@Import({ DataSourceConfig.class, MybatisConfig.class, ThreadConfig.class })
//@ImportResource("classpath:spring/spring.xml")
public class RootConfig {

    @Bean
    public static PropertySourcesPlaceholderConfigurer placeholderConfigurer() {
        PropertySourcesPlaceholderConfigurer placeholderConfigurer = new PropertySourcesPlaceholderConfigurer();
        return placeholderConfigurer;
    }
}
