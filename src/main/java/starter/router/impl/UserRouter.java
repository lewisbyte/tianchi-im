package starter.router.impl;

import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.sqlclient.Row;
import io.vertx.sqlclient.RowSet;
import io.vertx.sqlclient.Tuple;
import starter.constants.HttpHeaderConstant;
import starter.dao.PGSQLUtils;
import starter.exception.ControllerException;
import starter.router.RouterConf;
import starter.utils.SessionUtils;

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
            JsonObject body = ctx.getBodyAsJson();
            PGSQLUtils.getConnection().compose(sqlConnection ->
                    sqlConnection.
                            preparedQuery("INSERT INTO t_user (username, first_name,last_name,email,password,phone) VALUES ($1, $2, $3, $4, $5, $6)")
                            .execute(Tuple.of(body.getString("username"),
                                    body.getString("firstName"),
                                    body.getString("lastName"),
                                    body.getString("email"),
                                    body.getString("password"),
                                    body.getString("phone"))
                            ).onComplete(ar -> sqlConnection.close()));
            response.putHeader(HttpHeaderConstant.content_type, HttpHeaderConstant.text_plain);
            response.end();
        }).failureHandler(ctx -> {
            ctx.response().setStatusCode(400).end("Sorry! Not today");
        });
    }

    //    @ApiOperation(value = "/userLogin")
    public void userLogin(Router router) {
        router.get("/userLogin").handler(ctx -> {
            HttpServerResponse response = ctx.response();
            HttpServerRequest request = ctx.request();
            String username = request.getParam("username");
            String password = request.getParam("password");
            PGSQLUtils.getConnection().compose(sqlConnection -> sqlConnection.
                            preparedQuery("select id from t_user where username=$1 and password=$2").
                            execute(Tuple.of(username, password)).onComplete(ar -> {
                        if (ar.succeeded()) {
                            RowSet<Row> rows = ar.result();
                            if (rows.size() == 0) {
                                ControllerException.InvalidExceptionAccess.error(new RuntimeException("登录失败"));
                                ctx.fail(400);
                            }

                            for (Row row : rows) {
                                SessionUtils.login(row.getLong(0).toString(), username);
                            }
                        } else {
                            ControllerException.InvalidExceptionAccess.error(new RuntimeException("登录失败"));
                            ctx.fail(400);
                        }
                        sqlConnection.close();
                    })
            );

            response.putHeader(HttpHeaderConstant.content_type, HttpHeaderConstant.text_plain);
            response.end();

        }).failureHandler(ctx -> {
            ctx.response().setStatusCode(400).end("Sorry! Not today");
        });
    }

    //    @ApiOperation(value = "/user/{username}")
    public void username(Router router) {
        router.get("/user/:username").handler(ctx -> {
            HttpServerResponse response = ctx.response();
            HttpServerRequest request = ctx.request();
            String username = request.getParam("username");
            PGSQLUtils.getConnection().compose(sqlConnection ->
                    sqlConnection.preparedQuery("").execute().onComplete(ar -> sqlConnection.close())
            );

            response.putHeader(HttpHeaderConstant.content_type, HttpHeaderConstant.application_json);
            response.end();
        }).failureHandler(ctx -> {
            ctx.response().setStatusCode(400).end("Sorry! Not today");
        });
    }

}