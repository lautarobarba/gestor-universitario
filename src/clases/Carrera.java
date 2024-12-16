package clases;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import config.Database;

import java.sql.SQLException;
import java.util.List;

@DatabaseTable(tableName = "carreras")
public class Carrera {
    // Atributos
    @DatabaseField(generatedId = true)
    private int id;
    @DatabaseField(unique = true)
    private String nombre;

    // Constructor
    public Carrera() {
        // ORMLite needs a no-arg constructor
    }

    public Carrera(String nombre) {
        super();
        this.nombre = nombre;
    }

    @Override
    public String toString(){
        return nombre;
    }

    // Getters
    public int getId() {
        return id;
    }
    public String getNombre() {
        return nombre;
    }


    // Setters
    public void setNombre(String name) {
        this.nombre = nombre;
    }


    public static List<Carrera> buscarTodos(){
        try{
            Database database = Database.getInstance();
            Dao<Carrera, String> carreraDao = DaoManager.createDao(database.connection(), Carrera.class);
            List<Carrera> carreras = carreraDao.queryBuilder().query();
            return carreras;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}