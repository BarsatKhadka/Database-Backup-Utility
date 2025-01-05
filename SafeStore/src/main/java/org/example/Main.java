package org.example;

import org.example.service.AskUserInput;

import java.sql.SQLException;
import java.util.List;

public class Main
{
    public static void main( String[] args ) throws SQLException  {

        AskUserInput askUserInput = new AskUserInput();
        List<String> userInputs = askUserInput.askUserInput();
    }

    //mock connection to practice
//        Connection connection = null;
//        try {
//            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/grms", "root", "MySQL#123");
//        }
//        catch (SQLException e) {
//            System.out.println(e.getMessage());
//            throw new RuntimeException(e);
//
//        }
//
//        ResultSet rs = connection.createStatement().executeQuery("SELECT * FROM github_repo_entity");
//        while (rs.next()) {
//            System.out.println(rs.getString("full_name"));
//        }
//    }
}