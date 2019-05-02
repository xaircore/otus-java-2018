package com.xairlab.otus.message_system.servlets;

import com.xairlab.otus.message_system.entity.FrontendService;
import com.xairlab.otus.message_system.entity.User;
import com.xairlab.otus.message_system.entity.WebMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.util.HtmlUtils;

import java.util.List;

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
        frontendService.getUsers();
        model.addAttribute("users", frontendService.getFrontendUsers());
        return "users.html";
    }

    @GetMapping({"/add"})
    public String add(Model model) {
        model.addAttribute("user", new User());
        return "add.html";
    }

    @MessageMapping("/message")
    @SendTo("/topic/response")
    public WebMessage getMessage(WebMessage message) {
        frontendService.saveUser(message.getName(), message.getAge());
        WebMessage answer = new WebMessage();
        answer.setName(HtmlUtils.htmlEscape(message.getName()));
        answer.setAge(message.getAge());
        return answer;
    }
}
