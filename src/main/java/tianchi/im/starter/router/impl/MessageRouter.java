package tianchi.im.starter.router.impl;

import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.sqlclient.Row;
import io.vertx.sqlclient.Tuple;
import tianchi.im.starter.constants.HttpHeaderConstant;
import tianchi.im.starter.dao.AsyncBatchInsertDao;
import tianchi.im.starter.dao.PGSQLUtils;
import tianchi.im.starter.router.RouterConf;
import tianchi.im.starter.utils.SessionUtils;
import tianchi.im.starter.utils.StringUtils;

/**
 * @program: tianchi-im-vert.x
 * @description: 消息路由
 * @author: lewis
 * @create: 2021-07-17 16:43
 */
public class MessageRouter implements RouterConf {


    AsyncBatchInsertDao asyncBatchInsertDao = new AsyncBatchInsertDao();

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
            String roomid = SessionUtils.getRoomInfoByToken(token);

            if (StringUtils.isEmpty(token) || !SessionUtils.verifyLoginStatus(token)) {
                response.setStatusCode(400).end();
                return;
            }

            if (StringUtils.isEmpty(roomid)) {
                response.setStatusCode(400).end();
                return;
            }

            JsonObject body = ctx.getBodyAsJson();
            String id = body.getString("id");
            String text = body.getString("text");
            response.putHeader(HttpHeaderConstant.content_type, HttpHeaderConstant.text_plain);
            String s = String.format("INSERT INTO t_message_%s (text,roomid,stamp,mid) VALUES (%s,%s,%s,%s)", getSharding(roomid), text, Long.valueOf(roomid), System.currentTimeMillis(), id);
            asyncBatchInsertDao.submitMessage(s);
            response.end();
        });
    }

    private long getSharding(String roomid) {
        return Long.parseLong(roomid) % 32;
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


            if (StringUtils.isEmpty(token) || !SessionUtils.verifyLoginStatus(token)) {
                response.setStatusCode(400).end();
                return;
            }

            if (StringUtils.isEmpty(roomid)) {
                response.setStatusCode(400).end();
                return;
            }
            JsonObject body = ctx.getBodyAsJson();

            Integer pageIndex = body.getInteger("pageIndex");
            Integer pageSize = body.getInteger("pageSize");
            PGSQLUtils.getConnection().compose(sqlConnection ->
                    sqlConnection.preparedQuery("select mid,stamp,text from t_message_" + getSharding(roomid) + " where roomid=$1 order by id desc limit $2 offset $3").execute(
                            Tuple.of(Long.valueOf(roomid), pageSize, Math.abs(pageIndex + 1) * pageSize)
                    ).onComplete(ar -> sqlConnection.close())
            )
                    .onFailure(throwable -> {
                        response.setStatusCode(400).end();
                    })
                    .onSuccess(rows -> {
                        response.putHeader(HttpHeaderConstant.content_type, HttpHeaderConstant.application_json);
                        JsonArray jsonArray = new JsonArray();

                        if (rows.size() == 0) {
                            response.end(jsonArray.toString());
                            return;
                        }

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