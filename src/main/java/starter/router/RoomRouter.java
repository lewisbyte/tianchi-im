package starter.router;

import io.vertx.ext.web.Router;

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
        router.post("/room").handler(ctx->{

        });
    }

    //    @ApiOperation(value = "/room/{roomid}")
    public void roomRoomid(Router router) {
        router.get("/room/:roomid").handler(ctx->{

        });
    }

    //    @ApiOperation(value = "/roomLeave")
    public void roomLeave(Router router) {
        router.put("/roomLeave").handler(ctx->{

        });
    }

    //    @ApiOperation(value = "/room/{roomid}/enter")
    public void enter(Router router) {
        router.put("/room/:roomid/enter").handler(ctx->{

        });
    }

    //    @ApiOperation(value = "/room/{roomid}/users")
    public void users(Router router) {
        router.get("/room/:roomid/users").handler(ctx->{

        });
    }

    //    @ApiOperation(value = "/roomList")
    public void roomList(Router router) {
        router.post("/roomList").handler(ctx->{

        });
    }
}