package org.example.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ProcessUserInput {

    public void isValidInput(List<String> dataBaseDetails){

      if(dataBaseDetails.size() != 5){
          System.out.println("Insufficient parameters provided.");
      }

      String database = dataBaseDetails.get(0);
      String host = dataBaseDetails.get(1);
      String port = dataBaseDetails.get(2);
      String username = dataBaseDetails.get(3);
      String password = dataBaseDetails.get(4);

      if(database.equals("mysql")) {
            try (Connection connectionForValidation = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port, username, password)) {
               Connection connection = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port, username,password);
               ResultSet resultFromDatabase = connection.createStatement().executeQuery
                       ("SHOW DATABASES WHERE `Database` NOT IN (\n" +
                       "    'information_schema', \n" +
                       "    'performance_schema', \n" +
                       "    'sys', \n" +
                       "    'mysql'\n" +
                       ");");

               System.out.println("Connection successful");
               System.out.println();

               while (resultFromDatabase.next()) {
                   System.out.println(resultFromDatabase.getString(1));
               }


            } catch (Exception e) {
              String exceptionType = e.getClass().getSimpleName();
              String exceptionMessage = e.getMessage();
              System.out.println(exceptionType + ":" + " " + exceptionMessage);
            }
      }



    }


}
