package com.rasulov.repository.query.impl;

import com.rasulov.repository.SQLiteConnection;
import lombok.extern.java.Log;

import java.sql.Connection;
import java.sql.SQLException;

@Log
public class CreateTable {


    public static void createTable() {
        try {
            Connection connection = SQLiteConnection.getConnection();
            connection.createStatement().execute("CREATE TABLE IF NOT EXISTS [users] (" +
                    "[id] INTEGER  NOT NULL PRIMARY KEY AUTOINCREMENT," +
                    "[name] VARCHAR(60)  NOT NULL, " +
                    "[last_name] VARCHAR(60)  NOT NULL, " +
                    "[email] VARCHAR(200)  NOT NULL, " +
                    "[age] INTEGER NOT NULL, " +
                    "[hobby] VARCHAR(200)  NULL)");
        } catch (SQLException e) {
            log.info("SQLException info: " + e);
        }
        log.info("table create successfully");
    }

}
