package tianchi.lewis.indi.im.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @program: tianchi-im
 * @description:
 * @author: lewis
 * @create: 2021-07-08 00:38
 */
public class ControllerException {

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public static class InvalidExceptionAccess extends RuntimeException{
        public static void error(){
            throw new InvalidExceptionAccess();
        }
    }
}