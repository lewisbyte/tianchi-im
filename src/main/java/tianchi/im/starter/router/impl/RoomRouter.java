package tianchi.im.starter.router.impl;

import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.sqlclient.Row;
import io.vertx.sqlclient.Tuple;
import tianchi.im.starter.cache.CacheRoom;
import tianchi.im.starter.constants.HttpHeaderConstant;
import tianchi.im.starter.dao.PGSQLUtils;
import tianchi.im.starter.exception.ControllerException;
import tianchi.im.starter.router.RouterConf;
import tianchi.im.starter.utils.CollectionUtils;
import tianchi.im.starter.utils.SessionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: tianchi-im-vert.x
 * @description: 房间路由
 * @author: lewis
 * @create: 2021-07-17 16:44
 */
public class RoomRouter implements RouterConf {
    @Override
    public void configRouter(Router router) {
        room(router);
        roomList(router);
        roomLeave(router);
        roomRoomid(router);
        enter(router);
        users(router);
    }

    //    @ApiOperation(value = "room")
    public void room(Router router) {
        router.post("/room").handler(ctx -> {
            HttpServerResponse response = ctx.response();
            HttpServerRequest request = ctx.request();
            String token = SessionUtils.getToken(request);
            JsonObject body = ctx.getBodyAsJson();
            String name = body.getString("name");
            PGSQLUtils.getConnection().compose(sqlConnection ->
                    sqlConnection.preparedQuery("INSERT INTO t_room (name) VALUES ($1)")
                            .execute(Tuple.of(name)).
                            onComplete(ar -> sqlConnection.close())
            )
                    .onSuccess(event -> {
                        response.end();
                    })
                    .onFailure(event -> {
                        response.setStatusCode(400).end(event.getCause().toString());
                    });
        });
    }

    //    @ApiOperation(value = "/room/{roomid}")
    public void roomRoomid(Router router) {
        router.get("/room/:roomid").handler(ctx -> {
            HttpServerResponse response = ctx.response();
            HttpServerRequest request = ctx.request();
            response.putHeader(HttpHeaderConstant.content_type, HttpHeaderConstant.text_plain);
            String roomid = request.getParam("roomid");
            CacheRoom.Room room = CacheRoom.get(roomid);

            if (room != null && room.isValid()) {
                response.end(room.getName());
                return;
            }
            PGSQLUtils.getConnection().compose(sqlConnection ->
                    sqlConnection.preparedQuery("select name from t_room where id=$1").
                            execute(Tuple.of(Long.valueOf(roomid))).
                            onComplete(ar -> sqlConnection.close())
            ).
                    onSuccess(rows -> {
                        for (Row row : rows) {
                            String name = row.getString("name");
                            CacheRoom.add(roomid, CacheRoom.Room.builder().name(name).valid(true).build());
                            response.end(name);
                        }
                    })
                    .onFailure(event -> {
                        CacheRoom.add(roomid, CacheRoom.Room.builder().valid(false).build());
                        response.setStatusCode(400).end();
                    });
        });
    }

    //    @ApiOperation(value = "/roomLeave")
    public void roomLeave(Router router) {
        router.put("/roomLeave").handler(ctx -> {
            HttpServerResponse response = ctx.response();
            HttpServerRequest request = ctx.request();
            String token = SessionUtils.getToken(request);
            SessionUtils.leaveRoom(token);
            response.putHeader(HttpHeaderConstant.content_type, HttpHeaderConstant.text_plain);
            response.end();

        });
    }

    //    @ApiOperation(value = "/room/{roomid}/enter")
    public void enter(Router router) {
        router.put("/room/:roomid/enter").handler(ctx -> {
            HttpServerResponse response = ctx.response();
            HttpServerRequest request = ctx.request();
            String token = SessionUtils.getToken(request);
            String roomid = request.getParam("roomid");
            response.putHeader(HttpHeaderConstant.content_type, HttpHeaderConstant.text_plain);

            CacheRoom.Room room = CacheRoom.get(roomid);

            if (room != null && room.isValid()) {
                SessionUtils.leaveRoom(token);
                SessionUtils.entryRoom(token, roomid);
                response.end();
                return;
            } else {
                // 检查 房间id 是否合法
                PGSQLUtils.getConnection().compose(sqlConnection ->
                        sqlConnection.preparedQuery("select name from t_room where id=$1").
                                execute(Tuple.of(Long.valueOf(roomid))).
                                onComplete(ar -> sqlConnection.close())
                ).
                        onSuccess(rows -> {
                            SessionUtils.leaveRoom(token);
                            SessionUtils.entryRoom(token, roomid);
                            // 缓存房间信息
                            for (Row row : rows) {
                                CacheRoom.add(roomid, CacheRoom.Room.builder().
                                        name(row.getString("name")).valid(true).build());
                            }

                            response.end();
                        })
                        .onFailure(event -> {
                            response.setStatusCode(400).end();
                        });
            }
        });
    }

    //    @ApiOperation(value = "/room/{roomid}/users")
    public void users(Router router) {
        router.get("/room/:roomid/users").handler(ctx -> {
            HttpServerResponse response = ctx.response();
            HttpServerRequest request = ctx.request();
            String roomid = request.getParam("roomid");
            List<String> users = SessionUtils.getRoomUsers(roomid);
            List<String> list = CollectionUtils.isEmpty(users) ? new ArrayList<>() : users;
            response.putHeader(HttpHeaderConstant.content_type, HttpHeaderConstant.application_json);
            response.end(Json.encode(list));
        });
    }

    //    @ApiOperation(value = "/roomList")
    public void roomList(Router router) {
        router.post("/roomList").handler(ctx -> {
            HttpServerResponse response = ctx.response();
            HttpServerRequest request = ctx.request();
            response.putHeader(HttpHeaderConstant.content_type, HttpHeaderConstant.application_json);
            JsonObject body = ctx.getBodyAsJson();
            Integer pageIndex = body.getInteger("pageIndex");
            Integer pageSize = body.getInteger("pageSize");
            if (pageIndex < 0 || pageSize < 0) {
                ControllerException.InvalidExceptionAccess.error(new RuntimeException("分页查询错误，页码小于0"));
            }

            PGSQLUtils.getConnection().compose(sqlConnection ->
                    sqlConnection.preparedQuery("select name,id from t_room limit $1 offset $2").
                            execute(Tuple.of(pageSize, pageSize * pageIndex)).
                            onComplete(ar -> sqlConnection.close())
            )
                    .onSuccess(rows -> {
                        response.putHeader(HttpHeaderConstant.content_type, HttpHeaderConstant.application_json);
                        JsonArray jsonArray = new JsonArray();
                        for (Row row : rows) {
                            JsonObject obj = new JsonObject();
                            obj.put("name", row.getString("name"));
                            obj.put("id", row.getLong("id").toString());
                            jsonArray.add(obj);
                        }
                        response.end(jsonArray.toString());
                    })
                    .onFailure(event -> {
                        response.setStatusCode(400);
                    });
        });
    }
}