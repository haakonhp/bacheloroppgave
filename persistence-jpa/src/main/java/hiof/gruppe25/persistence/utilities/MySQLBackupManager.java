package hiof.gruppe25.persistence.utilities;

import hiof.gruppe25.core.persistenceinterfaces.iBackupManager;
import hiof.gruppe25.persistence.configuration.ConfigLoader;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;
import java.util.Properties;

public class MySQLBackupManager implements iBackupManager {
    private final String host;
    private final String port;
    private final String database;
    private final String username;
    private final String password;

    public MySQLBackupManager() {
        this(Objects.requireNonNull(MySQLBackupManager.class.getClassLoader()
                .getResource("configs/backupProfile.config")).getPath());
    }

    public MySQLBackupManager(String resourcePath) {
        if (System.getenv("BACKUP_HOST") != null) {
            host = System.getenv("BACKUP_HOST");
            port = System.getenv("BACKUP_PORT");
            database = System.getenv("BACKUP_DATABASE");
            username = System.getenv("BACKUP_USERNAME");
            password = System.getenv("BACKUP_PASSWORD");
            return;
        }
        try {
            Properties properties = ConfigLoader.loadProperties(resourcePath);

            host = Objects.requireNonNull(properties).getProperty("backup.host", "");
            port = Objects.requireNonNull(properties).getProperty("backup.port", "");
            database = Objects.requireNonNull(properties).getProperty("backup.database", "");
            username = Objects.requireNonNull(properties).getProperty("backup.username", "");
            password = Objects.requireNonNull(properties).getProperty("backup.password", "");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    private Path constructBackup() {
        try {
            Path tempFile = Files.createTempFile("bachelorreadfile-", ".tmp");

            ProcessBuilder builder = new ProcessBuilder();

            Path extras = createExtrasfile();

            builder.command("mysqldump", "--defaults-file=" + extras, "-h", host, "-P", port, database);
            builder.redirectOutput(new File(tempFile.toString()));

            Process process = builder.start();
            process.waitFor();
            Files.delete(extras);

            if (process.exitValue() == 0) {
                return tempFile;
            }
        } catch (IOException | InterruptedException e) {
            return null;
        }
        return null;
    }

    public byte[] createBackup() {
        Path result;
        if ((result = constructBackup()) != null) {
            try {
                byte[] file = Files.readAllBytes(result);
                Files.delete(result);
                return file;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return null;
    }

    public Path createExtrasfile() {
        try {
            String extrasFile = "[client] \n" +
                    String.format("user=%s\n", username) +
                    String.format("password=%s\n", password);

            Path tempFile = Files.createTempFile("extrasfile-", ".tmp");
            Files.write(tempFile, extrasFile.getBytes());

            return tempFile;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public boolean applyBackup(byte[] toWrite) {
        try {
            Path tempFile = Files.createTempFile("bachelorwritefile-", ".tmp");
            Files.write(tempFile, toWrite);
            Path extras = createExtrasfile();

            ProcessBuilder builder = new ProcessBuilder();
            builder.command("mysql", "--defaults-extra-file=" + extras, "-h", host, "-P", port, database);
            builder.redirectInput(new File(tempFile.toString()));
            Process process = builder.start();

            process.waitFor();

            Files.delete(tempFile);
            Files.delete(extras);

            return process.exitValue() == 0;
        } catch (IOException | InterruptedException e) {
            return false;
        }
    }
}



