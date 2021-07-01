package tianchi.lewis.indi.im.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import tianchi.lewis.indi.im.model.Page;
import tianchi.lewis.indi.im.model.Room;
import tianchi.lewis.indi.im.model.RoomList;
import tianchi.lewis.indi.im.service.RoomService;

/**
 * @program: tianchi-tianchi.lewis.indi.im
 * @description: Room 控制器
 * @author: lewis
 * @create: 2021-07-01 00:54
 */
@Api(tags = "[Room]")
@RestController
public class RoomController {

    @Autowired
    private RoomService roomService;

    @ApiOperation(value = "room")
    @RequestMapping(path = "/room", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public void f1(@RequestBody Room room) {

    }

    @ApiOperation(value = "/room/{roomid}")
    @RequestMapping(path = "/room/{roomid}", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public void f2(@RequestBody String roomid) {
    }

    @ApiOperation(value = "/roomLeave")
    @RequestMapping(path = "/roomLeave", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public void f3() {

    }

    @ApiOperation(value = "/room/{roomid}/enter")
    @RequestMapping(path = "/room/{roomid}/enter", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public void f4(@RequestBody String roomid) {

    }

    @ApiOperation(value = "/room/{roomid}/users")
    @RequestMapping(path = "/room/{roomid}/users", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public void f5(@RequestBody String roomid) {

    }

    @ApiOperation(value = "/roomList")
    @RequestMapping(path = "/roomList", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public void f6(@RequestBody Page page) {
    }
}