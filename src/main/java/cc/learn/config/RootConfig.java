package cc.learn.config;

import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.apache.activemq.spring.ActiveMQConnectionFactory;
import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.connection.SingleConnectionFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.listener.DefaultMessageListenerContainer;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.jms.ConnectionFactory;
import javax.jms.MessageListener;
import java.io.IOException;

/**
 * @Description:
 * @author: C
 * @date: 2018年11月14日 上午9:28:55
 */
@Configuration
@ComponentScan(basePackages = {"cc.learn.*"}, excludeFilters = {
        @ComponentScan.Filter(type = FilterType.ANNOTATION, value = EnableWebMvc.class)})
//@Import({ DataSourceConfig.class, MybatisConfig.class, ThreadConfig.class })
@ImportResource("classpath:spring/spring.xml")
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
        return null;
    }


    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

    /*
     * @author Cedar
     * @DESCRIPTION: ActiveMQ 连接工厂
     * @params: []
     * @return: org.apache.activemq.spring.ActiveMQConnectionFactory
     * @Date: 2019/6/10 10:21
     */
    @Bean
    public ActiveMQConnectionFactory activeMQConnectionFactory(){
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory();
        //指定代理的url
        activeMQConnectionFactory.setBrokerURL("tcp://localhost:61616");
        return  activeMQConnectionFactory;
    }

    @Bean
    public ConnectionFactory connectionFactory(ActiveMQConnectionFactory activeMQConnectionFactory){
        SingleConnectionFactory singleConnectionFactory =new  SingleConnectionFactory();
        singleConnectionFactory.setTargetConnectionFactory(activeMQConnectionFactory);
        return  singleConnectionFactory;
    }

    /**
     * @author Cedar
     * @DESCRIPTION: 声明消息目的地(队列)
     * @params: []
     * @return: org.apache.activemq.command.ActiveMQQueue
     * @Date: 2019/6/10 10:45
    */
    @Bean
    public ActiveMQQueue activeMQQueue(){
        ActiveMQQueue activeMQQueue = new ActiveMQQueue("learn.queue");
        return  activeMQQueue;
    }
    /**
     * @author Cedar
     * @DESCRIPTION: 声明消息目的地(主题)
     * @params: []
     * @return: org.apache.activemq.command.ActiveMQQueue
     * @Date: 2019/6/10 10:45
     */
    @Bean
    public ActiveMQTopic activeMQTopic(){
        ActiveMQTopic activeMQTopic = new ActiveMQTopic("learn.topic");
        return  activeMQTopic;
    }

    /**
     * @author Cedar
     * @DESCRIPTION: 声明JmsTemplate
     * @params: []
     * @return: org.springframework.jms.core.JmsTemplate
     * @Date: 2019/6/11 10:42
     * @param activeMQConnectionFactory
    */
    @Bean
    public JmsTemplate jmsTemplate(ConnectionFactory connectionFactory){
        JmsTemplate jmsTemplate = new JmsTemplate(connectionFactory);
        //指定默认目的地
        jmsTemplate.setDefaultDestinationName("learn.alert.queue");
        //jmsTemplate.setDefaultDestination(activeMQTopic);
        //指定消息转换器。默认为SimpleMessageConverter
        //jmsTemplate.setMessageConverter();
        return  jmsTemplate;
    }

   /* @Bean
    public DefaultMessageListenerContainer defaultMessageListenerContainer(ConnectionFactory connectionFactory,MessageListener messageListener){
        DefaultMessageListenerContainer defaultMessageListenerContainer = new DefaultMessageListenerContainer();
        defaultMessageListenerContainer.setConnectionFactory(connectionFactory);
        defaultMessageListenerContainer.setMessageListener(messageListener);
        defaultMessageListenerContainer.setDestinationName("");
        return  defaultMessageListenerContainer;
    }*/
}
