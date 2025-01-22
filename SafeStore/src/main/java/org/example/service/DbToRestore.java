package org.example.service;

import org.example.service.Incremental.MySQLIncremental;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DbToRestore {

    public static void DBToRestore(String databaseName , List<String> userInputs)  {

        String backupType = "";
        System.out.print("Type of backup you want to perform <full , incremental , differential> : ");

        while(databaseName.equals("all") && !backupType.equals("full")) {
            System.out.print("You can only perform full backups for all databases. Type 'full' to perform the full backup: ");
            Scanner scanner = new Scanner(System.in);
            backupType = scanner.next();
        }

        while(!backupType.equals("full") && !backupType.equals("incremental") && !backupType.equals("differential")) {
            Scanner scanner = new Scanner(System.in);
            backupType = scanner.next();
        }

        String database = userInputs.get(0);
        String host = userInputs.get(1);
        String port = userInputs.get(2);
        String username = userInputs.get(3);
        String password = userInputs.get(4);


        if(database.equals("mysql") && backupType.equals("full")){
                String desktopPath = System.getProperty("user.home") + "/Desktop/";   //user's data will be stored in Desktop.

                String[] commands = {"mysqldump", "-h", host , "-u", username, "-p"+password , databaseName};

                if(!port.equals("3306")){
                    commands = new String[]{"mysqldump", "-h", host, "-P", port, "-u", username, "-p" + password, databaseName};
                }

                if(databaseName.equals("all")){
                    commands = new String[]{"mysqldump", "-h", host, "-u", username, "-p" + password, "--all-databases"};
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

        if(database.equals("mysql") && backupType.equals("incremental")){


            MySQLIncremental incremental = new MySQLIncremental();
            incremental.incremental(username , password , databaseName);

        }



    }



}
