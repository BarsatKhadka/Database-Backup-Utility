package org.example.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class IsValidInput {


    public static Optional<List<String>> isValidInput(List<String> dataBaseDetails){

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

                //query for all databases except read databases that sql dynamically generates.
               ResultSet resultFromDatabase = connection.createStatement().executeQuery
                       ("SHOW DATABASES WHERE `Database` NOT IN (\n" +
                       "    'information_schema', \n" +
                       "    'performance_schema', \n" +
                       "    'sys', \n" +
                       "    'mysql'\n" +
                       ");");

               System.out.println("Connection successful");
               System.out.println();

               List<String> databases = new ArrayList<>();
               while (resultFromDatabase.next()) {
                   databases.add(resultFromDatabase.getString(1));
               }

               return databases.isEmpty() ? Optional.empty() : Optional.of(databases);


            } catch (Exception e) {
              String exceptionType = e.getClass().getSimpleName();
              String exceptionMessage = e.getMessage();
              System.out.println(exceptionType + ":" + " " + exceptionMessage);

            }
      }


      return Optional.empty();



    }


}
