package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Main
{
    public static void main( String[] args ) throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/grms", "root", "password");
        ResultSet rs = connection.createStatement().executeQuery("SELECT * FROM github_repo_entity");
        while (rs.next()) {
            System.out.println(rs.getString("full_name"));
        }
    }
}