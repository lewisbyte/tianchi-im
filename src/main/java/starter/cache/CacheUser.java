package starter.cache;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import lombok.Builder;
import org.checkerframework.checker.nullness.qual.Nullable;

// 用户缓存类
public class CacheUser {

    private static Cache<String, User> userInfo = CacheBuilder.newBuilder()
            //设置并发级别为8，并发级别是指可以同时写缓存的线程数
            .concurrencyLevel(8)
            //设置缓存容器的初始容量为10
            .initialCapacity(1 << 10)
            //设置缓存最大容量为100，超过100之后就会按照LRU最近虽少使用算法来移除缓存项
            .maximumSize(1 << 20)
            .build();

    public static boolean exist(String username) {
        CacheUser.User info = userInfo.getIfPresent(username);
        return info != null && info.isValid();
    }

    public static void add(String username, User user) {
        userInfo.put(username, user);
    }

    public static User get(String username) {
        return userInfo.getIfPresent(username);
    }


    @Builder
    public static class User {

        private boolean valid = false;
        private String username;
        private String firstName;
        private String lastName;
        private String email;
        private String password;
        private String phone;

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public boolean isValid() {
            return valid;
        }
    }
}
