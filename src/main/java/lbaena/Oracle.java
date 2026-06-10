package lbaena;

import java.sql.SQLException;

public class Oracle extends BaseDadesRelacionals {

    @Override
    protected String getStringConnexio() throws SQLException {
        return String.format("jdbc:oracle:thin:@%s:%s:%s", this.DB_HOST, this.DB_PORT, this.DB_NAME) +
               "?user=" + this.DB_USER + "&password=" + this.DB_PASSWD;
    }

}
