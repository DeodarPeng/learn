package cc.learn.config;

import java.beans.PropertyVetoException;
import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.mchange.v2.c3p0.ComboPooledDataSource;

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

  /*  @Bean
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
//    @Profile("prod")
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

}
