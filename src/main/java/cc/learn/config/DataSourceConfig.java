package cc.learn.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;

/**
 * @Description:
 * @author: C
 * @date: 2018年11月14日 上午10:18:34
 */
@Configuration
@EnableTransactionManagement
@PropertySource(value = {"classpath:db.properties"})
public class DataSourceConfig {

    @Value("${jdbc.user}")
    private String user;
    @Value("${jdbc.driver}")
    private String driver;
    @Value("${jdbc.password}")
    private String password;
    @Value("${jdbc.url}")
    private String url;

    /*@Bean
    @Profile("dev")
    public DataSource devDataSource() throws PropertyVetoException {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setDriverClass(driver);
        dataSource.setJdbcUrl("url");
        dataSource.setUser("user");
        dataSource.setPassword("pwd");
        return dataSource;
    }*/

    @Bean
   // @Profile("prod")
    public DataSource prodDataSource() throws PropertyVetoException {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setDriverClass(driver);
        dataSource.setJdbcUrl(url);
        dataSource.setUser(user);
        dataSource.setPassword(password);
        return dataSource;
    }

    @Bean
    public DataSourceTransactionManager transactionManager(DataSource dataSource) {
        DataSourceTransactionManager transactionManager = new DataSourceTransactionManager();
        transactionManager.setDataSource(dataSource);
        return transactionManager;
    }

    /**
     * 配置 JDBC 驱动的数据源
     * DriverManagerDataSource
     */
   /* @Bean
    public DataSource dataSource(){
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setDriverClassName("");
        ds.setUrl("");
        ds.setUsername("");
        ds.setPassword("");
        return  ds;
    }*/

    /**
     * 配置 JdbcTemplate
     *
     * @param dataSource
     * @return
     */
    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    /**
     * @author Cedar
     * @DESCRIPTION: 配置redis连接工厂  --> CachingConfig
     * @params: []
     * @return: org.springframework.data.redis.connection.RedisConnectionFactory
     * @Date: 2019/2/26 17:02
     */
   @Bean
    public RedisConnectionFactory redisCF() {
        JedisConnectionFactory cf = new JedisConnectionFactory();
        cf.setHostName("127.0.0.1");
        cf.setPort(6379);
        cf.setPassword("password");
        return cf;
    }

   /*  --> CachingConfig
    @Bean
    public RedisTemplate<String, User> redisTemplate(RedisConnectionFactory cf) {
        RedisTemplate<String, User> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(cf);
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        return redisTemplate;
    }*/

}
