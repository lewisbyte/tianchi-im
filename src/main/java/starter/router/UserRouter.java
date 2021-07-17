package starter.router;

import io.vertx.ext.web.Router;

/**
 * @program: tianchi-im-vert.x
 * @description: 用户路由
 * @author: lewis
 * @create: 2021-07-17 16:44
 */
public class UserRouter implements RouterConf {

    @Override
    public void configRouter(Router router) {
        user(router);
        userLogin(router);
        username(router);
    }

    //    @ApiOperation(value = "/user")
    public void user(Router router) {
        router.post("/user").handler(ctx->{

        });
    }

    //    @ApiOperation(value = "/userLogin")
    public void userLogin(Router router) {
        router.get("/userLogin").handler(ctx->{

        });
    }

    //    @ApiOperation(value = "/user/{username}")
    public void username(Router router) {
        router.get("/user/:username").handler(ctx->{

        });
    }

}