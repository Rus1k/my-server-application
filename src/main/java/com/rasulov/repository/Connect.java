package com.rasulov.repository;


import java.sql.*;

public class Connect {

    public static Connection connection;
    public static Statement statement;

    private static final String URL = "jdbc:sqlite:client_server_DB.s3db";

    public Connect() {
    }

    public static Connection getConnect(){

        try {

            connection = null;
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection(URL);
            if (!connection.isClosed()) {
                System.out.println("connect successfully!");
            }
        } catch (SQLException e) {
            System.out.println("connect NOT successfully!"+ e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("connect NOT successfully!"+ e.getMessage());
        }
        return connection;
    }

    public static void createTable() throws ClassNotFoundException, SQLException
    {
        statement = connection.createStatement();
        statement.execute("CREATE TABLE [users] (" +
                "[id] INTEGER  NOT NULL PRIMARY KEY AUTOINCREMENT," +
                "[name] VARCHAR(60)  NOT NULL, " +
                "[last_name] VARCHAR(60)  NOT NULL, " +
                "[email] VARCHAR(200)  NOT NULL, " +
                "[age] INTEGER NOT NULL, " +
                "[hobby] VARCHAR(200)  NULL)");
        System.out.println("table create successfully");
    }

    public static void CloseDB() throws ClassNotFoundException, SQLException
    {
        connection.close();
        statement.close();

        System.out.println("close connect");
    }
}