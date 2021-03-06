package tianchi.im.starter.dao;

import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.pgclient.PgConnectOptions;
import io.vertx.pgclient.PgPool;
import io.vertx.sqlclient.PoolOptions;
import io.vertx.sqlclient.SqlConnection;

/**
 * @program: tianchi-im-vert.x
 * @description: 数据库配置类
 * @author: lewis
 * @create: 2021-07-17 17:22
 */
public class PGSQLUtils {

    private static final PgConnectOptions connectOptions = new PgConnectOptions()
            .setPort(5432)
            .setHost("127.0.0.1")
            .setDatabase("postgres")
            .setUser("postgres")
            .setPassword("postgres");

    // Pool options
    private static final PoolOptions poolOptions = new PoolOptions().setMaxSize(84);

    // 保存池对象
    private static PgPool pool;


    // 获取池
    public static PgPool getPool() {
        return pool;
    }

    // 获取 sql 链接
    public static Future<SqlConnection> getConnection() {
        return pool.getConnection();
    }


    public static void configAndCreatePool(Vertx vertx) {
        pool = PgPool.pool(vertx, connectOptions, poolOptions);
    }
}