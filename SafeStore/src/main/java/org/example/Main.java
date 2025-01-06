package org.example;

import org.example.service.AskUserInput;
import org.example.service.DbToRestore;
import org.example.service.IsValidInput;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Main {
    public static void main(String[] args)  {

        AskUserInput askUserInput = new AskUserInput();
        List<String> userInputs = askUserInput.askUserInput();

        //check if user input is valid or not. If user input is valid then it will give you list of databases according to user's input database.
        Optional<List<String>> allDatabases = IsValidInput.isValidInput(userInputs);

        int count = 1;
        if(allDatabases.isPresent()) {

            System.out.println("You have " + allDatabases.get().size() + " databases." +
                    " Enter the name of database you want to restore. Type 'all' to restore all databases.");

            //displaying all databases present.
            for (String databaseName : allDatabases.get()) {
                System.out.println(count + "." + " " + databaseName);
                count++;
            }

            //scanner to take user input's database name to restore
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter database name: ");
            String databaseName = scanner.next();

            if (allDatabases.get().contains(databaseName)) {
                DbToRestore.DBToRestore(databaseName);
            } else if (databaseName.equals("all")) {
                DbToRestore.DBToRestore("");
            }
            else {
                System.out.println("Invalid database name.");
                while(!allDatabases.get().contains(databaseName) && !databaseName.equals("all")){
                    System.out.print("Enter database name: ");
                    databaseName = scanner.next();

                }
                DbToRestore.DBToRestore(databaseName);

            }


        }
    }
}

