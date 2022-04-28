package models;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

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
}