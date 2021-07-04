package tianchi.lewis.indi.im.serivce.impl;

import org.springframework.beans.factory.annotation.Autowired;
import tianchi.lewis.indi.im.dao.MessageMapper;
import tianchi.lewis.indi.im.serivce.MessageDataStoreService;

/**
 * @program: tianchi-im
 * @description:
 * @author: lewis
 * @create: 2021-07-03 12:04
 */
public class MessageDataStoreServiceImpl implements MessageDataStoreService {
    @Autowired
    private MessageMapper mapper;

    public void test(){
    }

}