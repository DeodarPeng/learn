package cc.learn.rpc; /*
                       @author Cedar
                       @DESCRIPTION
                       @create 2019/3/6
                      */

import cc.learn.jms.AlertService;
import cc.learn.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.remoting.JmsInvokerServiceExporter;
import org.springframework.remoting.httpinvoker.HttpInvokerProxyFactoryBean;
import org.springframework.remoting.httpinvoker.HttpInvokerServiceExporter;
import org.springframework.remoting.jaxws.JaxWsPortProxyFactoryBean;
import org.springframework.remoting.jaxws.SimpleJaxWsServiceExporter;
import org.springframework.remoting.rmi.RmiProxyFactoryBean;
import org.springframework.remoting.rmi.RmiServiceExporter;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.handler.SimpleUrlHandlerMapping;

import javax.annotation.Resource;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

@Component
public class RpcExport {
  @Resource(name = "userServiceImpl")
  private UserService userService;

  /**
   * @author Cedar
   * @DESCRIPTION:将 UserService 导出为 RMI 服务
   * @params: [userService]
   * @return: org.springframework.remoting.rmi.RmiServiceExporter @Date: 2019/3/6 17:26
   */
  @Bean
  public RmiServiceExporter rmiServiceExporter( ) {
    RmiServiceExporter rmiServiceExporter = new RmiServiceExporter();
    rmiServiceExporter.setService(userService);
    rmiServiceExporter.setServiceName("UserService");
    rmiServiceExporter.setServiceInterface(UserService.class);
    // 默认绑定本机的 1099 端口上的注册表，没有注册表则会启动一个
//    rmiServiceExporter.setRegistryHost("127.0.0.1");
    rmiServiceExporter.setRegistryPort(1199);
    return rmiServiceExporter;
  }

  /**
   * @author Cedar
   * @DESCRIPTION: 客户端装配 RMI 服务
   * @params: []
   * @return: org.springframework.remoting.rmi.RmiProxyFactoryBean @Date: 2019/6/19 10:42
   */
  /*@Bean
  public RmiProxyFactoryBean userService(){
      RmiProxyFactoryBean rmiProxyFactoryBean = new RmiProxyFactoryBean();
      rmiProxyFactoryBean.setServiceUrl("rmi://localhost:1199/UserService");
      rmiProxyFactoryBean.setServiceInterface(UserService.class);
      return  rmiProxyFactoryBean;
  }*/

  /**
   * @author Cedar
   * @DESCRIPTION: 导出为基于 HTTP Invoker 的服务
   * @params: [userService]
   * @return: org.springframework.remoting.httpinvoker.HttpInvokerServiceExporter @Date: 2019/3/8
   *     15:01
   */
  @Bean
  public HttpInvokerServiceExporter httpExporterUserService( ) {
    HttpInvokerServiceExporter httpInvokerServiceExporter = new HttpInvokerServiceExporter();
    httpInvokerServiceExporter.setService(userService);
    httpInvokerServiceExporter.setServiceInterface(UserService.class);
    return httpInvokerServiceExporter;
  }

  /**
   * @author Cedar
   * @DESCRIPTION: 建立一个 URL 处理器，映射 HTTP URL 到对应的服务上
   * @params: []
   * @return: org.springframework.web.servlet.HandlerMapping
   * @Date: 2019/6/19 11:10
  */
  @Bean
  public HandlerMapping httpInvokerMapping() {
    SimpleUrlHandlerMapping mapping = new SimpleUrlHandlerMapping();
    Properties mappings = new Properties();
    mappings.setProperty("/user.service", "httpExporterUserService");
    mapping.setMappings(mappings);
    return mapping;
  }

  /**
   * @author Cedar
   * @DESCRIPTION: 通过 HTTP 访问服务,配置代理 bean
   * @params: []
   * @return: org.springframework.remoting.httpinvoker.HttpInvokerProxyFactoryBean
   * @Date: 2019/6/19 11:11
  */
  @Bean
  public HttpInvokerProxyFactoryBean httpInvokerUserService() {
    HttpInvokerProxyFactoryBean invokerProxyFactoryBean = new HttpInvokerProxyFactoryBean();
    invokerProxyFactoryBean.setServiceUrl("http://localhost:6666/User/user.service");
    invokerProxyFactoryBean.setServiceInterface(UserService.class);
    return invokerProxyFactoryBean;
  }

  /**
   * @author Cedar
   * @DESCRIPTION: 导出 JAX-WS 端点
   * @params: []
   * @return: org.springframework.remoting.jaxws.SimpleJaxWsServiceExporter
   * @Date: 2019/6/19 13:48
  */
  @Bean
  public SimpleJaxWsServiceExporter jaxWsServiceExporter() {
    SimpleJaxWsServiceExporter simpleJaxWsServiceExporter = new SimpleJaxWsServiceExporter();
    simpleJaxWsServiceExporter.setBaseAddress("http://localhost:2223/services/");
    return simpleJaxWsServiceExporter;
  }

  /**
   * @author Cedar
   * @DESCRIPTION: 客户端代理 JAX-WS 服务
   * @params: []
   * @return: org.springframework.remoting.jaxws.JaxWsPortProxyFactoryBean
   * @Date: 2019/6/19 13:48
  */
  /*@Bean
  public JaxWsPortProxyFactoryBean jaxWsUserService() throws MalformedURLException {
    JaxWsPortProxyFactoryBean proxyFactoryBean = new JaxWsPortProxyFactoryBean();
    URL url = new URL("http://localhost:6665/services/?wsdl");
    proxyFactoryBean.setWsdlDocumentUrl(url);
    proxyFactoryBean.setServiceName("userService");
    proxyFactoryBean.setPortName("userServiceHttpPort");
    proxyFactoryBean.setServiceInterface(UserService.class);
    proxyFactoryBean.setNamespaceUri("http://user.com");
    return proxyFactoryBean;
  }*/

  /**
   * @author Cedar
   * @DESCRIPTION: 导出基于 JMS 的服务
   * @params:
   * @return:
   * @Date: 2019/6/19 14:33
  */
  @Bean
  public JmsInvokerServiceExporter jmsInvokerServiceExporter(AlertService alertService){
      JmsInvokerServiceExporter jmsInvokerServiceExporter = new JmsInvokerServiceExporter();
      jmsInvokerServiceExporter.setService(alertService);
      jmsInvokerServiceExporter.setServiceInterface(AlertService.class);
      return jmsInvokerServiceExporter;
  }

}
