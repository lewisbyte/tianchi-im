package tianchi.lewis.indi.im.service.impl;

import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import tianchi.lewis.indi.im.service.UserService;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class UserServiceImplTest {

    @Autowired
    private UserService userService;
    @Test
    void getInfo() {
        assertNotNull(userService.getInfo("hahahaddadadwdwqaasdsadadadsaha"));
    }
}