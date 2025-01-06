package org.example;

import org.example.service.AskUserInput;
import org.example.service.IsValidInput;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class Main {
    public static void main(String[] args) throws SQLException {

        AskUserInput askUserInput = new AskUserInput();
        List<String> userInputs = askUserInput.askUserInput();

        //check if user input is valid or not. If user input is valid then it will give you list of databases according to user's input database.
        Optional<List<String>> checkInput = IsValidInput.isValidInput(userInputs);



    }
}

