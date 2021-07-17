package starter.router.impl;

import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.JsonObject;
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
            try {
                HttpServerResponse response = ctx.response();
                JsonObject body = ctx.getBodyAsJson();


                response.putHeader(HttpHeaderConstant.content_type, HttpHeaderConstant.text_plain);

                response.end(body.toString());
            } catch (Exception e) {
                ctx.fail(400);
            }
        });
    }

    //    @ApiOperation(value = "/userLogin")
    public void userLogin(Router router) {
        router.get("/userLogin").handler(ctx -> {
            try {
                HttpServerResponse response = ctx.response();
                HttpServerRequest request = ctx.request();
                String username = request.getParam("username");
                String password = request.getParam("password");
                response.putHeader(HttpHeaderConstant.content_type, HttpHeaderConstant.text_plain);
            } catch (Exception e) {
                ctx.fail(400);
            }
        });
    }

    //    @ApiOperation(value = "/user/{username}")
    public void username(Router router) {
        router.get("/user/:username").handler(ctx -> {
            try {
                HttpServerResponse response = ctx.response();
                HttpServerRequest request = ctx.request();
                String username = request.getParam("username");

                response.putHeader(HttpHeaderConstant.content_type, HttpHeaderConstant.application_json);
            } catch (Exception e) {
                ctx.fail(400);
            }
        });
    }

}