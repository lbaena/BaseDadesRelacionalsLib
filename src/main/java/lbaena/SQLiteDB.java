package lbaena;

import java.io.File;

public class SQLiteDB extends BaseDadesRelacionals {
    private String DB_DIRECTORY;

    private String driver = "org.sqlite.JDBC";

    public SQLiteDB(String dbDirectory, String dbName) {
        super("localhost", "0", dbName, "", "");
        this.DB_DIRECTORY = dbDirectory;
        this.DB_DRIVER = driver;
    }

    public SQLiteDB(String dbName) {
        this(System.getProperty("user.dir"), dbName);
    }

    public String getDB_DIRECTORY() {
        return DB_DIRECTORY;
    }

    @Override
    public String getStringConnexio() {
        String fileName = DB_NAME.endsWith(".db") ? DB_NAME : DB_NAME + ".db";
        return "jdbc:sqlite:" + DB_DIRECTORY + File.separator + fileName;
    }

    @Override
    public java.sql.Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                Class.forName(DB_DRIVER);
                File dir = new File(DB_DIRECTORY);
                if (!dir.exists()) {
                    dir.mkdirs();
                }
                connection = java.sql.DriverManager.getConnection(getStringConnexio());
            }
        } catch (ClassNotFoundException e) {
            System.err.println("Driver SQLite no trobat: " + e.getMessage());
            return null;
        } catch (java.sql.SQLException e) {
            System.err.println("Error de connexió SQLite: " + e.getMessage());
            return null;
        }
        return connection;
    }
}
