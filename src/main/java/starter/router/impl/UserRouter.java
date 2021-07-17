package starter.router.impl;

import io.vertx.core.http.HttpServerResponse;
import io.vertx.ext.web.Router;
import starter.constants.HttpHeaderConstant;
import starter.router.RouterConf;

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
        router.post("/user").handler(ctx -> {
            HttpServerResponse response = ctx.response();
            response.putHeader(HttpHeaderConstant.content_type, HttpHeaderConstant.text_plain);
        });
    }

    //    @ApiOperation(value = "/userLogin")
    public void userLogin(Router router) {
        router.get("/userLogin").handler(ctx -> {
            HttpServerResponse response = ctx.response();
            response.putHeader(HttpHeaderConstant.content_type, HttpHeaderConstant.text_plain);
        });
    }

    //    @ApiOperation(value = "/user/{username}")
    public void username(Router router) {
        router.get("/user/:username").handler(ctx -> {
            HttpServerResponse response = ctx.response();
            response.putHeader(HttpHeaderConstant.content_type, HttpHeaderConstant.application_json);
        });
    }

}