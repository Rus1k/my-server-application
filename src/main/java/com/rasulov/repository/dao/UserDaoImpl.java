package com.rasulov.repository.dao;

import com.rasulov.entity.User;
import com.rasulov.repository.Connect;


import java.sql.*;

public class UserDaoImpl implements UserDao {

    public static final String INSERT_USER = "INSERT INTO users(name, last_name, email, age, hobby) VALUES (?,?,?,?,?)";


    PreparedStatement preparedStatement = null;
    Connection connection = Connect.getConnect();


    @Override
    public void save(User user) {

        try {
            preparedStatement = connection.prepareStatement(INSERT_USER, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setInt(4, user.getAge());
            preparedStatement.setString(5, user.getHobby());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void delete(User user) {

    }

    @Override
    public String update(User user) {
        return null;
    }

    @Override
    public boolean checkUser(String email) {
        return false;
    }
}
