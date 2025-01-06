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
        if(allDatabases.isPresent()){
            System.out.println("You have " + allDatabases.get().size() + " databases." +
                    " Enter the name of database you want to restore. Type 'all' to restore all databases." );
            for(String databaseName: allDatabases.get()){ System.out.println( count + "." + " "+ databaseName); count++; }
            Scanner scanner = new Scanner(System.in);
            String databaseName = scanner.next();
            DbToRestore.DBToRestore(databaseName);


        }else{
            System.out.println("Invalid input provided.");
        }





    }
}

