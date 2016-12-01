package com.rasulov.repository.dao;

import com.rasulov.entity.User;
import com.rasulov.repository.query.DBService;
import com.rasulov.repository.query.impl.DBServiceImpl;
import lombok.extern.java.Log;


@Log
public class UserDaoImpl implements UserDao {

    private DBService dbService = new DBServiceImpl();


    @Override
    public void save(User user) {
        dbService.save(user);
        log.info("DAO! Save user: " + user);
    }

    @Override
    public void delete(String email) {
        dbService.delete(email);
        log.info("DAO! Delete user: " + email);
    }

    @Override
    public void update(User user) {
        dbService.update(user);
        log.info("DAO! Update user: " + user);
    }


    @Override
    public boolean checkUser(String email) {
        log.info("DAO! Check user by email: " + email);
        return dbService.checkUser(email);
    }

    @Override
    public User getUserByEmail(String email) {
        log.info("DAO! Get user from email: " + email);
        return dbService.getUserByEmail(email);
    }
}