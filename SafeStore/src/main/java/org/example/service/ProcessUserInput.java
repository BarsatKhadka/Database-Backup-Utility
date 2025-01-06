package org.example.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class ProcessUserInput {

    public String isValidInput(List<String> dataBaseDetails){

      if(dataBaseDetails.size() != 4){
          return "Insufficient parameters provided.";
      }

      String host = dataBaseDetails.get(0);
      String port = dataBaseDetails.get(1);
      String username = dataBaseDetails.get(2);
      String password = dataBaseDetails.get(3);

     try (Connection connection = DriverManager.getConnection("jdbc:mysql://" + host+ ":"+ port, username, password)){
                System.out.println("Connection successful");
     } catch (SQLException e) {
         throw new RuntimeException(e);
     }

        return "true";
    }


}
