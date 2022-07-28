package config;

import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import clases.User;

import java.sql.SQLException;

public class Database {

    private static Database singleton = null;
    private static ConnectionSource connection = null;

    private Database(){
    }

    public String getDatabaseUrl() {
        return "jdbc:sqlite:src/db/gestor_universitario.db";
    }

    public static Database getInstance() throws SQLException {
        if(singleton == null){
            singleton = new Database();
        }
        return singleton;
    }

    public boolean openConnection(){
        try {
            connection = new JdbcConnectionSource(this.getDatabaseUrl());
            System.out.println("Conexión con base de datos iniciada correctamente...");
            return true;
        } catch (SQLException e){
            System.out.println("Error al iniciar conexión con base de datos...");
            e.printStackTrace();
            return false;
        }
    }

    public ConnectionSource connection(){
        return connection;
    }

    public boolean closeConnection(){
        try {
            connection.close();
            System.out.println("Conexión con base de datos finalizada correctamente...");
            return true;
        } catch (Exception e) {
            System.out.println("Error al finalizar conexión con base de datos...");
            e.printStackTrace();
            return false;
        }
    }

    public boolean checkTables(){
        System.out.println("Controlando tablas existentes...");

        try {
            TableUtils.createTableIfNotExists(connection, User.class);
        } catch (SQLException e) {
            System.out.println("Error al crear tabla users...");
            e.printStackTrace();
            return false;
        }

        return true;
    }
}
