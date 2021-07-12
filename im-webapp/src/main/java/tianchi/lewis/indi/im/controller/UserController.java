package tianchi.lewis.indi.im.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import tianchi.lewis.indi.im.model.RespUser;
import tianchi.lewis.indi.im.model.User;
import tianchi.lewis.indi.im.service.UserService;

/**
 * @program: tianchi-tianchi.lewis.indi.im
 * @description: User 控制器
 * @author: lewis
 * @create: 2021-07-01 00:54
 */
@Api(tags = "[UserController]")
@RestController
@RequestMapping
public class UserController {

    @Autowired
    private UserService userService;

    @ApiOperation(value = "/user")
    @RequestMapping(path = "/user", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void user(@RequestBody User user) {
        userService.create(user);
    }

    @ApiOperation(value = "/userLogin")
    @RequestMapping(path = "/userLogin")
    public String userLogin(String username, String password) {
        return userService.login(username, password);
    }

    @ApiOperation(value = "/user/{username}")
    @RequestMapping(path = "/user/{username}", produces = MediaType.APPLICATION_JSON_VALUE)
    public RespUser username(@PathVariable(value = "username") String username) {
        return userService.getInfo(username);
    }
}