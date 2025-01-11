package org.example.service.Incremental;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class MySQLIncremental {
    public void incremental(String user , String password) {
        try {
            isXtraBackupInstalled();
        } catch (IOException e) {
            try {
                System.out.println("XtraBackup is not installed in your system. It will be automatically installed. If this process fails please try manually installing XtraBackup.");
                installXtraBackup();
            } catch (IOException | InterruptedException ex) {
                System.out.println(ex.getMessage() + " " + "report the issue to owner.");
            }
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }


        System.out.println("\nEnter relative path to store your Incremental Backup File. \n eg: If you want " +
                "your file in desktop , simply type Desktop");
        System.out.print("\nEnter relative path of your Incremental Backup File: ");
        String incrementalBackupDir = giveIncrementalBackupDir();
        while(incrementalBackupDir.equals("directory path not valid.")) {
            System.out.println("---------Directory path not valid---------------");
            System.out.print("\nReEnter relative path of your Incremental Backup File: ");
            incrementalBackupDir = giveIncrementalBackupDir();
        }

        System.out.print("Enter folder name: ");
        Scanner scanner = new Scanner(System.in);
        String folderName = scanner.next();
        if(isFileExists(incrementalBackupDir + File.separator + folderName)) {
            System.out.println("Folder already exists!");
        }
        else{


        }





    }

    private static boolean isXtraBackupInstalled() throws IOException, InterruptedException {
        ProcessBuilder pb = new ProcessBuilder("xtrabackup", "--version");
        Process p = pb.start();
        int exitCode = p.waitFor();
        return exitCode == 0;

    }

    private static void installXtraBackup() throws IOException, InterruptedException {

    }

    public static void PerformBackup(){

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

    private static boolean isFileExists(String filePath) {
        File file = new File(filePath);
        return file.exists();
    }
}
