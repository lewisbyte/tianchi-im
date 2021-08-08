package tianchi.im.starter.cache;


import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

import java.util.List;

/**
 * 消息缓存
 */
public class MessageCache {


    // key: room id
    // value: room messages
    private static Cache<String, List<String>> messageCache = CacheBuilder.newBuilder()
            .concurrencyLevel(32)
            .initialCapacity(1 << 10)
            .maximumSize(1 << 20)
            .build();

}
