package tianchi.im.starter;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.http.HttpServer;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.BodyHandler;
import tianchi.im.starter.dao.PGSQLUtils;
import tianchi.im.starter.router.impl.MessageRouter;
import tianchi.im.starter.router.impl.RoomRouter;
import tianchi.im.starter.router.impl.UserRouter;

public class MainVerticle extends AbstractVerticle {

    @Override
    public void start(Promise<Void> startPromise) throws Exception {
        HttpServer server = vertx.createHttpServer();
        Router router = Router.router(vertx);
        // 处理post 请求参数
        router.route().handler(BodyHandler.create());

        // 创建数据库连接池
        PGSQLUtils.configAndCreatePool(vertx);

        router.errorHandler(500, ctx -> {
            ctx.response().setStatusCode(400).end(ctx.failure().getMessage());
        });
        router.route().failureHandler(route -> {
            String error = route.failure().getMessage();
            route.response().setStatusCode(400).end(error);
        });
        configRouter(router);

        server.requestHandler(router).listen(8080);

        for (int i = 0; i < 100; i++) {
            PGSQLUtils.getConnection().compose(sqlConnection -> sqlConnection.preparedQuery("select 1").
                    execute().
                    onComplete(ar -> sqlConnection.close()))
                    .onSuccess(event -> {
                        System.out.println("初始化链接数据库成功");
                    }).onFailure(event -> {
                System.out.println("初始化链接数据库失败" + event.getLocalizedMessage());
            });
        }

        System.out.println("server start ......");
    }

    // 配置路由
    void configRouter(Router router) {
        new MessageRouter().configRouter(router);
        new RoomRouter().configRouter(router);
        new UserRouter().configRouter(router);

        router.get("/baseline").handler(ctx -> {
            ctx.response().end("baseline");
        });
    }
}
