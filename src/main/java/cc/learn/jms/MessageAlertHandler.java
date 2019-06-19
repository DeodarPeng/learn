package cc.learn.jms;

import org.springframework.stereotype.Component;

/*
 @author Cedar
 @DESCRIPTION
 @create 2019/6/12
*/
@Component
public class MessageAlertHandler {
  public void handleMessageHandle(String message) {
    System.out.println("----------------收到消息：" + message + "--------------");
  }
}
