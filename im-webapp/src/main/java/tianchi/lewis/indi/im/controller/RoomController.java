package tianchi.lewis.indi.im.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import tianchi.lewis.indi.im.model.Page;
import tianchi.lewis.indi.im.model.Room;
import tianchi.lewis.indi.im.model.RoomList;
import tianchi.lewis.indi.im.model.RoomUser;
import tianchi.lewis.indi.im.service.RoomService;
import tianchi.lewis.indi.im.utils.UserUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @program: tianchi-tianchi.lewis.indi.im
 * @description: Room 控制器
 * @author: lewis
 * @create: 2021-07-01 00:54
 */
@Api(tags = "[Room]")
@RestController
@RequestMapping
public class RoomController {

    @Autowired
    private RoomService roomService;

    @ApiOperation(value = "room")
    @RequestMapping(path = "/room", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public void room(@RequestBody Room room) {
        roomService.createRoom(room);
    }

    @ApiOperation(value = "/room/{roomid}")
    @RequestMapping(path = "/room/{roomid}", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Room roomRoomid(@PathVariable String roomid, HttpServletRequest request) {
        return roomService.getRoomInfo(roomid);
    }

    @ApiOperation(value = "/roomLeave")
    @RequestMapping(path = "/roomLeave", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public void roomLeave(HttpServletRequest request) {
        roomService.leaveRoom(UserUtils.getToken(request));
    }

    @ApiOperation(value = "/room/{roomid}/enter")
    @RequestMapping(path = "/room/{roomid}/enter", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public void enter(@PathVariable String roomid, HttpServletRequest request) {
        roomService.enterRoom(roomid, UserUtils.getToken(request));
    }

    @ApiOperation(value = "/room/{roomid}/users")
    @RequestMapping(path = "/room/{roomid}/users", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<RoomUser> users(@PathVariable String roomid) {
        return roomService.getRoomUserInfo(roomid);
    }

    @ApiOperation(value = "/roomList")
    @RequestMapping(path = "/roomList", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<RoomList> roomList(@RequestBody Page page) {
        return roomService.getRoomList(page);
    }
}