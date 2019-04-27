package com.xairlab.otus.jetty.auth;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

public class BaseAuth implements Filter {

    private ServletContext context;
    final private List<String> admins;

    public BaseAuth() {
        admins = new ArrayList<>();
    }

    private boolean isAdmin(byte[] hash) {
        String credentials = new String(hash);
        for (String data : admins) {
            if (data.equals(credentials)) {
                return true;
            }
        }
        return false;
    }

    public void addAdmin(String login, String password) {
        admins.add(login + ":" + password);
    }

    @Override
    public void init(FilterConfig filterConfig) {
        this.context = filterConfig.getServletContext();
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse res = (HttpServletResponse) servletResponse;

        HttpSession session = req.getSession(false);
        String authHeader = req.getHeader("Authorization");

        if (session != null || authHeader != null) {
            String credentials = authHeader.split(" ")[1];
            if (isAdmin(Base64.getDecoder().decode(credentials))) {
                filterChain.doFilter(servletRequest, servletResponse);
            } else {
                res.setStatus(401);
                res.setHeader("WWW-Authenticate", "Basic realm=\"jetty\"");
            }
        } else {
            res.setStatus(401);
            res.setHeader("WWW-Authenticate", "Basic realm=\"jetty\"");
        }
    }

    @Override
    public void destroy() {

    }
}
