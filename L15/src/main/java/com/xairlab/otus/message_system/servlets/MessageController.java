package com.xairlab.otus.message_system.servlets;

import com.xairlab.otus.message_system.entity.Message;
import com.xairlab.otus.message_system.entity.User;
import com.xairlab.otus.message_system.service.UserService;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

@Controller
public class MessageController {

    private final UserService userService;

    public MessageController(UserService userService) {
        this.userService = userService;
    }

    @MessageMapping("/message")
    @SendTo("/topic/response")
    public Message getMessage(Message message) {
        User user = new User();
        user.setName(message.getName());
        user.setAge(message.getAge());
        userService.save(user);
        Message answer = new Message();
        answer.setName(HtmlUtils.htmlEscape(message.getName()));
        answer.setAge(message.getAge());
        return answer;
    }

}
