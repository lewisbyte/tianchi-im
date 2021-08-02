package tianchi.im.starter.dao;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * 异步批量插入
 */
public class AsyncBatchInsertDao {

    private static volatile int count = 0;

    private static ArrayBlockingQueue<String> arrayBlockingQueue = new ArrayBlockingQueue(1 << 21);

    public static final String T_MESSAGE_PREFIX = "INSERT INTO t_message (text,roomid,stamp,mid) VALUES ";

    public static void submitMessage(String s) {
        try {
            arrayBlockingQueue.put(s);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void handleMessage() {
        List<String> stringList = new ArrayList<>();
        arrayBlockingQueue.drainTo(stringList);
        int size = stringList.size();
        if (size == 0) {
            return;
        }
        StringBuffer stringBuffer = new StringBuffer(T_MESSAGE_PREFIX);
        for (int i = 0; i < size; i++) {
            String s = stringList.get(i);
            stringBuffer.append(s).append(i == size - 1 ? ';' : ',');
        }
        PGSQLUtils.getConnection().compose(sqlConnection ->
                sqlConnection.preparedQuery(stringBuffer.toString()).
                        execute().
                        onComplete(ar -> sqlConnection.close())
        ).onSuccess(event -> {
            count++;
        }).onFailure(event -> {
            count++;
        });
    }
}
