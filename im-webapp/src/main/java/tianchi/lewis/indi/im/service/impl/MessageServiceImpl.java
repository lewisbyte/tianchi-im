package tianchi.lewis.indi.im.service.impl;

import cn.hutool.core.bean.BeanUtil;
import org.checkerframework.checker.units.qual.A;
import org.codehaus.groovy.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tianchi.lewis.indi.im.entity.TMessage;
import tianchi.lewis.indi.im.model.Message;
import tianchi.lewis.indi.im.model.Page;
import tianchi.lewis.indi.im.serivce.MessageDataStoreService;
import tianchi.lewis.indi.im.service.MessageService;
import tianchi.lewis.indi.im.utils.SessionUtils;

import java.util.List;

/**
 * @program: tianchi-tianchi.lewis.indi.im
 * @description:
 * @author: lewis
 * @create: 2021-07-01 00:57
 */
@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageDataStoreService messageDataStoreService;

    @Override
    public List<Message> retrieve(Page page, String token) {
        return null;
    }

    @Override
    public void send(Message message, String token) {

        // 用户未登录
        if (StringUtils.isEmpty(token) || !SessionUtils.verifyLoginStatus(token)) {
            return;
        }

        String roomid = SessionUtils.getRoomInfoByToken(token);

        if (StringUtils.isEmpty(roomid)) {
            return;
        }

        TMessage tMessage = TMessage.builder().roomid(roomid).text(message.getText()).build();
        messageDataStoreService.save(tMessage);
    }
}