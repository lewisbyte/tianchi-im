package tianchi.lewis.indi.im.serivce;

import tianchi.lewis.indi.im.entity.TMessage;

import java.util.List;

public interface MessageDataStoreService {
    void save(TMessage tMessage);

    List<TMessage> getMessage(int pageIndex, int pageSize, Long roomid);

}

