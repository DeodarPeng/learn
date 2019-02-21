package cc.learn.controller;

import cc.learn.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import cc.learn.po.User;
import cc.learn.service.UserService;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

    @RequestMapping( value = "/saveUser", method = RequestMethod.POST)
    @ResponseBody
    public String saveUser(User user) {
        return userService.saveUser(user).toString();
    }

    @RequestMapping( value = "/updateUserById" , method = RequestMethod.POST)
    @ResponseBody
    public String updateUserById(User user) {
        return userService.updateUserById(user).toString();
    }

    @RequestMapping(value = "/getAllUser", method = RequestMethod.GET)
    public String getAllUser(Model model) {
        List<User> userList = userService.listAllUser();
        model.addAttribute("userList",userList);
        return "userList";
    }

    @RequestMapping(value = "/userInfo" , method = RequestMethod.GET)
    public String  userInfo(String userId, RedirectAttributes model){
        User user = userService.getUserById(userId);
        model.addAttribute("userid",user.getId());
        //将 user 对象添加到模型中，不设置 key 则会根据值的类型自动判断
        model.addFlashAttribute("user",user);
        return  "redirect:/user/{userid}";
    }

    /**
     * 使用 flash 属性中的对象
     * @param username
     * @param model
     * @return
     */
    @RequestMapping(value = "/showProfile" , method = RequestMethod.GET)
    public  String showProfile(@PathVariable String userid, Model model){
        if (!model.containsAttribute("user")){
            model.addAttribute(userService.getUserById(userid));
        }
        return "profile";
    }

    @ExceptionHandler({UserNotFoundException.class})
    @ResponseBody
    public String handleUserNotFound(){
        return  "user not found";
    }
}
