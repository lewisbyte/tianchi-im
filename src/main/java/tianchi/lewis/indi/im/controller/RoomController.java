package tianchi.lewis.indi.im.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: tianchi-im
 * @description: Room 控制器
 * @author: lewis
 * @create: 2021-07-01 00:54
 */
@Api(tags = "[room]")
@RestController
public class RoomController {

    @ApiOperation(value = "room")
    @RequestMapping(path = "/room", method = RequestMethod.POST)
    public void f1() {

    }
    @ApiOperation(value = "/room/{roomid}")
    @RequestMapping(path = "/room/{roomid}", method = RequestMethod.POST)
    public void f2() {
    }
    @ApiOperation(value = "/roomLeave")
    @RequestMapping(path = "/roomLeave", method = RequestMethod.POST)
    public void f3() {

    }
    @ApiOperation(value = "/room/{roomid}/enter")
    @RequestMapping(path = "/room/{roomid}/enter", method = RequestMethod.POST)
    public void f4() {

    }
    @ApiOperation(value = "/room/{roomid}/users")
    @RequestMapping(path = "/room/{roomid}/users", method = RequestMethod.POST)
    public void f5() {

    }
    @ApiOperation(value = "/roomList")
    @RequestMapping(path = "/roomList", method = RequestMethod.POST)
    public void f6() {
    }
}