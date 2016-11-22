package com.rasulov.repository.dao;


import com.rasulov.entity.User;

public interface UserDao {


    void save(User user);
    String delete(User user);
    String update(User user);
    boolean checkUser(String email);
    User getUserFromEmail(String email);

}