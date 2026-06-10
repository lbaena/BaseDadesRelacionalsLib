package lbaena;

import lombok.Getter;
import lombok.Setter;

import java.sql.SQLException;

@Getter
@Setter
public class PostgreSQL extends BaseDadesRelacionals {

    @Override
    protected String getStringConnexio() throws SQLException {
        return String.format("jdbc:postgresql://%s:%s/%s?user=%s&password=%s",
                this.DB_HOST, this.DB_PORT, this.DB_NAME, this.DB_USER, this.DB_PASSWD);
    }

}
