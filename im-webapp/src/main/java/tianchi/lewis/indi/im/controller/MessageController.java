package tianchi.lewis.indi.im.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import tianchi.lewis.indi.im.model.Message;
import tianchi.lewis.indi.im.model.Page;
import tianchi.lewis.indi.im.service.MessageService;
import tianchi.lewis.indi.im.utils.UserUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @program: tianchi-tianchi.lewis.indi.im
 * @description: 消息
 * @author: lewis
 * @create: 2021-07-01 00:54
 */
@Api(tags = "[Message]")
@RestController
@RequestMapping(value = "/v1")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @ResponseBody
    @ApiOperation(value = "/message/retrieve")
    @RequestMapping(path = "/message/retrieve", method = {RequestMethod.POST}, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Message> retrieve(@RequestBody Page page, HttpServletRequest request) {
        return messageService.retrieve(page, UserUtils.getToken(request));
    }

    @ApiOperation(value = "/message/send")
    @RequestMapping(path = "/message/send", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public void send(@RequestBody Message message, HttpServletRequest request) {
        messageService.send(message, UserUtils.getToken(request));
    }

}