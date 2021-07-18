package starter.router.impl;

import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import starter.constants.HttpHeaderConstant;
import starter.dao.PGSQLUtils;
import starter.exception.ControllerException;
import starter.router.RouterConf;
import starter.utils.SessionUtils;
import starter.utils.StringUtils;

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

                // 用户未登录
                if (StringUtils.isEmpty(token) || !SessionUtils.verifyLoginStatus(token)) {
                    ControllerException.InvalidExceptionAccess.error(new RuntimeException("auth token 非法"));
                }

                String roomid = SessionUtils.getRoomInfoByToken(token);
                if (StringUtils.isEmpty(roomid)) {
                    ControllerException.InvalidExceptionAccess.error(new RuntimeException("用户没有进入房间"));
                }

                JsonObject body = ctx.getBodyAsJson();
                String id = body.getString("id");
                String text = body.getString("text");

                PGSQLUtils.getConnection().compose(sqlConnection ->
                        sqlConnection.preparedQuery("").execute().onComplete(ar->sqlConnection.close())
                );

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
                String roomid = SessionUtils.getRoomInfoByToken(token);

                JsonObject body = ctx.getBodyAsJson();

                body.getInteger("pageIndex");
                body.getInteger("pageSize");
                PGSQLUtils.getConnection().compose(sqlConnection ->
                        sqlConnection.preparedQuery("").execute().onComplete(ar->sqlConnection.close())
                );

                response.putHeader(HttpHeaderConstant.content_type, HttpHeaderConstant.application_json);
                response.end();
            } catch (Exception e) {
                ctx.fail(400);
            }
        });
    }
}