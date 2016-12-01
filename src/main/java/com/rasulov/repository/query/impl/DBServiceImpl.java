package com.rasulov.repository.query.impl;


import com.rasulov.entity.User;
import com.rasulov.exception.UserDeleteException;
import com.rasulov.exception.UserUpdateException;
import com.rasulov.repository.SQLiteConnection;
import com.rasulov.repository.query.DBService;
import lombok.extern.java.Log;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Log
public class DBServiceImpl implements DBService {


    public static final String INSERT_USER = "INSERT INTO users(name, last_name, email, age, hobby) VALUES (?,?,?,?,?)";
    public static final String SELECT_USER = "SELECT id, name, last_name, email, age, hobby FROM users WHERE email = ?";
    public static final String DELETE_USER = "DELETE FROM users WHERE email = ?";
    public static final String CHECK_EMAIL = "SELECT email FROM users WHERE email =?";
    public static final String UPDATE_USER = "UPDATE users SET name = ?, last_name = ?, email = ?,age = ?, hobby = ?" +
            " WHERE EXISTS (SELECT id FROM users WHERE email = ?)";
    private Connection connection = SQLiteConnection.getConnection();


    @Override
    public void save(User user) {
        try {
            PreparedStatement insert = connection.prepareStatement(INSERT_USER);
            insert.setString(1, user.getName());
            insert.setString(2, user.getLastName());
            insert.setString(3, user.getEmail());
            insert.setInt(4, user.getAge());
            insert.setString(5, user.getHobby());
            insert.executeUpdate();

        } catch (SQLException e) {
            log.info("DBServiceImpl! save. SQLException info: " + e);
        }

    }

    @Override
    public void delete(String email) {

        try {
            PreparedStatement delete = connection.prepareStatement(DELETE_USER);
            delete.setString(1, email);
            int i = delete.executeUpdate();
            if (i == 0) {
                new UserDeleteException();
            }
        } catch (SQLException e) {
            log.info("DBServiceImpl! delete. SQLException info: " + e);
        }

    }

    @Override
    public void update(User user) {
        PreparedStatement update = null;
        try {
            log.info("DBServiceImpl! update user info: " + user);
            update = connection.prepareStatement(UPDATE_USER);
            update.setString(1, user.getName());
            update.setString(2, user.getLastName());
            update.setString(3, user.getEmail());
            update.setInt(4, user.getAge());
            update.setString(5, user.getHobby());
            update.setString(6, user.getEmail());
            int i = update.executeUpdate();
            if (i == 0) {
                new UserUpdateException();
            }
            log.info("updated");
        } catch (SQLException e) {
            log.info("DBServiceImpl! update. SQLException info: " + e);
        } finally {
            if (update != null) {
                try {
                    update.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    @Override
    public boolean checkUser(String email) {
        try {
            PreparedStatement check = connection.prepareStatement(CHECK_EMAIL);
            check.setString(1, email);
            ResultSet resultSet = check.executeQuery();


            if (!resultSet.getBoolean("email")){
                return true;
            }else {
                System.out.println("else");
                return false;
            }
        } catch (SQLException e) {
            log.info("DBServiceImpl! checkUser. SQLException from checkUser: " + e);
        }
        return false;
    }

    @Override
    public User getUserByEmail(String email) {
        User user = null;
        try {
            PreparedStatement select = connection.prepareStatement(SELECT_USER);
            log.info("DBServiceImpl! get user by email " + email);
            select.setString(1, email);
            ResultSet resultSet = select.executeQuery();
            if (resultSet.next()) {
                user = User.builder()
                        .id(resultSet.getInt("id"))
                        .name(resultSet.getString("name"))
                        .lastName(resultSet.getString("last_name"))
                        .email(email)
                        .age(resultSet.getInt("age"))
                        .hobby(resultSet.getString("hobby"))
                        .build();
            }
            log.info("DBServiceImpl! result User from DB " + user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }
}
