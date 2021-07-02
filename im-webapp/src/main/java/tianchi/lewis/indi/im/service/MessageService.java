package tianchi.lewis.indi.im.service;

import tianchi.lewis.indi.im.model.Message;
import tianchi.lewis.indi.im.model.Page;

import java.util.List;

public interface MessageService {

    List<Message> retrieve(Page page,String token);

    void send(Message message,String token);

}
