package starter.router;

import io.vertx.core.http.HttpServerResponse;
import io.vertx.ext.web.Router;
import starter.constants.HttpHeaderConstant;

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
            response.putHeader(HttpHeaderConstant.content_type, HttpHeaderConstant.text_plain);
        });
    }

    //    @ApiOperation(value = "/room/{roomid}")
    public void roomRoomid(Router router) {
        router.get("/room/:roomid").handler(ctx -> {
            HttpServerResponse response = ctx.response();
            response.putHeader(HttpHeaderConstant.content_type, HttpHeaderConstant.text_plain);
        });
    }

    //    @ApiOperation(value = "/roomLeave")
    public void roomLeave(Router router) {
        router.put("/roomLeave").handler(ctx -> {
            HttpServerResponse response = ctx.response();
            response.putHeader(HttpHeaderConstant.content_type, HttpHeaderConstant.text_plain);
        });
    }

    //    @ApiOperation(value = "/room/{roomid}/enter")
    public void enter(Router router) {
        router.put("/room/:roomid/enter").handler(ctx -> {
            HttpServerResponse response = ctx.response();
            response.putHeader(HttpHeaderConstant.content_type, HttpHeaderConstant.text_plain);
        });
    }

    //    @ApiOperation(value = "/room/{roomid}/users")
    public void users(Router router) {
        router.get("/room/:roomid/users").handler(ctx -> {
            HttpServerResponse response = ctx.response();
            response.putHeader(HttpHeaderConstant.content_type, HttpHeaderConstant.application_json);
        });
    }

    //    @ApiOperation(value = "/roomList")
    public void roomList(Router router) {
        router.post("/roomList").handler(ctx -> {
            HttpServerResponse response = ctx.response();
            response.putHeader(HttpHeaderConstant.content_type, HttpHeaderConstant.application_json);
        });
    }
}