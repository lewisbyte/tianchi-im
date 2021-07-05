package tianchi.lewis.indi.im.serivce;

import tianchi.lewis.indi.im.entity.TUser;

import java.util.List;

public interface UserDataStoreService {
    void create(TUser user);

    String login(String username, String password);

    TUser getInfo(String username);

}
