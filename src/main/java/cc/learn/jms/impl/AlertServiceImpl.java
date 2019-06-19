package cc.learn.jms.impl;/*
 @author Cedar
 @DESCRIPTION 
 @create 2019/6/11
*/

import cc.learn.jms.AlertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.jms.support.JmsUtils;
import org.springframework.stereotype.Service;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.Session;

@Service
public class AlertServiceImpl implements AlertService {
    @Autowired
    private JmsTemplate jmsTemplate;
    @Override
    public void sendMessageAlert(String message) {
        jmsTemplate.send("learn.alert.queue", new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                return session.createObjectMessage(message);
            }
        });
    }

    public void sendMessageAlert1(String message) {
        jmsTemplate.convertAndSend(message);
    }


    public Object receiveMessageAlert(){
        try {
            ObjectMessage receive = (ObjectMessage)jmsTemplate.receive();
            return   receive.getObject();
        } catch (JMSException e) {
            //抛出转换后的异常
            throw JmsUtils.convertJmsAccessException(e);
        }
    }
    public Object receiveMessageAlert1(){
        return jmsTemplate.receiveAndConvert();
    }

}
