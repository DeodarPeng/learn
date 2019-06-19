package cc.learn.rpc.endpoint;/*
 @author Cedar
 @DESCRIPTION 
 @create 2019/3/8
*/

import cc.learn.po.User;
import cc.learn.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.annotation.Resource;
import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.List;
@Component
@WebService(serviceName = "UserServiceWS")
public class UserServiceEndpoint  extends SpringBeanAutowiringSupport {
    @Resource(name = "userServiceImpl")
    UserService userService;  //自动装配UserService

    @WebMethod
    public User saveUser(User user) {
        return userService.saveUser(user); //委托给 UserService
    }

    @WebMethod
    public List<User> findAll() {
        return userService.listAllUser();
    }
}
