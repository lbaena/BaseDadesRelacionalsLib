package lbaena;

import lombok.Getter;
import lombok.Setter;

import java.sql.SQLException;

@Getter
@Setter
public class SQLiteDB extends BaseDadesRelacionals {

    protected String DB_DIRECTORY;

    @Override
    protected String getStringConnexio() throws SQLException {
        return "jdbc:sqlite:" + this.DB_DIRECTORY + "/" + this.DB_NAME;
    }

}
