package tianchi.lewis.indi.im.service.impl;

import org.springframework.stereotype.Service;
import tianchi.lewis.indi.im.model.Message;
import tianchi.lewis.indi.im.model.Page;
import tianchi.lewis.indi.im.service.MessageService;

import java.util.List;

/**
 * @program: tianchi-tianchi.lewis.indi.im
 * @description:
 * @author: lewis
 * @create: 2021-07-01 00:57
 */
@Service
public class MessageServiceImpl implements MessageService {


    @Override
    public List<Message> retrieve(Page page, String token) {
        return null;
    }

    @Override
    public void send(Message message, String token) {

    }
}