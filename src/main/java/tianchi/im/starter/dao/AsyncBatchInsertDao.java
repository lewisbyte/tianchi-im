package tianchi.im.starter.dao;


import io.vertx.sqlclient.Tuple;

/**
 * 异步批量插入
 */
public class AsyncBatchInsertDao {

    // 用于存储批量环境
    private StringBuffer stringBuffer = new StringBuffer("INSERT INTO t_message (text,roomid,stamp,mid) VALUES ");

    // 时钟周期大小
    // 预防出现多次轮训发现没有触发
    private volatile int tick = 0;


    //65536 批量语句长度大小的阈值
    private static final int size = 1 << 16;

    public void submitMessage(String s) {
        stringBuffer.append(s);
        executeTask();
    }


    public void executeTask() {
        if (tick++ > 1000) {
            insertDb();
        } else if (stringBuffer.length() > size) {
            insertDb();
        }
    }

    private void insertDb() {
        PGSQLUtils.getConnection().compose(sqlConnection ->
                sqlConnection.preparedQuery(stringBuffer.toString()).
                        execute().
                        onComplete(ar -> sqlConnection.close())
        ).onSuccess(event -> {
            System.out.println("插入成功");
        }).onFailure(event -> {
            System.out.println("插入失败" + event);
        });
        tick = 0;
        stringBuffer = new StringBuffer("INSERT INTO t_message (text,roomid,stamp,mid) VALUES");
    }
}
