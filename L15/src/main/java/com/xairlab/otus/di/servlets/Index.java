package com.xairlab.otus.di.servlets;

import com.xairlab.otus.di.entity.User;
import com.xairlab.otus.di.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

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
