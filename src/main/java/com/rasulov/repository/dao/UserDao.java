package com.rasulov.repository.dao;


import com.rasulov.entity.User;

public interface UserDao {


    void save(User user);
    void delete(User user);
    String update(User user);
    boolean checkUser(String email);

}
