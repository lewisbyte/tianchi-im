package tianchi.lewis.indi.im.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import tianchi.lewis.indi.im.model.User;
import tianchi.lewis.indi.im.service.UserService;

/**
 * @program: tianchi-tianchi.lewis.indi.im
 * @description: User 控制器
 * @author: lewis
 * @create: 2021-07-01 00:54
 */
@Api(tags = "[UserController]")
@RestController(value = "/v1")
public class UserController {

    @Autowired
    private UserService userService;

    @ApiOperation(value = "/user")
    @RequestMapping(path = "/user", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public void f1(@RequestBody User user) {

    }

    @ApiOperation(value = "/userLogin")
    @RequestMapping(path = "/userLogin", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public void f2(String username,String password) {

    }

    @ApiOperation(value = "/user/{username}")
    @RequestMapping(path = "/user/{username}", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public void f3(@RequestBody String username) {

    }


}