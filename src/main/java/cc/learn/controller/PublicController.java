package cc.learn.controller;

import cc.learn.jms.AlertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class PublicController {

    @Autowired
    private AlertService alertService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {
        return "index";
    }

    @RequestMapping("/sendJmsMessage")
    public void sendJmsMessage(String message) {
        alertService.sendMessageAlert(message);
    }
}
