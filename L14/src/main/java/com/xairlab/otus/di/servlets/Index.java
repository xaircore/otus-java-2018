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

    @PostMapping({"/insert"})
    public RedirectView insert(@ModelAttribute User user) {
        User newUser = new User();
        newUser.setName(user.getName());
        newUser.setAge(user.getAge());
        userService.save(newUser);
        return new RedirectView("/users", true);
    }

    @PostMapping({"/find"})
    public RedirectView find(@ModelAttribute User user) {
        User currentUser = userService.findByName(user.getName());
        if (currentUser == null) {
            Map<String, Object> model = new TreeMap<>();
            model.put("user", new User());
            RedirectView redirectView = new RedirectView("/", true);
            redirectView.setAttributesMap(model);
            return redirectView;
        } else {
            Map<String, Object> model = new TreeMap<>();
            model.put("user", currentUser);
            RedirectView redirectView = new RedirectView("/find", true);
            redirectView.setAttributesMap(model);
            return redirectView;
        }
    }
}
