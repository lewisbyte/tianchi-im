package tianchi.lewis.indi.im.service.impl;

import cn.hutool.core.bean.BeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tianchi.lewis.indi.im.entity.TUser;
import tianchi.lewis.indi.im.model.User;
import tianchi.lewis.indi.im.serivce.UserDataStoreService;
import tianchi.lewis.indi.im.service.UserService;
import tianchi.lewis.indi.im.utils.SessionUtils;

/**
 * @program: tianchi-tianchi.lewis.indi.im
 * @description:
 * @author: lewis
 * @create: 2021-07-01 00:58
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDataStoreService userDataStoreService;

    @Override
    public void create(User user) {
        TUser tUser = TUser.builder().build();
        BeanUtil.copyProperties(user, tUser);
        userDataStoreService.create(tUser);
    }

    @Override
    public String login(String username, String password) {

        String token = userDataStoreService.login(username, password);
        SessionUtils.login(token);
        return token;
    }

    @Override
    public User getInfo(String username) {
        User user = new User();
        TUser tuser = userDataStoreService.getInfo(username);
        BeanUtil.copyProperties(tuser, user);
        return user;
    }
}