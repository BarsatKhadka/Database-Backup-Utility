package org.example;

import org.example.service.AskUserInput;
import org.example.service.ProcessUserInput;

import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException {


//        ask User for input.
        AskUserInput askUserInput = new AskUserInput();
        List<String> userInputs = askUserInput.askUserInput();

        //process User input
        ProcessUserInput processUserInput = new ProcessUserInput();
        //first check if user input is valid or not
        processUserInput.isValidInput(userInputs);
    }
}

