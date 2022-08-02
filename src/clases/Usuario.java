package clases;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import config.Database;

import java.sql.SQLException;

@DatabaseTable(tableName = "usuarios")
public class Usuario {
    // Roles
    public static final String ROL_ADMINISTRADOR = "ADMINISTRADOR";
    public static final String ROL_PROFESOR = "PROFESOR";
    public static final String ROL_ALUMNO = "ALUMNO";

    // Atributos
    @DatabaseField(generatedId = true)
    private int id;
    @DatabaseField(unique = true)
    private String correo;
    @DatabaseField()
    private String contrasena;
    @DatabaseField()
    private String nombre;
    @DatabaseField()
    private String rol;

    // Constructor
    public Usuario() {
        // ORMLite needs a no-arg constructor
    }

    public Usuario(String correo, String contrasena, String nombre, String rol) {
        super();
        this.correo = correo;
        this.contrasena = contrasena;
        this.nombre = nombre;
        this.rol = rol;
    }

    @Override
    public String toString(){
        return nombre;
    }

    // Getters
    public int getId() {
        return id;
    }
    public String getCorreo() {
        return correo;
    }
    public String getContrasena() {
        return contrasena;
    }
    public String getNombre() {
        return nombre;
    }
    public String getRol() {
        return rol;
    }

    // Setters
    public void setCorreo(String correo) {
        this.correo = correo;
    }
    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }
    public void setNombre(String name) {
        this.nombre = nombre;
    }
    public void setRol(String rol) {
        this.rol = rol;
    }

    // Métodos
    public static Usuario crearUsuario(String correo, String contrasena, String nombre, String rol){
        try{
            Database database = Database.getInstance();
            Dao<Usuario, String> usuarioDao = DaoManager.createDao(database.connection(), Usuario.class);
            Usuario usuario = new Usuario(correo, contrasena, nombre, rol);
            usuarioDao.create(usuario);
            return usuario;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Usuario iniciarSesion(String correo, String contrasena){
        try{
            Database database = Database.getInstance();
            Dao<Usuario, String> usuarioDao = DaoManager.createDao(database.connection(), Usuario.class);
            // List<User> users = userDao.queryBuilder().where().eq("correo", correo).query();
            Usuario usuario = usuarioDao.queryBuilder().where().eq("correo", correo).and().eq("contrasena", contrasena).queryForFirst();
            return usuario;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}