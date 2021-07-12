package tianchi.lewis.indi.im.service.impl;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tianchi.lewis.indi.im.entity.TMessage;
import tianchi.lewis.indi.im.exception.ControllerException;
import tianchi.lewis.indi.im.model.Message;
import tianchi.lewis.indi.im.model.Page;
import tianchi.lewis.indi.im.serivce.MessageDataStoreService;
import tianchi.lewis.indi.im.service.MessageService;
import tianchi.lewis.indi.im.utils.SessionUtils;

import java.util.List;
import java.util.stream.Collectors;

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
        try {
            String roomid = SessionUtils.getRoomInfoByToken(token);
            return messageDataStoreService.getMessage(page.getPageIndex(), page.getPageSize(), Long.valueOf(roomid))
                    .stream()
                    .map(Message::new)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
            ControllerException.InvalidExceptionAccess.error(e);
        }
        return Lists.newArrayList();
    }

    @Override
    public void send(Message message, String token) {

        // 用户未登录
        if (StringUtils.isEmpty(token) || !SessionUtils.verifyLoginStatus(token)) {
            ControllerException.InvalidExceptionAccess.error(new RuntimeException("auth token 非法"));
            return;
        }

        String roomid = SessionUtils.getRoomInfoByToken(token);

        if (StringUtils.isEmpty(roomid)) {
            ControllerException.InvalidExceptionAccess.error(new RuntimeException("用户没有进入房间"));
            return;
        }

        try {
            TMessage tMessage = TMessage.builder().roomid(Long.valueOf(roomid)).mid(message.getId()).text(message.getText()).stamp(System.currentTimeMillis()).build();
            messageDataStoreService.save(tMessage);
        } catch (Exception e) {
            e.printStackTrace();
            ControllerException.InvalidExceptionAccess.error(e);
        }
    }
}