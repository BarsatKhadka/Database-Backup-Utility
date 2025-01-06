package org.example.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class ProcessUserInput {

    public void isValidInput(List<String> dataBaseDetails){

      if(dataBaseDetails.size() != 4){
          System.out.println("Insufficient parameters provided.");
      }

      String database = dataBaseDetails.get(0);
      String host = dataBaseDetails.get(1);
      String port = dataBaseDetails.get(2);
      String username = dataBaseDetails.get(3);
      String password = dataBaseDetails.get(4);

      if(database.equals("mysql")) {
            try (Connection connection = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port, username, password)) {
                System.out.println("Connection successful");
            } catch (Exception e) {
              String exceptionType = e.getClass().getSimpleName();
              String exceptionMessage = e.getMessage();
              System.out.println(exceptionType + ":" + " " + exceptionMessage);
            }
      }



    }


}
