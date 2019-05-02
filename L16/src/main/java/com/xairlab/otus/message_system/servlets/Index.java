package com.xairlab.otus.message_system.servlets;

import com.xairlab.otus.message_system.entity.User;
import com.xairlab.otus.message_system.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class Index {

    private final UserService userService;

    public Index(UserService userService) {
        this.userService = userService;
    }

    @GetMapping({"/"})
    public String index(Model model) {
        model.addAttribute("user", new User());
        return "index.html";
    }

    @GetMapping({"/users"})
    public String userList(Model model) {
        List<User> users = userService.all();
        model.addAttribute("users", users);
        model.addAttribute("user", new User());
        return "users.html";
    }

    @GetMapping({"/add"})
    public String add(Model model) {
        model.addAttribute("user", new User());
        return "add.html";
    }
}
