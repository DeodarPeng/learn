package cc.learn.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class AppWideExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public String hasException(){
        return  "has exception";
    }

}
