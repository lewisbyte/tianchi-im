package starter.utils;

import java.util.Collection;

/**
 * @program: tianchi-im-vert.x
 * @description:
 * @author: lewis
 * @create: 2021-07-17 17:29
 */
public class CollectionUtils {
    public static boolean isEmpty(Collection<?> collection) {
        return collection == null || collection.isEmpty();
    }
}