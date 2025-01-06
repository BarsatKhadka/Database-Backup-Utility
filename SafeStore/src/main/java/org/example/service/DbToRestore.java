package org.example.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class DbToRestore {

    public static void DBToRestore(String databaseName , List<String> userInputs)  {

        String database = userInputs.get(0);
        String host = userInputs.get(1);
        String port = userInputs.get(2);
        String username = userInputs.get(3);
        String password = userInputs.get(4);
        String osName = System.getProperty("os.name");

        if(database.equals("mysql")){
            if(osName.contains("Windows")){}
            else{
                String[] commands = {"mysqldump", "-h", host , "-u", username, "-p"+password , databaseName};

                ProcessBuilder processBuilder = new ProcessBuilder(commands);
                // the '>' in shell command to redirect output to path is done by this in process Builder.
                processBuilder.redirectOutput(new File(databaseName+".sql"));

                try {
                    Process process = processBuilder.start();
                    process.waitFor();
                    System.out.println("Database backup completed.");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }


            }

    }



}}
