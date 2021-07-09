package tianchi.lewis.indi.im.serivce.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tianchi.lewis.indi.im.dao.MessageMapper;
import tianchi.lewis.indi.im.entity.TMessage;
import tianchi.lewis.indi.im.serivce.MessageDataStoreService;

import java.util.List;

/**
 * @program: tianchi-im
 * @description:
 * @author: lewis
 * @create: 2021-07-03 12:04
 */
@Service
public class MessageDataStoreServiceImpl implements MessageDataStoreService {
    @Autowired
    private MessageMapper mapper;

    @Override
    public void save(TMessage tMessage) {
        mapper.insert(tMessage);
    }

    @Override
    public List<TMessage> getMessage(int pageIndex, int pageSize, Long roomid) {
        return mapper.selectPage(
                new Page<>(pageIndex, pageSize),
                Wrappers.<TMessage>query().lambda().eq(TMessage::getRoomid, roomid )
        ).getRecords();
    }
}