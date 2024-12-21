package clases;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import config.Database;

import java.sql.SQLException;
import java.util.List;

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
    private String documento;
    @DatabaseField()
    private String rol;

    // Constructor
    public Usuario() {
        // ORMLite needs a no-arg constructor
    }

    public Usuario(String correo, String contrasena, String nombre, String documento, String rol) {
        super();
        this.correo = correo;
        this.contrasena = contrasena;
        this.nombre = nombre;
        this.documento = documento;
        this.rol = rol;
    }

    @Override
    public String toString(){
        return this.nombre + " (" + this.documento + ")";
    }


    // Getters
    public int getId() {
        return this.id;
    }
    public String getCorreo() {
        return this.correo;
    }
    public String getContrasena() {
        return this.contrasena;
    }
    public String getNombre() {
        return this.nombre;
    }
    public String getDocumento() {
        return this.documento;
    }
    public String getRol() {
        return this.rol;
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
    public void setDocumento(String documento) {
        this.documento = documento;
    }
    public void setRol(String rol) {
        this.rol = rol;
    }


    // Métodos
    public static Usuario crearUsuario(String correo, String contrasena, String nombre, String documento, String rol){
        try{
            Database database = Database.getInstance();
            Dao<Usuario, String> usuarioDao = DaoManager.createDao(database.connection(), Usuario.class);
            Usuario usuario = new Usuario(correo, contrasena, nombre, documento, rol);
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
            Usuario usuario = usuarioDao.queryBuilder().where().eq("correo", correo).and().eq("contrasena", contrasena).queryForFirst();
            return usuario;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<Usuario> buscarTodos(){
        try{
            Database database = Database.getInstance();
            Dao<Usuario, String> usuarioDao = DaoManager.createDao(database.connection(), Usuario.class);
            List<Usuario> usuarios = usuarioDao.queryBuilder().query();
            return usuarios;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}