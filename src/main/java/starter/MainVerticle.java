package starter;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.http.HttpServer;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.BodyHandler;
import starter.dao.PGSQLUtils;
import starter.router.impl.MessageRouter;
import starter.router.impl.RoomRouter;
import starter.router.impl.UserRouter;

public class MainVerticle extends AbstractVerticle {

    @Override
    public void start(Promise<Void> startPromise) throws Exception {
        HttpServer server = vertx.createHttpServer();
        Router router = Router.router(vertx);
        // 处理post 请求参数
        router.route().handler(BodyHandler.create());

        // 创建数据库连接池
        PGSQLUtils.configAndCreatePool(vertx);

        // 业务配置
        configRouter(router);

        server.requestHandler(router).listen(8080);

        System.out.println("server start ......");
    }

    // 配置路由
    void configRouter(Router router) {
        new MessageRouter().configRouter(router);
        new RoomRouter().configRouter(router);
        new UserRouter().configRouter(router);
    }
}
