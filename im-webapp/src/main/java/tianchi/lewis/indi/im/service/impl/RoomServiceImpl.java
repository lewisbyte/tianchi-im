package tianchi.lewis.indi.im.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import tianchi.lewis.indi.im.entity.TRoom;
import tianchi.lewis.indi.im.exception.ControllerException;
import tianchi.lewis.indi.im.model.Page;
import tianchi.lewis.indi.im.model.Room;
import tianchi.lewis.indi.im.model.RoomList;
import tianchi.lewis.indi.im.serivce.RoomDataStoreService;
import tianchi.lewis.indi.im.service.RoomService;
import tianchi.lewis.indi.im.utils.SessionUtils;

import java.util.List;
import java.util.Objects;
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
    public String createRoom(Room room) {
        try {
            TRoom troom = TRoom.builder().name(room.getName()).build();
            roomDataStoreService.save(troom);
            return troom.getId().toString();
        } catch (Exception e) {
            e.printStackTrace();
            ControllerException.InvalidExceptionAccess.error(e);
        }
        return null;
    }

    @Override
    public void enterRoom(String roomid, String token) {
        if (Objects.isNull(roomDataStoreService.getRoomInfo(roomid))) {
            ControllerException.InvalidExceptionAccess.error(new RuntimeException("roomid 非法，无此房间信息"));
        }
        try {
            leaveRoom(token);
            SessionUtils.entryRoom(token, roomid);
        } catch (Exception e) {
            e.printStackTrace();
            ControllerException.InvalidExceptionAccess.error(e);
        }
    }

    @Override
    public void leaveRoom(String token) {
        try {
            SessionUtils.leaveRoom(token);
        } catch (Exception e) {
            e.printStackTrace();
            ControllerException.InvalidExceptionAccess.error(e);
        }
    }

    @Override
    public String getRoomInfo(String roomid) {
        TRoom troom = null;
        try {
            troom = roomDataStoreService.getRoomInfo(roomid);
        } catch (Exception e) {
            e.printStackTrace();
            ControllerException.InvalidExceptionAccess.error(e);
        }
        if (Objects.isNull(troom)) {
            ControllerException.InvalidExceptionAccess.error(new RuntimeException("roomid 非法，无此房间信息"));
        }
        return troom.getName();
    }

    @Override
    public List<String> getRoomUserInfo(String roomid) {
        try {
            List<String> users = SessionUtils.getRoomUsers(roomid);
            return CollectionUtils.isEmpty(users) ? Lists.newArrayList() : users;
        } catch (Exception e) {
            e.printStackTrace();
            ControllerException.InvalidExceptionAccess.error(e);
        }
        return Lists.newArrayList();
    }

    @Override
    public List<RoomList> getRoomList(Page page) {
        if (page.getPageIndex() < 0 || page.getPageSize() < 0) {
            ControllerException.InvalidExceptionAccess.error(new RuntimeException("分页查询错误，页码小于0"));
        }

        try {
            return roomDataStoreService.getRoomList(page.getPageIndex() + 1, page.getPageSize())
                    .stream()
                    .map(RoomList::new)
                    .collect(Collectors.toList());

        } catch (Exception e) {
            e.printStackTrace();
            ControllerException.InvalidExceptionAccess.error(e);
        }
        return Lists.newArrayList();
    }
}