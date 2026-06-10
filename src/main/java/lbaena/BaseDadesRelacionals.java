package lepsima;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.beans.property.SimpleStringProperty;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public abstract class BaseDadesRelacionals {
    protected String DB_PASSWD;
    protected String DB_DRIVER;
    protected String DB_PORT;
    protected String DB_USER;
    protected String DB_NAME;
    protected String DB_HOST;

    protected Connection connection;

    protected BaseDadesRelacionals(String dbHost, String dbPort, String dbName,
                                   String dbUser, String dbPasswd) {
        this.DB_HOST   = dbHost;
        this.DB_PORT   = dbPort;
        this.DB_NAME   = dbName;
        this.DB_USER   = dbUser;
        this.DB_PASSWD = dbPasswd;
    }

    protected abstract String getStringConnexio();

    public String getDB_USER() {
        return DB_USER;
    }
    public String getDB_NAME() {
        return DB_NAME;
    }
    public String getDB_HOST() {
        return DB_HOST;
    }

    public Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                Class.forName(DB_DRIVER);
                connection = DriverManager.getConnection(
                        getStringConnexio(), DB_USER, DB_PASSWD);
            }
        } catch (ClassNotFoundException e) {
            System.err.println("Driver no trobat: " + e.getMessage());
            return null;
        } catch (SQLException e) {
            System.err.println("Error de connexió: " + e.getMessage());
            return null;
        }
        return connection;
    }

    public void executaSQL(String sql) {
        Connection conn = getConnection();
        if (conn == null) return;
        try (Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            System.err.println("Error executant SQL: " + e.getMessage());
        }
    }

    public ResultSet executaSQLSelect(String sql) {
        Connection conn = getConnection();
        if (conn == null) return null;
        try {
            Statement stmt = conn.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            return stmt.executeQuery(sql);
        } catch (SQLException e) {
            System.err.println("Error executant SELECT: " + e.getMessage());
            return null;
        }
    }

    @SuppressWarnings("unchecked")
    public TableView<List<String>> obteDadesTableView(String sql, TableView<List<String>> tableView) {
        ResultSet rs = executaSQLSelect(sql);
        if (rs == null) return tableView;
        return obteDadesTableView(rs, tableView);
    }

    @SuppressWarnings("unchecked")
    public TableView<List<String>> obteDadesTableView(ResultSet rs, TableView<List<String>> tableView) {
        tableView.getItems().clear();
        tableView.getColumns().clear();

        try {
            ResultSetMetaData meta = rs.getMetaData();
            int numCols = meta.getColumnCount();

            // Crear columnes dinàmicament
            for (int i = 1; i <= numCols; i++) {
                final int colIndex = i - 1;
                TableColumn<List<String>, String> col =
                        new TableColumn<>(meta.getColumnLabel(i));
                col.setCellValueFactory(param ->
                        new SimpleStringProperty(param.getValue().get(colIndex)));
                tableView.getColumns().add(col);
            }

            // Afegir files
            while (rs.next()) {
                List<String> fila = new ArrayList<>();
                for (int i = 1; i <= numCols; i++) {
                    String valor = rs.getString(i);
                    fila.add(valor != null ? valor : "");
                }
                tableView.getItems().add(fila);
            }
            rs.close();

        } catch (SQLException e) {
            System.err.println("Error omplint TableView: " + e.getMessage());
        }
        return tableView;
    }

    public void tancaConnexio() {
        if (connection != null) {
            try {
                if (!connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException e) {
                System.err.println("Error tancant connexió: " + e.getMessage());
            } finally {
                connection = null;
            }
        }
    }
}
