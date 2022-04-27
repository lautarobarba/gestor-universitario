package config;

import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;

public class Database {
    private static ConnectionSource connection = null;

    public String getDatabaseUrl() {
        return "jdbc:sqlite:db/example.db";
    }

    public ConnectionSource getInstance() throws SQLException {
        if(connection == null){
            connection = new JdbcConnectionSource(this.getDatabaseUrl());
            System.out.println("Conexión con database iniciada correctamente...");
        }
        return connection;
    }

    public boolean closeConnection(){
        try {
            connection.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean checkTables() {
        //this.
        System.out.println("Controlando tablas existentes...");
        return true;
    }
}
