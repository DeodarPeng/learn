package cc.learn.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cc.learn.po.User;
import cc.learn.service.UserService;

import java.util.Arrays;
import java.util.List;

/**
 * @Description:
 * @author: C
 * @date: 2018年11月14日 下午12:42:20
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/saveUser")
    @ResponseBody
    public String getUserById(User user) {
        return userService.saveUser(user).toString();
    }

    @RequestMapping("/updateUserById")
    @ResponseBody
    public String updateUserById(User user) {
        return userService.updateUserById(user).toString();
    }

    @RequestMapping("/getAllUser")
    @ResponseBody
    public String getAllUser() {
        List<User> users = userService.listAllUser();
        return Arrays.toString(users.toArray());
    }
}
