package lbaena;

import lombok.Getter;
import lombok.Setter;

import java.sql.SQLException;

@Getter
@Setter
public class MariaDB extends BaseDadesRelacionals {

    public MariaDB() {
    }

    public MariaDB(String DB_PORT, String DB_NAME, String DB_USERNAME, String DB_PASSWORD, String DB_HOST, String DB_DRIVER) {
        this.DB_NAME = DB_NAME;
        this.DB_USER = DB_USERNAME;
        this.DB_PASSWD = DB_PASSWORD;
        this.DB_HOST = DB_HOST;
        this.DB_PORT = DB_PORT;
        this.DB_DRIVER = DB_DRIVER;
    }

    @Override
    protected String getStringConnexio() throws SQLException {
        return String.format("jdbc:mysql://%s:%s/%s?user=%s&password=%s",
                this.DB_HOST, this.DB_PORT, this.DB_NAME, this.DB_USER, this.DB_PASSWD);
    }

}
