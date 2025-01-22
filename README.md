# SafeStore-DBMS

A command-line interface (CLI) utility for backing up and restoring various types of databases. This tool supports MySQL, PostgreSQL, MongoDB, SQLite, and other databases. It features automatic backup scheduling, compression, local/cloud storage options, and logging.

---

## Features

- **Supported Databases**: MySQL, PostgreSQL, MongoDB, SQLite, and more.
- **Backup Types**: Full, incremental, and differential backups.
- **Compression**: Backup files are compressed to save storage space.
- **Storage Options**:
    - Local storage.
    - Cloud storage (AWS S3, Google Cloud Storage, Azure Blob Storage).
- **Logging**: Detailed logs of backup activities.
- **Notifications**: Optional Slack notifications on backup completion.
- **Restore**: Restore databases from backup files with selective restoration options.

---

## Installation

### Prerequisites
- Java 17 or higher.
- Maven (for building the project).
- Database drivers (if not using default ones).
- Cloud storage credentials (if using cloud storage).

### Steps
1. Clone the repository:
   ```bash
   git clone https://github.com/barsatKhadka/SafeStore-DBMS.git
   cd SafeStore-DBMS
   ```

Run the Utility

To run the utility, use:

```java -jar target/SafeStore-DBMS.jar```

## Usage

***The utility will prompt you for all required inputs. No command-line arguments are needed.***
### 1. Backup a Database

Run the utility:

```java -jar target/database-backup-utility.jar```

Follow the prompts:

    Select the database type (e.g., MySQL, PostgreSQL, MongoDB).
    Enter connection details (host, port, username, password, database name).
    Choose the backup type (full, incremental, or differential).
    Select storage option (local or cloud).
    If using cloud storage, provide cloud credentials (e.g., AWS access key, Google Cloud credentials).

The backup will be created and stored in the specified location. Logs will be saved in the logs/ directory.
### 2. Restore a Database

1) Run the utility:

```java -jar target/database-backup-utility.jar```

2) Select the "Restore" option and follow the prompts:

    Provide the path to the backup file.
    Choose whether to perform a full restore or selective restore (if supported by the DBMS).

3. Schedule Backups

Run the utility and select the "Schedule Backup" option. Then, configure the backup schedule (e.g., daily, weekly) and provide the necessary details (database connection, backup type, storage location).
4. View Logs

Logs are stored in the logs/ directory. Each log file contains details such as start time, end time, status, and errors (if any).
Configuration
Cloud Storage Credentials

    AWS S3: Provide accessKey and secretKey.
    Google Cloud Storage: Provide the path to your service account JSON file.
    Azure Blob Storage: Provide connectionString and containerName.

### 3. Slack Notifications

To enable Slack notifications:

    Create a Slack webhook URL.
    Set the SLACK_WEBHOOK_URL environment variable or provide it during runtime.

Examples
Backup a MySQL Database

    Run the utility:

    java -jar target/database-backup-utility.jar

    Select "MySQL" as the database type.

    Enter connection details:
        Host: localhost
        Port: 3306
        Username: root
        Password: password
        Database: mydatabase

    Choose "Full Backup" and "Local Storage".

The backup will be saved as mydatabase_full_backup_<timestamp>.zip.
Restore a PostgreSQL Database

    Run the utility:

    java -jar target/database-backup-utility.jar

    Select "Restore".

    Provide the path to the backup file: /backups/mydatabase_full_backup_20231001.zip.

    Choose "Full Restore".

The database will be restored from the backup.
Directory Structure
```
database-backup-utility/
├── src/                     # Source code
├── logs/                    # Log files
├── backups/                 # Default backup directory
├── target/                  # Compiled JAR file
├── pom.xml                  # Maven configuration
└── README.md                # This file
```
### Dependencies

    Database Drivers:
        MySQL: mysql-connector-java
        PostgreSQL: postgresql
        MongoDB: mongodb-driver-sync
        SQLite: sqlite-jdbc
    Compression: zip4j
    Cloud Storage:
        AWS S3: aws-java-sdk-s3
        Google Cloud Storage: google-cloud-storage
        Azure Blob Storage: azure-storage-blob
    Logging: log4j
    Notifications: slack-api-client

### Contributing

Contributions are welcome! Please follow these steps:

    Fork the repository.
    Create a new branch for your feature or bugfix.
    Submit a pull request.

## License

This project is licensed under the MIT License. See the LICENSE file for details.
Support

For any issues or questions, please open an issue on the GitHub repository.

