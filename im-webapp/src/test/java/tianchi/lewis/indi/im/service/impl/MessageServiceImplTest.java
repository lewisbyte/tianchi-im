package tianchi.lewis.indi.im.service.impl;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import tianchi.lewis.indi.im.model.Page;
import tianchi.lewis.indi.im.serivce.MessageDataStoreService;
import tianchi.lewis.indi.im.service.MessageService;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class MessageServiceImplTest {


    @Autowired
    private MessageDataStoreService messageService;


    @Test
    void retrieve() {
        assertEquals(4,messageService.getMessage(-2,2,1412450112839880706L).size());
    }
}