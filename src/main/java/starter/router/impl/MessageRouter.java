package starter.router.impl;

import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.sqlclient.Row;
import io.vertx.sqlclient.Tuple;
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
                    sqlConnection.preparedQuery("INSERT INTO t_message (text,roomid,stamp,mid) VALUES ($1,$2,$3,$4)").
                            execute(Tuple.of(text, roomid, System.currentTimeMillis(), id)).
                            onComplete(ar -> sqlConnection.close())
            );

            response.putHeader(HttpHeaderConstant.content_type, HttpHeaderConstant.text_plain);
            response.end();
        });
    }

    /**
     * 消息拉取
     *
     * @param router
     */
    private void messageRetrieve(Router router) {
        router.post("/message/retrieve").handler(ctx -> {
            HttpServerResponse response = ctx.response();
            HttpServerRequest request = ctx.request();
            String token = SessionUtils.getToken(request);
            String roomid = SessionUtils.getRoomInfoByToken(token);

            JsonObject body = ctx.getBodyAsJson();

            Integer pageIndex = body.getInteger("pageIndex");
            Integer pageSize = body.getInteger("pageSize");
            PGSQLUtils.getConnection().compose(sqlConnection ->
                    sqlConnection.preparedQuery("select mid,stamp,text from t_message where roomid=$1 limit $2 offset $3 order by id desc").execute(
                            Tuple.of(roomid, pageSize, Math.abs(pageIndex + 1) * pageSize)
                    ).onComplete(ar -> sqlConnection.close())
            )
                    .onFailure(throwable -> {
                        response.setStatusCode(400).end(throwable.getMessage());
                    })
                    .onSuccess(rows -> {
                        response.putHeader(HttpHeaderConstant.content_type, HttpHeaderConstant.application_json);
                        JsonArray jsonArray = new JsonArray();
                        for (Row row : rows) {
                            JsonObject obj = new JsonObject();
                            obj.put("id", row.getString("mid"));
                            obj.put("text", row.getString("text"));
                            obj.put("timestamp", row.getLong("stamp").toString());
                            jsonArray.add(obj);
                        }
                        response.end(jsonArray.toString());
                    });
        });
    }
}