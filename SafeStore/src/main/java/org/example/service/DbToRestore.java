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


        if(database.equals("mysql")){
                String desktopPath = System.getProperty("user.home") + "/Desktop/";   //user's data will be stored in Desktop.

                String[] commands = {"mysqldump", "-h", host , "-u", username, "-p"+password , databaseName};

                if(!port.equals("3306")){
                    commands = new String[]{"mysqldump", "-h", host, "-P", port, "-u", username, "-p" + password, databaseName};
                }


                ProcessBuilder processBuilder = new ProcessBuilder(commands);
                // the '>' in shell command to redirect output to path is done by this in process Builder.
                processBuilder.redirectOutput(new File(desktopPath+ databaseName+".sql"));

                try {
                    Process process = processBuilder.start();
                    process.waitFor();
                    System.out.println("Database backup completed.");
                } catch (IOException e) {
                    throw new RuntimeException("Failed to execute the database backup command. Please ensure MySQL is installed and accessible, " +
                            "and the output file path is valid. Error: " + e.getMessage());
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }


            }

    }



}
