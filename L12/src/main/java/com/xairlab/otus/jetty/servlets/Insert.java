package com.xairlab.otus.jetty.servlets;

import com.xairlab.otus.jetty.entity.User;
import com.xairlab.otus.jetty.service.UserService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Insert extends HttpServlet {

    private final UserService userService;

    public Insert(UserService userService) {
        this.userService = userService;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        User user = new User();
        user.setName(req.getParameter("name"));
        user.setAge(Integer.valueOf(req.getParameter("age")));
        userService.save(user);
        resp.sendRedirect("/users");
    }
}
