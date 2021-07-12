package tianchi.lewis.indi.im.serivce;

import tianchi.lewis.indi.im.entity.TRoom;

import java.util.List;

public interface RoomDataStoreService {
    void save(TRoom room);

    TRoom getRoomInfo(String roomid);

    List<TRoom> getRoomList(int pageIndex, int pageSize);
}
