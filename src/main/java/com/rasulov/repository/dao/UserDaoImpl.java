package com.rasulov.repository.dao;

import com.rasulov.entity.User;
import com.rasulov.exception.UserDeleteException;
import com.rasulov.exception.UserUpdateException;
import com.rasulov.repository.SQLiteConnection;
import lombok.extern.java.Log;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


@Log
public class UserDaoImpl implements UserDao {

    public static final String INSERT_USER = "INSERT INTO users(name, last_name, email, age, hobby) VALUES (?,?,?,?,?)";
    public static final String SELECT_USER = "SELECT id, name, last_name, email, age, hobby FROM users WHERE email = ?";
    public static final String DELETE_USER = "DELETE FROM users WHERE email = ?";
    public static final String CHECK_EMAIL = "SELECT email FROM users WHERE email =?";
    public static final String UPDATE_USER = "UPDATE users SET name = ?, last_name = ?, email = ?,age = ?, hobby = ? " +
            "WHERE email = ?";

    PreparedStatement preparedStatement = null;

    @Override
    public void save(User user) {
        log.info("Save user: " + user.toString());
        try {
            preparedStatement = SQLiteConnection.getConnection().prepareStatement(INSERT_USER);
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setInt(4, user.getAge());
            preparedStatement.setString(5, user.getHobby());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            log.info(e.toString());
        }

    }

    @Override
    public void delete(User user) {
        log.info("Delete user: " + user.toString());

        try {
            preparedStatement = SQLiteConnection.getConnection().prepareStatement(DELETE_USER);
            preparedStatement.setString(1, user.getEmail());
            int i = preparedStatement.executeUpdate();
            if (i == 0) {
                new UserDeleteException();
            }
        } catch (SQLException e) {
            log.info(e.toString());
        }

    }

    @Override
    public void update(User user) {
        log.info("update user: " + user.toString());

        try {
            preparedStatement = SQLiteConnection.getConnection().prepareStatement(UPDATE_USER);
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setInt(4, user.getAge());
            preparedStatement.setString(5, user.getHobby());
            int i = preparedStatement.executeUpdate();
            if (i == 0) {
                new UserUpdateException();
            }
        } catch (SQLException e) {
            log.info(e.toString());

        }
    }


    @Override
    public boolean checkUser(String email) {
        log.info("Check user by email: " + email);

        try {
            preparedStatement = SQLiteConnection.getConnection().prepareStatement(CHECK_EMAIL);
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet != null) {
                return true;
            } else return false;
        } catch (SQLException e) {
            log.info(e.toString());
        }
        return false;
    }

    @Override
    public User getUserFromEmail(String email) {
        log.info("Get user from email: " + email);

        User user = null;
        try {
            preparedStatement = SQLiteConnection.getConnection().prepareStatement(SELECT_USER);
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            user = User.builder()
                    .id(resultSet.getInt("id"))
                    .name(resultSet.getString("name"))
                    .lastName(resultSet.getString("last_name"))
                    .email(email)
                    .age(resultSet.getInt("age"))
                    .hobby(resultSet.getString("hobby"))
                    .build();
        } catch (SQLException e) {
            log.info(e.toString());
        }

        return user;
    }
}