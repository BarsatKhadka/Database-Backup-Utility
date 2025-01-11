package org.example.service.Incremental;

import java.io.File;
import java.util.Scanner;

public class MySQLIncremental {
    public void incremental(String user , String password) {
        String fullBackupDir = giveFullBackupDir();
        System.out.println(fullBackupDir);

    }

    public static String giveFullBackupDir() {
        String homeDir = System.getProperty("user.home");
        System.out.println("\nEnter relative path to store your Incremental Backup File. \n eg: If you want " +
                "your file in desktop , simply type Desktop");
        System.out.print("\nEnter relative path of your Incremental Backup File: ");
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
