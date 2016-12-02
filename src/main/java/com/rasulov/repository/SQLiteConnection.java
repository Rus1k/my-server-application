package com.rasulov.repository;


import com.rasulov.repository.query.impl.CreateTable;
import lombok.extern.java.Log;

import java.sql.*;

@Log
public class SQLiteConnection {

    private static final String URL= "jdbc:sqlite:client_server_DB.s3db";
    public static Connection connection;
    private static SQLiteConnection sqLiteConnection = new SQLiteConnection();

    private SQLiteConnection() {

        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection(URL);

            CreateTable.createTable();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static SQLiteConnection getConnection() {
        return sqLiteConnection;
    }
}