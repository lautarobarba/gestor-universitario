package views;

import config.Database;
import controllers.UserController;
import models.User;

import java.sql.SQLException;

public class CLI {
    public static void main(String[] args) throws SQLException {
        System.out.println("Ejecutando CLI...");

        Database database = Database.getInstance();

        database.openConnection();
        database.checkTables();

        UserController.createUser("lau_mb94@hotmail.com", "HoLa","Lautaro Barba", User.ROLE_ADMINISTRADOR);
        database.closeConnection();
    }
}
