package tianchi.lewis.indi.im.service.impl;

import org.springframework.stereotype.Service;
import tianchi.lewis.indi.im.model.User;
import tianchi.lewis.indi.im.service.UserService;

import java.util.List;

/**
 * @program: tianchi-tianchi.lewis.indi.im
 * @description:
 * @author: lewis
 * @create: 2021-07-01 00:58
 */
@Service
public class UserServiceImpl implements UserService {
    @Override
    public void create(User user) {

    }

    @Override
    public String login(String username, String password) {
        return null;
    }

    @Override
    public List<User> getInfo(String username) {
        return null;
    }
}