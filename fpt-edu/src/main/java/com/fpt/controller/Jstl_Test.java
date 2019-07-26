package com.fpt.controller;

import com.fpt.entity.Atube;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static com.googlecode.objectify.ObjectifyService.ofy;

public class Jstl_Test extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Atube> list = ofy().load().type(Atube.class).list();
        req.setAttribute("list", list);
        req.setAttribute("name", "Hello World!!");
        req.getRequestDispatcher("/jstl-test.jsp").forward(req, resp);
        try {

        } catch (Exception ex){
    }
}
}
