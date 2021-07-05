package tianchi.lewis.indi.im.serivce.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tianchi.lewis.indi.im.dao.UserMapper;
import tianchi.lewis.indi.im.entity.TUser;
import tianchi.lewis.indi.im.serivce.UserDataStoreService;

import java.util.Objects;

/**
 * @program: tianchi-im
 * @description:
 * @author: lewis
 * @create: 2021-07-03 12:04
 */
@Service
public class UserDataStoreServiceImpl implements UserDataStoreService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public void create(TUser user) {
        userMapper.insert(user);
    }

    @Override
    public String login(String username, String password) {
        TUser user = userMapper.selectOne(Wrappers.<TUser>query().lambda().eq(TUser::getUsername, username).eq(TUser::getPassword, password));
        return Objects.nonNull(user) ? user.getId().toString() : "";
    }

    @Override
    public TUser getInfo(String username) {
        return userMapper.selectOne(Wrappers.<TUser>query().lambda().eq(TUser::getUsername, username));
    }
}