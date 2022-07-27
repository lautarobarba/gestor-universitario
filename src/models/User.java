package models;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import config.Database;

import java.sql.SQLException;
import java.util.List;

@DatabaseTable(tableName = "users")
public class User {
    // Roles
    public static final String ROLE_ADMINISTRADOR = "ADMINISTRADOR";
    public static final String ROLE_PROFESOR = "PROFESOR";
    public static final String ROLE_ALUMNO = "ALUMNO";

    // Atributos
    @DatabaseField(generatedId = true)
    private int id;
    @DatabaseField(unique = true)
    private String email;
    @DatabaseField()
    private String password;
    @DatabaseField()
    private String name;
    @DatabaseField()
    private String role;

    // Constructor
    public User() {
        // ORMLite needs a no-arg constructor
    }

    public User(String email, String password, String name, String role) {
        super();
        this.email = email;
        this.password = password;
        this.name = name;
        this.role = role;
    }

    @Override
    public String toString(){
        return name;
    }

    // Getters
    public int getId() {
        return id;
    }
    public String getEmail() {
        return email;
    }
    public String getPassword() {
        return password;
    }
    public String getName() {
        return name;
    }
    public String getRole() {
        return role;
    }

    // Setters
    public void setEmail(String email) {
        this.email = email;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setRole(String role) {
        this.role = role;
    }

    // Métodos
    public static User iniciarSesion(String email, String password){
        try{
            Database database = Database.getInstance();
            Dao<User, String> userDao = DaoManager.createDao(database.connection(), User.class);
            // List<User> users = userDao.queryBuilder().where().eq("email", email).query();
            User user = userDao.queryBuilder().where().eq("email", email).and().eq("password", password).queryForFirst();
            return user;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}