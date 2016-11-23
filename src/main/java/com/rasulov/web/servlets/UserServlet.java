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

        String stringAge = request.getParameter("age");
        int age = 0;
        if(stringAge!=null){
            age = Integer.parseInt(stringAge);
        }

        response.setContentType("text/json");
        User user = new User();
        user.setName(request.getParameter("name"));
        user.setLastName(request.getParameter("lastName"));
        user.setEmail(request.getParameter("email"));
        user.setAge(age);
        user.setHobby(request.getParameter("hobby"));

        log.info(user.toString());

        userDao.save(user);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        JSONObject json = new JSONObject();
        String email= request.getParameter("email");

        log.info("Search by email: +" + email);

        User user = userDao.getUserFromEmail(email);
        json.put("name", user.getName());
        json.put("lastName", user.getLastName());
        json.put("email", user.getEmail());
        json.put("age", user.getAge());
        json.put("hobby", user.getHobby());

        log.info("Response from server: "+user.toString());

        response.setContentType("application/json");
        response.getWriter().write(json.toString());
    }

}