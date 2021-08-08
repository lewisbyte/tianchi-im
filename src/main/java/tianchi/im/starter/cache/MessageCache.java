package tianchi.im.starter.cache;


import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

import java.util.List;

/**
 * 消息缓存
 *
 * 消息分为2层：
 * 离线消息：数据库中存储的消息为离线消息
 * 在线消息：缓存中存储的消息为在线消息，在线消息保持一个固定大小的size，保持一个实时更新状态
 *
 * 接口请求API 接口，先尝试从在线消息队列里面拉取消息，如果能够拉取到消息，则直接返回消息
 * 否则在再数据库中查询消息
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
