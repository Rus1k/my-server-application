package com.rasulov.web.servlets;

import com.rasulov.entity.User;
import com.rasulov.repository.dao.UserDao;
import com.rasulov.repository.dao.UserDaoImpl;
import lombok.extern.java.Log;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Log
public class UserServlet extends HttpServlet {

    private UserDao userDao = new UserDaoImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.info("do Post start work!");

        response.setContentType("text/json");

        User user = new User();

        String stringAge = request.getParameter("age");
        int age = 0;
        if (stringAge != null) {
            age = new Integer(stringAge);
        }

        user.setName(request.getParameter("name"));
        user.setLastName(request.getParameter("lastName"));
        user.setEmail(request.getParameter("email"));
        user.setAge(age);
        user.setHobby(request.getParameter("hobby"));

        log.info("doPost! User: " + user);

        userDao.save(user);
        log.info("do Post finish work!");

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.info("do Get start work!");

        JSONObject json = new JSONObject();

        String email = request.getParameter("email");
        boolean theUserHas = userDao.checkUser(email);

        log.info("doGet! theUserHas. " + theUserHas);
        log.info("doGet! Search by email: " + email);

        response.setContentType("application/json");
        if(theUserHas) {

            User user = userDao.getUserByEmail(email);
            json.put("name", user.getName());
            json.put("lastName", user.getLastName());
            json.put("email", user.getEmail());
            json.put("age", user.getAge());
            json.put("hobby", user.getHobby());

            log.info("doGet! Response from server: " + user);

            response.getWriter().write(json.toString());
        }else {
            response.getWriter().write("");
        }
        log.info("do Get finish work!");

    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.info("do Put start work!");

        response.setContentType("text/json");

        User user = new User();

        String stringAge = request.getParameter("age");
        int age = 0;
        if (stringAge != null) {
            age = new Integer(stringAge);
        }

        user.setName(request.getParameter("name"));
        user.setLastName(request.getParameter("lastName"));
        user.setEmail(request.getParameter("email"));
        user.setAge(age);
        user.setHobby(request.getParameter("hobby"));

        log.info("do Put! user from request: " + user);

        userDao.update(user);

        log.info("do Put finish work!");
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.info("do Delete start work!");

        String email = request.getParameter("email");

        log.info("do Delete! User email: " + email);

        userDao.delete(email);

        log.info("do Delete finish work!");
    }
}