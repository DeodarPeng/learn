package cc.learn.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.FileSystemResource;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.io.IOException;

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

    /**
     * 配置 multipart 解析器
     */
    @Bean
    public MultipartResolver multipartResolver() throws IOException {
       /* CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver();
        //默认临时文件路径为 Servlet 容器的临时目录
        commonsMultipartResolver.setUploadTempDir(new FileSystemResource("/temp/uploads"));
        return commonsMultipartResolver;*/

    return  null;
    }


}
