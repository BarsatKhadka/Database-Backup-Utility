package org.example.service.Incremental;

import java.io.File;
import java.util.Scanner;

public class MySQLIncremental {
    public void incremental(String user , String password) {

        System.out.println("\nEnter relative path to store your Incremental Backup File. \n eg: If you want " +
                "your file in desktop , simply type Desktop");
        System.out.print("\nEnter relative path of your Incremental Backup File: ");
        String incrementalBackupDir = giveIncrementalBackupDir();
        while(!incrementalBackupDir.equals("directory path not valid")) {
            System.out.println("---------Directory path not valid---------------");
            System.out.print("\nReEnter relative path of your Incremental Backup File: ");
            incrementalBackupDir = giveIncrementalBackupDir();
        }
        System.out.println("Enter file name");

    }



    public static String giveIncrementalBackupDir() {
        String homeDir = System.getProperty("user.home");

        Scanner scanner = new Scanner(System.in);
        String relativePath = scanner.next();

        String fullBackupDir = homeDir + File.separator + relativePath;
        if(isDirectoryExists(fullBackupDir)) {
            return fullBackupDir;
        }
        else{
            return "directory path not valid.";
        }

    }

    private static boolean isDirectoryExists(String directoryPath) {
        File directory = new File(directoryPath);
        return directory.exists() && directory.isDirectory();
    }
}
