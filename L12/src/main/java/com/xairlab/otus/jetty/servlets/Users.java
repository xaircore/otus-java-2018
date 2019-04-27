package com.xairlab.otus.jetty.servlets;

import com.xairlab.otus.jetty.service.UserService;
import org.jtwig.JtwigModel;
import org.jtwig.JtwigTemplate;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Users extends HttpServlet {

    private final UserService userService;

    private final JtwigTemplate template = JtwigTemplate.classpathTemplate("/templates/users.html");

    public Users(UserService userService) {
        this.userService = userService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        JtwigModel model = new JtwigModel();
        model.with("users", userService.all());

        template.render(model, resp.getOutputStream());
    }
}
