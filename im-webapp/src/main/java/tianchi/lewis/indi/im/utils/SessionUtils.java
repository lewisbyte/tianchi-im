package tianchi.lewis.indi.im.utils;

import cn.hutool.core.collection.ConcurrentHashSet;
import org.springframework.util.StringUtils;
import tianchi.lewis.indi.im.model.RoomUser;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * @program: tianchi-im
 * @description: 用户会话状态工具类
 * <p>
 * 本类用于存储用户的当前状态
 * @author: lewis
 * @create: 2021-07-06 00:44
 */
public class SessionUtils {

    // 存储用户当前的所处的房间
    // key: token
    // value: room id
    private static Map<String, String> roomSessionMap = new ConcurrentHashMap<>();

    // 存储房间的用户
    // key: roomid
    // value: username set list
    private static Map<String, Set<String>> roomUserInfoMap = new ConcurrentHashMap<>();

    // 存储用户信息
    //key: token
    //value: username
    private static Map<String, String> userInfo = new ConcurrentHashMap<>();


    /**
     * 进入房间
     *
     * @param token
     * @param roomid
     * @return
     */
    public static boolean entryRoom(String token, String roomid) {
        if (StringUtils.isEmpty(token) || StringUtils.isEmpty(roomid)) {
            return false;
        }
        // 1. 存储用户所在房间
        roomSessionMap.put(token, roomid);

        // 2. 存储房间的用户列表
        Set<String> set = roomUserInfoMap.getOrDefault(roomid, new ConcurrentHashSet<>());
        set.add(userInfo.get(token));
        roomUserInfoMap.put(roomid, set);
        return true;
    }

    /**
     * 获取用户房间信息
     *
     * @param token
     * @return room id
     */
    public static String getRoomInfoByToken(String token) {
        if (StringUtils.isEmpty(token)) {
            return null;
        }
        // 1. 获取用户房间信息
        return roomSessionMap.get(token);
    }

    /**
     * 离开房间
     *
     * @param token
     * @return room id
     */
    public static void leaveRoom(String token) {
        if (StringUtils.isEmpty(token)) {
            return;
        }
        // 从房间的用户列表中移除
        roomUserInfoMap.get(roomSessionMap.get(token)).remove(token);
        // 注销，移除用户信息
        roomSessionMap.remove(token);
    }

    /**
     * 验证用户登录状态，检查是否有进入登录
     *
     * @param token
     * @return
     */
    public static boolean verifyLoginStatus(String token) {
        return userInfo.containsKey(token);
    }

    /**
     * 登录，记录用户的相关信息
     *
     * @param token
     * @param username
     * @return
     */
    public static boolean login(String token, String username) {
        if (StringUtils.isEmpty(token) || StringUtils.isEmpty(username)) {
            return false;
        }
        userInfo.put(token, username);
        return true;
    }

    /**
     * 获取房间用户列表
     *
     * @param roomid
     * @return
     */
    public static List<RoomUser> getRoomUsers(String roomid) {
        return roomUserInfoMap.get(roomid).stream().map(RoomUser::new).collect(Collectors.toList());
    }

}