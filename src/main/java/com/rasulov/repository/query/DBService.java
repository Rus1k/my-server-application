package com.rasulov.repository.query;


import com.rasulov.entity.User;

public interface DBService {


    void save(User user);
    void delete(String email);
    void update(User user);
    boolean checkUser(String email);
    User getUserByEmail(String email);

}
