package starter.router.impl;

import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import starter.constants.HttpHeaderConstant;
import starter.router.RouterConf;
import starter.utils.SessionUtils;

/**
 * @program: tianchi-im-vert.x
 * @description: 消息路由
 * @author: lewis
 * @create: 2021-07-17 16:43
 */
public class MessageRouter implements RouterConf {

    @Override
    public void configRouter(Router router) {
        messageSend(router);
        messageRetrieve(router);
    }

    /**
     * 消息发送
     *
     * @param router
     */
    private void messageSend(Router router) {
        router.post("/message/send").handler(ctx -> {
            try {
                HttpServerResponse response = ctx.response();
                HttpServerRequest request = ctx.request();
                String token = SessionUtils.getToken(request);

                JsonObject body = ctx.getBodyAsJson();
                body.getString("id");
                body.getString("text");

                response.putHeader(HttpHeaderConstant.content_type, HttpHeaderConstant.text_plain);
                response.end();
            } catch (Exception e) {
                ctx.fail(400);
            }
        });
    }

    /**
     * 消息拉取
     *
     * @param router
     */
    private void messageRetrieve(Router router) {
        router.post("/message/retrieve").handler(ctx -> {
            try {
                HttpServerResponse response = ctx.response();
                HttpServerRequest request = ctx.request();
                String token = SessionUtils.getToken(request);
                JsonObject body = ctx.getBodyAsJson();

                body.getInteger("pageIndex");
                body.getInteger("pageSize");

                response.putHeader(HttpHeaderConstant.content_type, HttpHeaderConstant.application_json);
                response.end();
            } catch (Exception e) {
                ctx.fail(400);
            }
        });
    }
}