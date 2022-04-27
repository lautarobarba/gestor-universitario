package config;

import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;

public class Database {

    public String getDatabaseUrl() {
        return "jdbc:sqlite:db/example.db";
    }

    public ConnectionSource getDatabaseConnection() throws SQLException {
        ConnectionSource connectionSource = new JdbcConnectionSource(databaseUrl);
        System.out.println("Conexión con database iniciada correctamente...");
        return connectionSource;
    }
}
