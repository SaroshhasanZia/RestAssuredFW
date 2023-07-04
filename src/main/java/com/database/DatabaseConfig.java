package com.database;

import com.enums.PropertyType;
import com.utils.PropertyUtil;
import lombok.Getter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConfig {




    protected static Connection dbConnection(String host,String user, String password) throws SQLException, ClassNotFoundException {


        Class.forName("com.mysql.cj.jdbc.Driver");

        Connection connection;

        connection = DriverManager.getConnection(host, user, password);


        return connection;

    }
}
