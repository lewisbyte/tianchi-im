package tianchi.lewis.indi.im.service.impl;

import cn.hutool.core.bean.BeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tianchi.lewis.indi.im.entity.TUser;
import tianchi.lewis.indi.im.exception.ControllerException;
import tianchi.lewis.indi.im.model.RespUser;
import tianchi.lewis.indi.im.model.User;
import tianchi.lewis.indi.im.serivce.UserDataStoreService;
import tianchi.lewis.indi.im.service.UserService;
import tianchi.lewis.indi.im.utils.SessionUtils;

import java.util.Objects;

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
        try {
            TUser tUser = TUser.builder().build();
            BeanUtil.copyProperties(user, tUser);
            userDataStoreService.create(tUser);
        } catch (Exception e) {
            e.printStackTrace();
            ControllerException.InvalidExceptionAccess.error();
        }
    }

    @Override
    public String login(String username, String password) {

        try {
            String token = userDataStoreService.login(username, password);
            if (StringUtils.isEmpty(token)){
                ControllerException.InvalidExceptionAccess.error();
            }
            SessionUtils.login(token, username);
            return token;
        } catch (Exception e) {
            e.printStackTrace();
            ControllerException.InvalidExceptionAccess.error();
        }
        return "login error";
    }

    @Override
    public RespUser getInfo(String username) {
        RespUser user = new RespUser();
        TUser tuser = userDataStoreService.getInfo(username);
        if (Objects.isNull(tuser)) {
            ControllerException.InvalidExceptionAccess.error();
        }
        BeanUtil.copyProperties(tuser, user);
        return user;
    }
}