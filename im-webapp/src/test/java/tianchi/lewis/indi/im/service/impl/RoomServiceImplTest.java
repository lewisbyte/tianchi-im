package tianchi.lewis.indi.im.service.impl;

import org.checkerframework.checker.units.qual.A;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import tianchi.lewis.indi.im.service.RoomService;

import static org.junit.jupiter.api.Assertions.*;


@RunWith(SpringRunner.class)
@SpringBootTest
class RoomServiceImplTest {

    @Autowired
    RoomService roomService;

    @org.junit.jupiter.api.Test
    void getRoomInfo() {
        assertNotNull(roomService.getRoomInfo("1412426137359368194"));
    }
}