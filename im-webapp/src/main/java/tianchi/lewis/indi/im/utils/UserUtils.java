package tianchi.lewis.indi.im.utils;

import org.springframework.util.StringUtils;
import tianchi.lewis.indi.im.constants.FieldConst;
import tianchi.lewis.indi.im.exception.ControllerException;

import javax.servlet.http.HttpServletRequest;

/**
 * @program: tianchi-im
 * @description:
 * @author: lewis
 * @create: 2021-07-03 00:20
 */
public class UserUtils {

    /**
     * 令牌格式
     *
     * Authorization: Bearer <token>
     * @param request
     */
    public static String getToken(HttpServletRequest request) {
        String auth = request.getHeader(FieldConst.TOKEN);
        if (StringUtils.isEmpty(auth)){
            ControllerException.InvalidExceptionAccess.error(new RuntimeException("Bearer Token 获取header令牌为空"));
        }


        String token = auth.replace("Bearer ", "");
        if (StringUtils.isEmpty(token)){
            ControllerException.InvalidExceptionAccess.error(new RuntimeException("Bearer token 解析header令牌为空"));
        }
        return token;
    }
}