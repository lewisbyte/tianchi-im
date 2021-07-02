package tianchi.lewis.indi.im.utils;

import tianchi.lewis.indi.im.constants.FieldConst;

import javax.servlet.http.HttpServletRequest;

/**
 * @program: tianchi-im
 * @description:
 * @author: lewis
 * @create: 2021-07-03 00:20
 */
public class UserUtils {
    public static String getToken(HttpServletRequest request) {
        return request.getHeader(FieldConst.TOKEN);
    }
}