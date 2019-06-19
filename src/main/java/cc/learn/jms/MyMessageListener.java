package cc.learn.jms;/*
 @author Cedar
 @DESCRIPTION 
 @create 2019/6/12
*/

import org.springframework.jms.listener.SessionAwareMessageListener;
import org.springframework.stereotype.Component;

import javax.jms.*;

@Component
public class MyMessageListener implements javax.jms.MessageListener {

    @Override
    public void onMessage(Message message) {
        TextMessage text=(TextMessage) message;
        System.out.println("接收到的消息是一个文本消息:"+ text);
    }
    @Component
    class  MySessionAwareMessageListener implements SessionAwareMessageListener {

        @Override
        public void onMessage(Message message, Session session) throws JMSException {
            TextMessage receiveTextMessage = (TextMessage)message;
            System.out.println("接收到的消息是一个文本消息:"+ receiveTextMessage.getText());
            //通过session 创建  producer对象，再回送信息
            //从message中取出信息回送的目的地,以便创建生产者.
            MessageProducer producer=session.createProducer( message.getJMSReplyTo()   );
            //创建一条消息
            Message textMessage =session.createTextMessage(  "生产者发过来的信息已经处理完毕，game over..."   );
            //调用发送
            producer.send(textMessage);
        }
    }
}
