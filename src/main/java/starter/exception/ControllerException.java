package starter.exception;

/**
 * @program: tianchi-im
 * @description:
 * @author: lewis
 * @create: 2021-07-08 00:38
 */
public class ControllerException {

    public static class InvalidExceptionAccess extends RuntimeException{
        public InvalidExceptionAccess(Exception e){
            super(e);
        }


        public static void error(Exception e){
            throw new InvalidExceptionAccess(e);
        }
    }
}