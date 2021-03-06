package tianchi.im.starter.router.impl;

import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.sqlclient.Row;
import io.vertx.sqlclient.Tuple;
import tianchi.im.starter.cache.CacheUser;
import tianchi.im.starter.constants.HttpHeaderConstant;
import tianchi.im.starter.dao.AsyncBatchInsertDao;
import tianchi.im.starter.dao.PGSQLUtils;
import tianchi.im.starter.router.RouterConf;
import tianchi.im.starter.utils.SessionUtils;

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
            String username = body.getString("username");
            if (CacheUser.exist(username)) {
                response.setStatusCode(400).end();
                return;
            }

            String firstName = body.getString("firstName");
            String lastName = body.getString("lastName");
            String email = body.getString("email");
            String phone = body.getString("phone");
            String password = body.getString("password");
            PGSQLUtils.getConnection().compose(
                    sqlConnection -> sqlConnection.
                            preparedQuery("select first_name,last_name,email,phone,password from t_user where username=$1").
                            execute(Tuple.of(username)).
                            onComplete(a -> sqlConnection.close())
            ).onSuccess(rows -> {

                // 未查询到，新增用户
                if (rows.size() == 0) {
                    // 缓存新增用户
                    CacheUser.add(username, CacheUser.User.builder().
                            firstName(firstName).
                            lastName(lastName).
                            email(email).
                            phone(phone).
                            password(password).
                            valid(true).
                            build());
                    SessionUtils.login(username, username);
                    //插入用户
                    AsyncBatchInsertDao.submitUser(String.format("('%s', '%s', '%s', '%s', '%s', '%s')",
                            username,
                            firstName,
                            lastName,
                            email,
                            password,
                            phone));
                    response.setStatusCode(200);
                } else {
                    // 查询到了，代表不可以插入，但是需要缓存到cache中
                    for (Row row : rows) {
                        CacheUser.add(username, CacheUser.User.builder().
                                firstName(row.getString("first_name")).
                                lastName(row.getString("last_name")).
                                email(row.getString("email")).
                                phone(row.getString("phone")).
                                valid(true).
                                build());
                        SessionUtils.login(username, username);

                    }
                    response.setStatusCode(400);
                }
                response.end();

            }).onFailure(event -> {
                // 查询失败的情况下
                PGSQLUtils.getConnection().compose(sqlConnection ->
                        sqlConnection.preparedQuery("INSERT INTO t_user (username, first_name,last_name,email,password,phone) VALUES ($1, $2, $3, $4, $5, $6)")
                                .execute(Tuple.of(username,
                                        firstName,
                                        lastName,
                                        email,
                                        password,
                                        phone)
                                ).onComplete(ar -> sqlConnection.close())
                );
                response.setStatusCode(400).end();
            });
        });
    }

    //    @ApiOperation(value = "/userLogin")
    public void userLogin(Router router) {
        router.get("/userLogin").handler(ctx -> {
            HttpServerResponse response = ctx.response();
            HttpServerRequest request = ctx.request();
            String username = request.getParam("username");
            String password = request.getParam("password");
            CacheUser.User cacheUser = CacheUser.get(username);
            response.putHeader(HttpHeaderConstant.content_type, HttpHeaderConstant.text_plain);

            // 已经登录失败，并且上次已经在数据库校验过没有此用户，则直接跳过
            if (cacheUser != null && !cacheUser.isValid()) {
                response.setStatusCode(400).end();
                return;
            }

            // 用户存在，检查用户密码
            if (cacheUser != null && cacheUser.isValid()) {
                boolean success = password.equals(cacheUser.getPassword());
                if (success) {
                    SessionUtils.login(username, username);
                }
                response.setStatusCode(success ? 200 : 400).end(success ? username : "");
                return;
            }

            // 数据库层面校验用户是否存在
            PGSQLUtils.getConnection().compose(
                    sqlConnection -> sqlConnection.
                            preparedQuery("select first_name,last_name,email,phone from t_user where username=$1 and password=$2").
                            execute(Tuple.of(username, password)).
                            onComplete(a -> sqlConnection.close())
            ).onSuccess(event -> {

                if (event.size() == 0) {
                    CacheUser.add(username, CacheUser.User.builder().valid(false).build());
                    response.setStatusCode(400).end();
                    return;
                }

                for (Row row : event) {
                    CacheUser.add(username, CacheUser.User.builder().
                            password(password).
                            firstName(row.getString("first_name")).
                            lastName(row.getString("last_name")).
                            email(row.getString("email")).
                            phone(row.getString("phone")).
                            valid(true).
                            build());
                    SessionUtils.login(username, username);
                }
                response.end(username);
            }).onFailure(event -> {
                        // 添加一个空用户，用于标识此用户不存在
                        CacheUser.add(username, CacheUser.User.builder().valid(false).build());
                        response.setStatusCode(400).end();
                    }
            );
        });
    }

    //    @ApiOperation(value = "/user/{username}")
    public void username(Router router) {
        router.get("/user/:username").handler(ctx -> {
            HttpServerResponse response = ctx.response();
            HttpServerRequest request = ctx.request();
            response.putHeader(HttpHeaderConstant.content_type, HttpHeaderConstant.application_json);
            String username = request.getParam("username");
            CacheUser.User user = CacheUser.get(username);
            if (user != null && user.isValid()) {
                JsonObject jsonObject = new JsonObject();
                jsonObject.put("firstName", user.getFirstName());
                jsonObject.put("lastName", user.getLastName());
                jsonObject.put("email", user.getEmail());
                jsonObject.put("phone", user.getPhone());
                response.end(jsonObject.toString());
                return;
            }

            PGSQLUtils.getConnection().compose(sqlConnection ->
                            sqlConnection.
                                    preparedQuery("select first_name,last_name,email,password,phone from t_user where username=$1").
                                    execute(Tuple.of(username)).onComplete(ar -> sqlConnection.close())
                    ).
                    onSuccess(rows -> {
                        if (rows.size() == 0) {
                            response.setStatusCode(400).end();
                            return;
                        }

                        for (Row row : rows) {
                            CacheUser.User newUser = CacheUser.User.builder().
                                    username(username).
                                    password(row.getString("password")).
                                    firstName(row.getString("first_name")).
                                    lastName(row.getString("last_name")).
                                    email(row.getString("email")).
                                    phone(row.getString("phone")).
                                    valid(true).
                                    build();
                            CacheUser.add(username, newUser);
                            JsonObject jsonObject = new JsonObject();
                            jsonObject.put("firstName", newUser.getFirstName());
                            jsonObject.put("lastName", newUser.getLastName());
                            jsonObject.put("email", newUser.getEmail());
                            jsonObject.put("phone", newUser.getPhone());
                            response.end(jsonObject.toString());
                        }
                    })
                    .onFailure(event -> {
                        response.setStatusCode(400).end();
                    });
        });
    }

}