package com.xairlab.otus.jetty;

import com.xairlab.otus.jetty.auth.BaseAuth;
import com.xairlab.otus.jetty.auth.DenyAuth;
import com.xairlab.otus.jetty.connection.H2Connection;
import com.xairlab.otus.jetty.entity.User;
import com.xairlab.otus.jetty.service.DBService;
import com.xairlab.otus.jetty.service.UserService;
import com.xairlab.otus.jetty.servlets.*;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.servlet.FilterHolder;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

public class Main {

    public static void main(String[] args) throws Exception {

        H2Connection h2con = new H2Connection();
        DBService<User> DBService = new DBService<>(h2con);

        UserService userService = new UserService(DBService);

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);

        BaseAuth baseAuth = new BaseAuth();
        baseAuth.addAdmin("admin", "admin");

        context.addServlet(new ServletHolder(new Index()), "/");
        context.addServlet(new ServletHolder(new Add()), "/add");
        context.addServlet(new ServletHolder(new Users(userService)), "/users");
        context.addServlet(new ServletHolder(new Insert(userService)), "/insert");
        context.addServlet(new ServletHolder(new Find(userService)), "/find");


        context.addFilter(new FilterHolder(baseAuth), "/", null);
        context.addFilter(new FilterHolder(new DenyAuth()), "/**", null);

        Server server = new Server(8080);
        server.setHandler(new HandlerList(context));

        server.start();
        server.join();

        h2con.close();
    }
}
