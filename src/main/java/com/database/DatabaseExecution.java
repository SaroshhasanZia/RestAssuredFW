package com.database;

import com.constants.FrameworkConstants;
import com.enums.PropertyType;
import com.utils.PropertyUtil;

import java.sql.*;

import static com.database.DatabaseConfig.dbConnection;


public class DatabaseExecution {
    private static String host = FrameworkConstants.getDbHost();
    private static String user = FrameworkConstants.getDbUserName();
    private static String password = FrameworkConstants.getDbPassword();


    public static void updateCQM(String query) throws SQLException, ClassNotFoundException {

        Connection con = dbConnection(host,user,password);

        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = con.createStatement();
            int rowsAffected = stmt.executeUpdate(query);
            System.out.println(rowsAffected + " row(s) affected.");


        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            con.close();

        }
    }

    public static void selectQuery(String query,String host, String username, String password) throws SQLException, ClassNotFoundException {

        Connection con = dbConnection(host, username, password);

        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                String columnOne = rs.getString(1);
                String columnTwo = rs.getString(2);
                System.out.println(columnOne + "  " + columnTwo);


            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            con.close();

        }
    }


}
