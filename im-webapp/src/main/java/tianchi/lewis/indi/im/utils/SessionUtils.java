package tianchi.lewis.indi.im.utils;

import cn.hutool.core.collection.ConcurrentHashSet;
import org.springframework.util.StringUtils;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @program: tianchi-im
 * @description: 用户会话状态工具类
 * @author: lewis
 * @create: 2021-07-06 00:44
 */
public class SessionUtils {

    // 存储用户当前的所处的房间
    private static Map<String, String> sessionMap = new ConcurrentHashMap<>();

    private static Set<String> loginStatus = new ConcurrentHashSet<>();

    /**
     * 进入房间
     * @param token
     * @param roomid
     * @return
     */
    public static boolean entryRoom(String token, String roomid) {
        if (StringUtils.isEmpty(token)||StringUtils.isEmpty(roomid)){
            return false;
        }
        sessionMap.put(token, roomid);
        return true;
    }

    /**
     *
     * @param token
     * @return
     */
    public static String getRoomInfoByToken(String token) {
        if (StringUtils.isEmpty(token)){
            return null;
        }
        return sessionMap.get(token);
    }


    public static boolean verifyLoginStatus(String token) {
        return loginStatus.contains(token);
    }

    public static boolean login(String token) {
        if (StringUtils.isEmpty(token)) {
            return false;
        }
        loginStatus.add(token);
        return true;
    }
}