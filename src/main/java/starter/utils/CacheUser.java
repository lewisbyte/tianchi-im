package starter.utils;

import java.util.HashMap;
import java.util.Map;

public class CacheUser {

    // key username
    // value password
    public static final Map<String, String> userInfo = new HashMap<>();

    public static boolean exist(String username) {
        return userInfo.containsKey(username);
    }

    public static void add(String username, String password) {
        userInfo.put(username, password);
    }
    public static String get(String username) {
       return userInfo.get(username);
    }


}
