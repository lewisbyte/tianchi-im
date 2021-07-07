package tianchi.lewis.indi.im.service.impl;

import cn.hutool.core.bean.BeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tianchi.lewis.indi.im.entity.TRoom;
import tianchi.lewis.indi.im.exception.ControllerException;
import tianchi.lewis.indi.im.model.Page;
import tianchi.lewis.indi.im.model.Room;
import tianchi.lewis.indi.im.model.RoomList;
import tianchi.lewis.indi.im.model.RoomUser;
import tianchi.lewis.indi.im.serivce.RoomDataStoreService;
import tianchi.lewis.indi.im.service.RoomService;
import tianchi.lewis.indi.im.utils.SessionUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @program: tianchi-tianchi.lewis.indi.im
 * @description:
 * @author: lewis
 * @create: 2021-07-01 00:57
 */
@Service
public class RoomServiceImpl implements RoomService {
    @Autowired
    private RoomDataStoreService roomDataStoreService;

    @Override
    public void createRoom(Room room) {
        try {
            roomDataStoreService.save(TRoom.builder().name(room.getName()).build());
        } catch (Exception e) {
            ControllerException.InvalidExceptionAccess.error();
        }
    }

    @Override
    public void enterRoom(String roomid, String token) {
        try {
            SessionUtils.entryRoom(token, roomid);
        } catch (Exception e) {
            ControllerException.InvalidExceptionAccess.error();
        }
    }

    @Override
    public void leaveRoom(String token) {
        try {
            SessionUtils.leaveRoom(token);
        } catch (Exception e) {
            ControllerException.InvalidExceptionAccess.error();
        }
    }

    @Override
    public Room getRoomInfo(String roomid) {
        TRoom troom = roomDataStoreService.getRoomInfo(roomid);
        Room room = new Room();
        BeanUtil.copyProperties(troom, room);
        return room;
    }

    @Override
    public List<RoomUser> getRoomUserInfo(String roomid) {
        return SessionUtils.getRoomUsers(roomid);
    }

    @Override
    public List<RoomList> getRoomList(Page page) {
        return roomDataStoreService.getRoomList(page.getPageIndex(), page.getPageSize())
                .stream()
                .map(RoomList::new)
                .collect(Collectors.toList());
    }
}