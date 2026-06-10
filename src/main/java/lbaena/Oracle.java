package lbaena;

import lbaena.BaseDadesRelacionals;

public class Oracle extends BaseDadesRelacionals {

    public enum DriverType {
        SID,
        SERVICE_NAME
    }

    private String driver = "oracle.jdbc.OracleDriver";
    private String DB_DRIVER_TYPE = DriverType.SERVICE_NAME.name();

    public OracleDB(String dbHost, String dbPort, String dbName, String dbUser, String dbPasswd, DriverType driverType) {
        super(dbHost, dbPort, dbName, dbUser, dbPasswd);
        this.DB_DRIVER = driver;
        this.DB_DRIVER_TYPE = driverType.name();
    }

    public OracleDB(String dbHost, String dbName, String dbUser, String dbPasswd) {
        this(dbHost, "1521", dbName, dbUser, dbPasswd, DriverType.SERVICE_NAME);
    }

    public String getDB_DRIVER_TYPE() {
        return DB_DRIVER_TYPE;
    }

    @Override
    public String getStringConnexio() {
        if (DriverType.SID.name().equals(DB_DRIVER_TYPE)) {
            return "jdbc:oracle:thin:@" + DB_HOST + ":" + DB_PORT + ":" + DB_NAME;
        } else {
            return "jdbc:oracle:thin:@//" + DB_HOST + ":" + DB_PORT + "/" + DB_NAME;
        }
    }
}
