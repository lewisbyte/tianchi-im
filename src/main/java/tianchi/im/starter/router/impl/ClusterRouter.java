package tianchi.im.starter.router.impl;

import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import tianchi.im.starter.router.RouterConf;

/**
 * lewis
 * 集群路由
 */
public class ClusterRouter implements RouterConf {


    @Override
    public void configRouter(Router router) {
        updateCluster(router);
        checkCluster(router);
    }


    // 更新集群状态
    private void updateCluster(Router router) {
        router.post("/updateCluster").handler(ctx -> {
            JsonObject body = ctx.getBodyAsJson();

            // TODO
        });
    }

    // 检查集群状态
    private void checkCluster(Router router) {
        router.get("/checkCluster").handler(ctx -> {
            ctx.response().end();
        });
    }
}
