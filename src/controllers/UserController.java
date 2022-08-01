package controllers;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import config.Database;
import clases.Usuario;

import java.sql.SQLException;

public class UserController{

    //private Database database = Database.getInstance();


    public static Usuario createUser(String email, String password, String name, String role){
        try{
            Database database = Database.getInstance();
            Dao<Usuario, String> userDao = DaoManager.createDao(database.connection(), Usuario.class);
            Usuario usuario = new Usuario(email, password, name, role);
            userDao.create(usuario);
            return usuario;
        } catch (SQLException e) {
            System.out.println("Error al crear usuario...");
            e.printStackTrace();
        }
        return null;
    }
}
