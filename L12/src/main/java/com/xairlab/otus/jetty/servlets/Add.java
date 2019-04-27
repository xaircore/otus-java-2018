package com.xairlab.otus.jetty.servlets;

import org.jtwig.JtwigModel;
import org.jtwig.JtwigTemplate;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Add extends HttpServlet {

    private final JtwigTemplate template = JtwigTemplate.classpathTemplate("/templates/add.html");

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        JtwigModel model = new JtwigModel();

        template.render(model, resp.getOutputStream());
    }
}
