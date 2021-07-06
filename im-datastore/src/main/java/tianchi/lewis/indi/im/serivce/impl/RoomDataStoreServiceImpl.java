package tianchi.lewis.indi.im.serivce.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tianchi.lewis.indi.im.dao.RoomMapper;
import tianchi.lewis.indi.im.entity.TRoom;
import tianchi.lewis.indi.im.serivce.RoomDataStoreService;

import java.util.List;

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

    @Override
    public TRoom getRoomInfo(String roomid) {
        return roomMapper.selectById(roomid);
    }

    @Override
    public List<TRoom> getRoomList(int pageIndex, int pageSize) {
        Page<TRoom> page = new Page<>(pageIndex, pageSize);
        return roomMapper.selectPage(page, new QueryWrapper<>()).getRecords();
    }
}