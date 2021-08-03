package tianchi.im.starter.schedule;

import io.vertx.core.Vertx;
import tianchi.im.starter.dao.AsyncBatchInsertDao;

public class ScheduleTaskConf {

    public static final int DURATION = 80;

    public static void conf(Vertx vertx) {
        vertx.setPeriodic(DURATION, (l) -> AsyncBatchInsertDao.handleMessage());
        vertx.setPeriodic(DURATION-30, (l) -> AsyncBatchInsertDao.handleRoom());
    }
}
