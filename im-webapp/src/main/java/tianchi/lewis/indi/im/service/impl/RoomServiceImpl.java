package tianchi.lewis.indi.im.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tianchi.lewis.indi.im.model.Page;
import tianchi.lewis.indi.im.model.Room;
import tianchi.lewis.indi.im.model.RoomList;
import tianchi.lewis.indi.im.model.RoomUser;
import tianchi.lewis.indi.im.serivce.RoomDataStoreService;
import tianchi.lewis.indi.im.service.RoomService;

import java.util.List;

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
        roomDataStoreService.save(tianchi.lewis.indi.im.entity.Room.builder().name(room.getName()).build());
    }

    @Override
    public void enterRoom(String roomid, String token) {

    }

    @Override
    public void leaveRoom(String token) {

    }

    @Override
    public Room getRoomInfo(String roomid) {
        return null;
    }

    @Override
    public List<RoomUser> getRoomUserInfo(String roomid) {
        return null;
    }

    @Override
    public List<RoomList> getRoomList(Page page) {
        return null;
    }
}