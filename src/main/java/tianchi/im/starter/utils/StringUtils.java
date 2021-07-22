package tianchi.im.starter.utils;

/**
 * @program: tianchi-im-vert.x
 * @description:
 * @author: lewis
 * @create: 2021-07-17 17:27
 */
public class StringUtils {

    public static boolean isEmpty(Object str) {
        return str == null || "".equals(str);
    }
}