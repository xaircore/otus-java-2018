package com.xairlab.otus.message_system.servlets;

import com.xairlab.otus.message_system.entity.FrontendService;
import com.xairlab.otus.message_system.entity.WebMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.util.HtmlUtils;

@Controller
public class Index {

    private final FrontendService frontendService;

    public Index(FrontendService frontendService) {
        this.frontendService = frontendService;
    }

    @GetMapping({"/"})
    public String index() {
        return "index.html";
    }

    @GetMapping({"/users"})
    public String userList(Model model) {
        frontendService.getUsersAction();
        model.addAttribute("users", frontendService.getUsers());
        return "users.html";
    }

    @GetMapping({"/add"})
    public String add() {
        return "add.html";
    }

    @MessageMapping("/message")
    @SendTo("/topic/response")
    public WebMessage getMessage(WebMessage message) {
        frontendService.saveUserAction(message.getName(), message.getAge());
        WebMessage answer = new WebMessage();
        answer.setName(HtmlUtils.htmlEscape(message.getName()));
        answer.setAge(message.getAge());
        return answer;
    }
}
