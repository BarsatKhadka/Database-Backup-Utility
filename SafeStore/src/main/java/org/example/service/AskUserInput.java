package org.example.service;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class AskUserInput {

    public List<String> askUserInput(){
        System.out.println("Welcome to safeStore v1.0. Please provide the following information for a successful database backup.");
        System.out.println("---------------------------");

        System.out.print("host: ");
        Scanner hostScanner = new Scanner(System.in);
        String host = hostScanner.next();

        System.out.print("port: ");
        Scanner portScanner = new Scanner(System.in);
        String port = portScanner.next();

        System.out.print("username: ");
        Scanner usernameScanner = new Scanner(System.in);
        String username = usernameScanner.next();

        System.out.print("password: ");
        Scanner passwordScanner = new Scanner(System.in);
        String password = passwordScanner.next();


        return Arrays.asList(host, port, username, password);
    }
}
