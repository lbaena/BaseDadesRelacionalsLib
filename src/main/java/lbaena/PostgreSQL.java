package lbaena;

public class PostgreSQL extends BaseDadesRelacionals {

    private String driver = "org.postgresql.Driver";

    public PostgreSQL(String dbHost, String dbPort, String dbName, String dbUser, String dbPasswd) {
        super(dbHost, dbPort, dbName, dbUser, dbPasswd);
        this.DB_DRIVER = driver;
    }

    public PostgreSQL(String dbHost, String dbName, String dbUser, String dbPasswd) {
        this(dbHost, "5432", dbName, dbUser, dbPasswd);
    }

    @Override
    public String getStringConnexio() {
        return "jdbc:postgresql://" + DB_HOST + ":" + DB_PORT + "/" + DB_NAME;
    }
}
