package tianchi.im.starter.schedule;

import io.vertx.core.Vertx;
import tianchi.im.starter.dao.AsyncBatchInsertDao;

public class ScheduleTaskConf {
    public static void conf(Vertx vertx) {
        vertx.setPeriodic(2, (l) -> {
            AsyncBatchInsertDao.handleMessage();
        });
    }
}
