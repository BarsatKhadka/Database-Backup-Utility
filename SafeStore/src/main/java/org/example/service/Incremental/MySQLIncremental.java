package org.example.service.Incremental;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class MySQLIncremental {
    public void incremental(String user , String password , String databaseName) {
        String username = user;
        String pwd = password;

        try {
            isXtraBackupInstalled();
        } catch (IOException e) {
            System.out.println("XtraBackup is not installed in your system. Please manually install percona XtraBackup.");

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
            PerformIncrementalBackup(user,pwd , incrementalBackupDir+File.separator+folderName , databaseName);

        }
        else{
            System.out.println("Creating new folder on directory " + incrementalBackupDir + File.separator + folderName + " and storing your Incremental Backup File.");
            PerformFullBackup(user,pwd , incrementalBackupDir+File.separator+folderName , databaseName);
            System.out.println("Folder created and full backup is set for future incremental backup.!");

        }





    }

    private static boolean isXtraBackupInstalled() throws IOException, InterruptedException {
        ProcessBuilder pb = new ProcessBuilder("xtrabackup", "--version");
        Process p = pb.start();
        int exitCode = p.waitFor();
        return exitCode == 0;

    }

    public static void PerformFullBackup(String MYSQL_USER, String MYSQL_PASSWORD, String targetDir ,String database) {
        System.out.println("\nsudo: Enter your password: ");
        Scanner scanner = new Scanner(System.in);
        String sudoPassword = scanner.next();

        ProcessBuilder processBuilder = new ProcessBuilder();
        processBuilder.command("bash", "-c", "echo " + sudoPassword + " | sudo -S xtrabackup --backup --user=" + MYSQL_USER + " --password=" + MYSQL_PASSWORD + " --target-dir=" + targetDir +
                " --databases=" + database);


        System.out.println("Executing command: " + targetDir);

        Process process = null;
        try {
            process = processBuilder.start();
        } catch (IOException e) {
            throw new RuntimeException("Failed to start XtraBackup process: " + e.getMessage(), e);
        }

        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String line;
        try {
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to read process output: " + e.getMessage(), e);
        }

        BufferedReader errorReader = new BufferedReader(new InputStreamReader(process.getErrorStream()));
        String errorLine;
        try {
            while ((errorLine = errorReader.readLine()) != null) {
                System.err.println(errorLine);
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to read process error output: " + e.getMessage(), e);
        }

        int exitCode;
        try {
            exitCode = process.waitFor();
        } catch (InterruptedException e) {
            throw new RuntimeException("Process was interrupted: " + e.getMessage(), e);
        }

        if (exitCode != 0) {
            throw new RuntimeException("XtraBackup full backup process failed with exit code: " + exitCode);
        } else {
            System.out.println("Full backup completed successfully at: " + targetDir);
        }
    }

    public static void PerformIncrementalBackup(String MYSQL_USER , String MYSQL_PASSWORD , String targetDir , String database) {
        System.out.println("\nsudo: Enter your password: ");
        Scanner scanner = new Scanner(System.in);
        String sudoPassword = scanner.next();

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String nowFormatted = now.format(formatter).replace("-", "_");


        String incrementalDir = targetDir + File.separator + "incremental_" + nowFormatted;
        ProcessBuilder processBuilder = new ProcessBuilder();
        processBuilder.command("bash", "-c", "echo " + sudoPassword + " | sudo -S xtrabackup --backup " +
                "--user=" + MYSQL_USER + " " +
                "--password=" + MYSQL_PASSWORD + " " +
                "--target-dir=" + incrementalDir + " " +
                "--incremental-basedir=" + targetDir +
                " --databases=" + database);


        Process process = null;
        try {
            process = processBuilder.start();
            System.out.println("Executing command: " + targetDir + File.separator + "incremental " + nowFormatted);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String line;
        while (true) {
            try {
                if (!((line = reader.readLine()) != null)) break;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            System.out.println(line);
        }
        BufferedReader errorReader = new BufferedReader(new InputStreamReader(process.getErrorStream()));
        String errorLine;
        while (true) {
            try {
                if (!((errorLine = errorReader.readLine()) != null)) break;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            System.err.println(errorLine);
        }

        int exitCode = 0;
        try {
            exitCode = process.waitFor();
            System.out.println("Incremental backup completed successfully at: " + targetDir + File.separator + "incremental " + nowFormatted);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        if (exitCode != 0) {
            throw new RuntimeException("XtraBackup process failed with exit code: " + exitCode);
        }

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
