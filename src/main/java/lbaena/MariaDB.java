package lbaena;

public class MariaDB extends BaseDadesRelacionals {

    private String driver = "org.mariadb.jdbc.Driver";

    public MariaDB(String dbHost, String dbPort, String dbName, String dbUser, String dbPasswd) {
        super(dbHost, dbPort, dbName, dbUser, dbPasswd);
        this.DB_DRIVER = driver;
    }

    public MariaDB(String dbHost, String dbName, String dbUser, String dbPasswd) {
        this(dbHost, "3306", dbName, dbUser, dbPasswd);
    }

    @Override
    public String getStringConnexio() {
        return "jdbc:mariadb://" + DB_HOST + ":" + DB_PORT + "/" + DB_NAME;
    }
}
