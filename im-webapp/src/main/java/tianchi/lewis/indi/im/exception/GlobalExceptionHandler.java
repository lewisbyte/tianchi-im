package tianchi.lewis.indi.im.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @program: tianchi-im
 * @description:
 * @author: lewis
 * @create: 2021-07-12 23:24
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    @ResponseBody
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    String handleException(Exception e){
        return "Exception: " + e.getMessage();
    }
}