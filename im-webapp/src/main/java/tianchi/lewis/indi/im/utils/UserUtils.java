package tianchi.lewis.indi.im.utils;

import org.springframework.util.StringUtils;
import tianchi.lewis.indi.im.constants.FieldConst;

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
            return null;
        }
        return auth.replace("Bearer ","");
    }
}