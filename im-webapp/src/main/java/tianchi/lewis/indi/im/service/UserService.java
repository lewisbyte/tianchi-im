package tianchi.lewis.indi.im.service;

import tianchi.lewis.indi.im.model.RespUser;
import tianchi.lewis.indi.im.model.User;

import java.util.List;

public interface UserService {
    void create(User user);

    String login(String username, String password);

    RespUser getInfo(String username);

}
