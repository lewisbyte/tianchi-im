package tianchi.lewis.indi.im.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import tianchi.lewis.indi.im.model.Message;
import tianchi.lewis.indi.im.model.Page;
import tianchi.lewis.indi.im.service.MessageService;

/**
 * @program: tianchi-tianchi.lewis.indi.im
 * @description: 消息
 * @author: lewis
 * @create: 2021-07-01 00:54
 */
@Api(tags = "[Message]")
@RestController(value = "/v1")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @ApiOperation(value = "/message/retrieve")
    @RequestMapping(path = "/message/retrieve", method = {RequestMethod.POST}, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public void f3(@RequestBody Message message) {

    }

    @ApiOperation(value = "/message/send")
    @RequestMapping(path = "/message/send", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public void f4(@RequestBody Page page) {

    }

}