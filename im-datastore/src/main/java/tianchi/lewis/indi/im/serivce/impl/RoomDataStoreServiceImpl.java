package tianchi.lewis.indi.im.serivce.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tianchi.lewis.indi.im.dao.RoomMapper;
import tianchi.lewis.indi.im.entity.TRoom;
import tianchi.lewis.indi.im.serivce.RoomDataStoreService;

/**
 * @program: tianchi-im
 * @description:
 * @author: lewis
 * @create: 2021-07-03 12:04
 */
@Service
public class RoomDataStoreServiceImpl implements RoomDataStoreService {

    @Autowired
    private RoomMapper roomMapper;


    @Override
    public void save(TRoom room) {
        roomMapper.insert(room);
    }
}