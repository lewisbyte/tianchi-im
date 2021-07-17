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
            try {
                HttpServerResponse response = ctx.response();
                JsonObject body = ctx.getBodyAsJson();
                String name = body.getString("name");

                response.putHeader(HttpHeaderConstant.content_type, HttpHeaderConstant.text_plain);
            } catch (Exception e) {
                ctx.fail(400);
            }
        });
    }

    //    @ApiOperation(value = "/room/{roomid}")
    public void roomRoomid(Router router) {
        router.get("/room/:roomid").handler(ctx -> {
            try {
                HttpServerResponse response = ctx.response();
                HttpServerRequest request = ctx.request();
                String roomid = request.getParam("roomid");
                response.putHeader(HttpHeaderConstant.content_type, HttpHeaderConstant.text_plain);
            } catch (Exception e) {
                ctx.fail(400);
            }
        });
    }

    //    @ApiOperation(value = "/roomLeave")
    public void roomLeave(Router router) {
        router.put("/roomLeave").handler(ctx -> {
            try {
                HttpServerResponse response = ctx.response();
                HttpServerRequest request = ctx.request();
                String token = SessionUtils.getToken(request);

                response.putHeader(HttpHeaderConstant.content_type, HttpHeaderConstant.text_plain);
            } catch (Exception e) {
                ctx.fail(400);
            }
        });
    }

    //    @ApiOperation(value = "/room/{roomid}/enter")
    public void enter(Router router) {
        router.put("/room/:roomid/enter").handler(ctx -> {
            try {
                HttpServerResponse response = ctx.response();
                HttpServerRequest request = ctx.request();
                String token = SessionUtils.getToken(request);
                String roomid = request.getParam("roomid");

                response.putHeader(HttpHeaderConstant.content_type, HttpHeaderConstant.text_plain);
            } catch (Exception e) {
                ctx.fail(400);
            }
        });
    }

    //    @ApiOperation(value = "/room/{roomid}/users")
    public void users(Router router) {
        router.get("/room/:roomid/users").handler(ctx -> {
            try {
                HttpServerResponse response = ctx.response();
                HttpServerRequest request = ctx.request();
                String roomid = request.getParam("roomid");

                response.putHeader(HttpHeaderConstant.content_type, HttpHeaderConstant.application_json);
            } catch (Exception e) {
                ctx.fail(400);
            }
        });
    }

    //    @ApiOperation(value = "/roomList")
    public void roomList(Router router) {
        router.post("/roomList").handler(ctx -> {
            try {
                HttpServerResponse response = ctx.response();
                HttpServerRequest request = ctx.request();
                JsonObject body = ctx.getBodyAsJson();
                body.getInteger("pageIndex");
                body.getInteger("pageSize");
                response.putHeader(HttpHeaderConstant.content_type, HttpHeaderConstant.application_json);
            } catch (Exception e) {
                ctx.fail(400);
            }
        });
    }
}