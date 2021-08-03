package tianchi.im.starter.dao;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * 异步批量插入
 */
public class AsyncBatchInsertDao {

    // 保证异步线程内持有一个static field 的 gcRoot 引用，确保线程任务不会中断
    private static volatile int gcRoot = 0;

    private static final ArrayBlockingQueue<String> messageArrayBlockingQueue = new ArrayBlockingQueue<>(1 << 18);
    private static final ArrayBlockingQueue<String> roomArrayBlockingQueue = new ArrayBlockingQueue<>(1 << 18);
    private static final ArrayBlockingQueue<String> userArrayBlockingQueue = new ArrayBlockingQueue<>(1 << 18);

    public static final String T_MESSAGE_PREFIX = "INSERT INTO t_message (text,roomid,stamp,mid) VALUES ";
    public static final String T_ROOM_PREFIX = "INSERT INTO t_room (id,name) VALUES ";
    public static final String T_USER_PREFIX = "INSERT INTO t_user (username, first_name,last_name,email,password,phone) VALUES ";

    public static void submitMessage(String s) {
        submitToQueue(messageArrayBlockingQueue, s);
    }

    public static void submitRoom(String s) {
        submitToQueue(roomArrayBlockingQueue, s);
    }

    public static void submitUser(String s) {
        submitToQueue(userArrayBlockingQueue, s);
    }

    private static void submitToQueue(ArrayBlockingQueue<String> queue, String s) {
        try {
            queue.put(s);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 消息处理器
     */
    public static void handleMessage() {
        handle(messageArrayBlockingQueue, T_MESSAGE_PREFIX);
    }

    /**
     * 房间处理器
     */
    public static void handleRoom() {
        handle(roomArrayBlockingQueue, T_ROOM_PREFIX);
    }

    /**
     * 用户处理器
     */
    public static void handleUser() {
        handle(userArrayBlockingQueue, T_USER_PREFIX);
    }

    /**
     * 数据处理模板
     *
     * @param queue
     * @param dml
     */
    public static void handle(ArrayBlockingQueue<String> queue, String dml) {
        List<String> stringList = new ArrayList<>();
        queue.drainTo(stringList);
        int size = stringList.size();
        if (size == 0) {
            return;
        }
        StringBuffer stringBuffer = new StringBuffer(1 << 16);
        stringBuffer.append(dml);
        for (int i = 0; i < size; i++) {
            String s = stringList.get(i);
            stringBuffer.append(s).append(i == size - 1 ? ';' : ',');
        }
        PGSQLUtils.getConnection().compose(sqlConnection ->
                        sqlConnection.preparedQuery(stringBuffer.toString()).execute().
                                onComplete(ar -> sqlConnection.close())).
                onSuccess(event -> gcRoot++).onFailure(event -> gcRoot++);
    }
}
