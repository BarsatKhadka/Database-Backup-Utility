package org.example.service;

public class DbToRestore {

    public static void DBToRestore(String databaseName ){
        String osname = System.getProperty("os.name");
        System.out.println("OS: " + osname);
        System.out.println("Restoring database " + databaseName);

    }



}
