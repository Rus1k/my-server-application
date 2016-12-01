package com.rasulov.repository.dao;


import com.rasulov.entity.User;

public interface UserDao {


    void save(User user);
    void delete(String email);
    void update(User user);
    boolean checkUser(String email);
    User getUserByEmail(String email);


}