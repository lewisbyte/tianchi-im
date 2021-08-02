package tianchi.im.starter.schedule;

import io.vertx.core.Vertx;
import tianchi.im.starter.dao.AsyncBatchInsertDao;

public class ScheduleTaskConf {
    public static void conf(Vertx vertx) {
        // 等待80ms ，用于积压一些数据量，产生批量提交的效果
        vertx.setPeriodic(80, (l) -> {
            AsyncBatchInsertDao.handleMessage();
        });
    }
}
