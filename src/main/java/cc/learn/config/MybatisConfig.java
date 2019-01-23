package cc.learn.config;

import java.io.IOException;

import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

/**
 * @Description:
 * @author: C
 * @date: 2018年11月14日 上午11:08:32
 */
@Configuration
@MapperScan("cc.learn.mapper")
public class MybatisConfig {

    private static final Log logger = LogFactory.getLog(MybatisConfig.class);

    @Bean
    public SqlSessionFactory sessionFactory(DataSource dataSource) {
        try {
            SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
            sqlSessionFactoryBean.setDataSource(dataSource);
            sqlSessionFactoryBean.setTypeAliasesPackage("cc.learn.po");
            // 配置mapper的扫描，找到所有的mapper.xml映射文件
            Resource[] resources = new PathMatchingResourcePatternResolver()
                    .getResources("classpath:mybatis/mapper/*.xml");
            sqlSessionFactoryBean.setMapperLocations(resources);
            // 加载全局的配置文件
            //            sqlSessionFactoryBean
            //                    .setConfigLocation(new DefaultResourceLoader().getResource("classpath:mybatis/mybatis-config.xml"));
            return sqlSessionFactoryBean.getObject();
        } catch (IOException e) {
            logger.warn("mybatis resolver mapper*xml is error");
            return null;
        } catch (Exception e) {
            logger.warn("mybatis sqlSessionFactoryBean create error");
            return null;
        }
    }
}
