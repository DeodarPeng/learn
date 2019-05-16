package cc.learn.rest;/*
 @author Cedar
 @DESCRIPTION 
 @create 2019/3/28
*/

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Component
public class MyRestTemplate {
    @Autowired
    private RestTemplate restTemplate;

    public void useExchange(){
        MultiValueMap<String,String> headers= new LinkedMultiValueMap<>();
        headers.add("Accept","application/json");
        HttpEntity<Object> requestEntity = new HttpEntity<>(headers);
        ResponseEntity<String> response = restTemplate.exchange("url", HttpMethod.GET, requestEntity, String.class);
        String body = response.getBody();
    }
}
