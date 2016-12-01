package com.rasulov.repository;


import com.rasulov.repository.query.impl.CreateTable;
import lombok.extern.java.Log;

import java.sql.*;

@Log
public class SQLiteConnection {

    private static final String url= "jdbc:sqlite:client_server_DB.s3db";


    private SQLiteConnection() {
        CreateTable.createTable();
    }

    public static Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection(url);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  connection;
    }

//    public static void closeDB(Connection connection){
//        try {
//            connection.close();
//        } catch (SQLException e) {
//            log.info("SQLException info: " + e);
//        }
//        log.info("close connect");
//    }
}