package com.rasulov.repository.dao;

import com.rasulov.entity.User;
import com.rasulov.repository.SQLiteConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;



public class UserDaoImpl implements UserDao {

    public static final String INSERT_USER = "INSERT INTO users(name, last_name, email, age, hobby) VALUES (?,?,?,?,?)";
    public static final String SELECT_USER = "SELECT id, name, last_name, email, age, hobby FROM users WHERE email = ?";
    public static final String DELETE_USER = "DELETE FROM users WHERE email = ?";
    public static final String CHECK_EMAIL = "SELECT email FROM users WHERE email =?";
    public static final String UPDATE_USER = "UPDATE users SET name = ?, last_name = ?, email = ?,age = ?, hobby = ? " +
            "WHERE email = ?";

    String returnText;

    PreparedStatement preparedStatement = null;

    @Override
    public void save(User user) {
        try {
            preparedStatement = SQLiteConnection.db.connection.prepareStatement(INSERT_USER);
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
    public String delete(User user) {

        try {
            preparedStatement = SQLiteConnection.db.connection.prepareStatement(DELETE_USER);
            preparedStatement.setString(1, user.getEmail());
            int i = preparedStatement.executeUpdate();
            if (i == 0) {
                returnText = "not delete";
            } else returnText = "deleted";
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return returnText;

    }

    @Override
    public String update(User user) {
        try {
            preparedStatement = SQLiteConnection.db.connection.prepareStatement(UPDATE_USER);
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setInt(4, user.getAge());
            preparedStatement.setString(5, user.getHobby());
            int i = preparedStatement.executeUpdate();
            if (i == 0) {
                returnText = "no changes";
            } else returnText = "changes successfully";
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return returnText;
    }


    @Override
    public boolean checkUser(String email) {
        try {
            preparedStatement = SQLiteConnection.db.connection.prepareStatement(CHECK_EMAIL);
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet!= null){
                return true;
            }else return false;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public User getUserFromEmail(String email) {
        User user = null;
        try {
            preparedStatement = SQLiteConnection.db.connection.prepareStatement(SELECT_USER);
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
            e.printStackTrace();
        }

        return user;
    }
}