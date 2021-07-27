package tianchi.im.starter.cache;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import lombok.Builder;

public class CacheRoom {

    private static Cache<String, Room> roomInfo = CacheBuilder.newBuilder()
            //设置并发级别为8，并发级别是指可以同时写缓存的线程数
            .concurrencyLevel(32)
            //设置缓存容器的初始容量为10
            .initialCapacity(1 << 20)
            //设置缓存最大容量为100，超过100之后就会按照LRU最近虽少使用算法来移除缓存项
            .maximumSize(1 << 20)
            .build();

    public static void add(String id, Room room) {
        roomInfo.put(id, room);
    }

    public static Room get(String id) {
        return roomInfo.getIfPresent(id);
    }

    @Builder
    public static class Room{
        private String name;
        private boolean valid = false;

        public String getName() {
            return name;
        }

        public boolean isValid() {
            return valid;
        }
    }
}
