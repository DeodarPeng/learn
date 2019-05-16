package cc.learn.rpc;/*
 @author Cedar
 @DESCRIPTION 
 @create 2019/3/6
*/

import cc.learn.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.remoting.httpinvoker.HttpInvokerProxyFactoryBean;
import org.springframework.remoting.httpinvoker.HttpInvokerServiceExporter;
import org.springframework.remoting.jaxws.JaxWsPortProxyFactoryBean;
import org.springframework.remoting.jaxws.SimpleJaxWsServiceExporter;
import org.springframework.remoting.rmi.RmiProxyFactoryBean;
import org.springframework.remoting.rmi.RmiServiceExporter;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.handler.SimpleUrlHandlerMapping;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

@Component
public class RpcExport {

    /**
     * @author Cedar
     * @DESCRIPTION:将 UserService 导出为 RMI 服务
     * @params: [userService]
     * @return: org.springframework.remoting.rmi.RmiServiceExporter
     * @Date: 2019/3/6 17:26
    */
    @Bean
    public RmiServiceExporter rmiServiceExporter(UserService userService){
        RmiServiceExporter rmiServiceExporter = new RmiServiceExporter();
        rmiServiceExporter.setService(userService);
        rmiServiceExporter.setServiceName("UserService");
        rmiServiceExporter.setServiceInterface(UserService.class);
        //默认绑定本机的 1099 端口上的注册表，没有注册表则会启动一个
        rmiServiceExporter.setRegistryHost("127.0.1.1");
        rmiServiceExporter.setRegistryPort(1199);
        return  rmiServiceExporter;
    }

    @Bean
    public RmiProxyFactoryBean userService(){
        RmiProxyFactoryBean rmiProxyFactoryBean = new RmiProxyFactoryBean();
        rmiProxyFactoryBean.setServiceUrl("rmi://localhost/UserService");
        rmiProxyFactoryBean.setServiceInterface(UserService.class);
        return  rmiProxyFactoryBean;
    }

    /**
     * @author Cedar
     * @DESCRIPTION: 导出为基于 HTTP Invoker 的服务
     * @params: [userService]
     * @return: org.springframework.remoting.httpinvoker.HttpInvokerServiceExporter
     * @Date: 2019/3/8 15:01
    */
    @Bean
    public HttpInvokerServiceExporter httpExporterUserService(UserService userService){
        HttpInvokerServiceExporter httpInvokerServiceExporter = new HttpInvokerServiceExporter();
        httpInvokerServiceExporter.setService(userService);
        httpInvokerServiceExporter.setServiceInterface(UserService.class);
        return httpInvokerServiceExporter;
    }

    @Bean
    public HandlerMapping httpInvokerMapping(){
        SimpleUrlHandlerMapping mapping = new SimpleUrlHandlerMapping();
        Properties mappings = new Properties();
        mappings.setProperty("/user.service","httpExporterUserService");
        mapping.setMappings(mappings);
        return  mapping;
    }

    @Bean
    public HttpInvokerProxyFactoryBean httpInvokerUserService(){
        HttpInvokerProxyFactoryBean invokerProxyFactoryBean = new HttpInvokerProxyFactoryBean();
        invokerProxyFactoryBean.setServiceUrl("http://localhost:8080/User/user.service");
        invokerProxyFactoryBean.setServiceInterface(UserService.class);
        return  invokerProxyFactoryBean;
    }

    @Bean
    public SimpleJaxWsServiceExporter jaxWsServiceExporter(){
        SimpleJaxWsServiceExporter simpleJaxWsServiceExporter = new SimpleJaxWsServiceExporter();
        //默认
        simpleJaxWsServiceExporter.setBaseAddress("http://localhost:8080/");
        return simpleJaxWsServiceExporter;
    }

    @Bean
    public JaxWsPortProxyFactoryBean jaxWsUserService() throws MalformedURLException {
        JaxWsPortProxyFactoryBean proxyFactoryBean = new JaxWsPortProxyFactoryBean();
        URL url = new URL("http://localhost:8080");
        proxyFactoryBean.setWsdlDocumentUrl(url);
        proxyFactoryBean.setServiceName("userService");
        proxyFactoryBean.setPortName("userServiceHttpPort");
        proxyFactoryBean.setServiceInterface(UserService.class);
        proxyFactoryBean.setNamespaceUri("http://user.com");
        return  proxyFactoryBean;
    }
}
