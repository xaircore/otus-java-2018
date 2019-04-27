package com.xairlab.otus.jetty.servlets;

import com.xairlab.otus.jetty.entity.User;
import com.xairlab.otus.jetty.service.UserService;
import org.jtwig.JtwigModel;
import org.jtwig.JtwigTemplate;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class Find extends HttpServlet {

    private final UserService userService;
    private final org.jtwig.JtwigTemplate template = JtwigTemplate.classpathTemplate("/templates/find.html");

    public Find(UserService userService) {
        this.userService = userService;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String search = req.getParameter("search");
        boolean find = false;
        List<User> users = userService.all();
        for (User user : users) {
            if (user.getName().equals(search)) {
                JtwigModel model = new JtwigModel();
                model.with("user", user);
                template.render(model, resp.getOutputStream());
                find = true;
            }
        }
        if (!find) {
            resp.sendError(404);
        }
    }
}
