package com.rasulov.repository;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


public final class SQLiteConnection {

    public Connection connection;
    public static Statement statement;
    public static SQLiteConnection db = new SQLiteConnection();

    private SQLiteConnection() {
        String url= "jdbc:sqlite:client_server_DB.s3db";

        try {
            Class.forName("org.sqlite.JDBC");
            this.connection = DriverManager.getConnection(url);
            createTable();
        }
        catch (Exception sqle) {
            sqle.printStackTrace();
        }
    }

    public static void createTable(){
        try {
            statement = db.connection .createStatement();
            statement.execute("CREATE TABLE [users] (" +
                    "[id] INTEGER  NOT NULL PRIMARY KEY AUTOINCREMENT," +
                    "[name] VARCHAR(60)  NOT NULL, " +
                    "[last_name] VARCHAR(60)  NOT NULL, " +
                    "[email] VARCHAR(200)  NOT NULL, " +
                    "[age] INTEGER NOT NULL, " +
                    "[hobby] VARCHAR(200)  NULL)");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("table create successfully");
    }

    public static void closeDB(Connection connection){
        try {
            connection.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("close connect");
    }
}