package com.rasulov.web.servlets;

import com.rasulov.entity.User;
import com.rasulov.repository.dao.UserDao;
import com.rasulov.repository.dao.UserDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SaveInfoServlet extends HttpServlet {
    UserDao userDao = new UserDaoImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String lastName = req.getParameter("lastName");
        String email = req.getParameter("email");
        int age = req.getIntHeader("age");
        String hobby = req.getParameter("hobby");

        userDao.save(User.builder()
                .name(name)
                .lastName(lastName)
                .email(email)
                .age(age)
                .hobby(hobby)
                .build());

    }
}