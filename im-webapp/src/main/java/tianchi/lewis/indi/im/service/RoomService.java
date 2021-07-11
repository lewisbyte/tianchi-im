package tianchi.lewis.indi.im.service;

import tianchi.lewis.indi.im.model.Page;
import tianchi.lewis.indi.im.model.Room;
import tianchi.lewis.indi.im.model.RoomList;
import tianchi.lewis.indi.im.model.RoomUser;

import java.util.List;

public interface RoomService {

    void createRoom(Room room);

    void enterRoom(String roomid, String token);

    void leaveRoom(String token);

    Room getRoomInfo(String roomid);

    List<String> getRoomUserInfo(String roomid);

    List<RoomList> getRoomList(Page page);

}
