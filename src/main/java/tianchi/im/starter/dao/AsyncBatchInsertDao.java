package tianchi.im.starter.dao;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * 异步批量插入
 */
public class AsyncBatchInsertDao {

    private static volatile int gcRoot = 0;

    private static ArrayBlockingQueue<String> messageArrayBlockingQueue = new ArrayBlockingQueue(1 << 18);
    private static ArrayBlockingQueue<String> roomArrayBlockingQueue = new ArrayBlockingQueue(1 << 18);

    public static final String T_MESSAGE_PREFIX = "INSERT INTO t_message (text,roomid,stamp,mid) VALUES ";
    public static final String T_ROOM_PREFIX = "INSERT INTO t_room (id,name) VALUES ";

    public static void submitMessage(String s) {
        try {
            messageArrayBlockingQueue.put(s);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void submitRoom(String s) {
        try {
            roomArrayBlockingQueue.put(s);
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
        StringBuffer stringBuffer = new StringBuffer(dml);
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
