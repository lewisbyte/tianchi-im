package tianchi.lewis.indi.im.service;

import tianchi.lewis.indi.im.model.User;

import java.util.List;

public interface UserService {
    void create(User user);

    String login(String username, String password);

    User getInfo(String username);

}
