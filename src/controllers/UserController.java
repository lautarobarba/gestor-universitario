package controllers;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import config.Database;
import clases.User;

import java.sql.SQLException;

public class UserController{

    //private Database database = Database.getInstance();


    public static User createUser(String email, String password, String name, String role){
        try{
            Database database = Database.getInstance();
            Dao<User, String> userDao = DaoManager.createDao(database.connection(), User.class);
            User user = new User(email, password, name, role);
            userDao.create(user);
            return user;
        } catch (SQLException e) {
            System.out.println("Error al crear usuario...");
            e.printStackTrace();
        }
        return null;
    }
}
