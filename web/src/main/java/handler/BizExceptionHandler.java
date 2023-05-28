package handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @Author: suragi
 * @Date: 2023/5/27 20:14
 * @Description:
 */
@Slf4j
@RestControllerAdvice
public class BizExceptionHandler {

    @ExceptionHandler({RuntimeException.class})
    public void defaultHandler(Exception exception){
        exception.printStackTrace();
        log.error(exception.getMessage());
    }
}

