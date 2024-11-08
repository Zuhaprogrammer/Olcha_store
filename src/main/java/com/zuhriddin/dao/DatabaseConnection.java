package com.zuhriddin.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class DatabaseConnection {
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "192002";
    private static final String URL = "jdbc:postgresql://localhost:5432/olcha_store";

    public Connection connection() {
        try {
            return DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
