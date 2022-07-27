package views;

import java.util.Scanner;
import config.Database;
import controllers.UserController;
import models.User;
import java.sql.SQLException;

public class CLI {
    public static void main(String[] args) throws SQLException {

        // Base de datos
        Database database = Database.getInstance();
        database.openConnection();
        database.checkTables();

        // Usuario logueado
        User user = null;

        // Menus de pruebas
        Integer seleccion = 1;
        Scanner input = new Scanner(System.in);

        // Campos
        String email = null;
        String password = null;

        while(seleccion != 0){
            if(user == null){
                // Menu sin usuario
                System.out.println("== Ejecutando CLI [SIN USUARIO] ==");
                System.out.println("==  MENU  ==");
                System.out.println("1 - Iniciar sesión.");
                System.out.println();
                System.out.println("0 - Salir.");
                System.out.print(">> ");
            } else if (user.getRole().equals("ADMINISTRADOR")){
                // Menu de usuario ADMINISTRADOR
                System.out.println("== Ejecutando CLI ["+ user.getRole() + ": " +user.getName() +"] ==");
                System.out.println("==  MENU  ==");
                System.out.println("1 - Dar de alta un usuario.");
                System.out.println();
                System.out.println("0 - Salir.");
                System.out.print(">> ");
            } else if (user.getRole().equals("PROFESOR")){
                // Menu de usuario PROFESOR
                System.out.println("== Ejecutando CLI ["+ user.getRole() + ": " +user.getName() +"] ==");
                System.out.println("==  MENU  ==");
                System.out.println("2 - Dar de alta un usuario.");
                System.out.println();
                System.out.println("0 - Salir.");
                System.out.print(">> ");
            } else if (user.getRole().equals("ALUMNO")){
                // Menu de usuario ALUMNO
                System.out.println("== Ejecutando CLI ["+ user.getRole() + ": " +user.getName() +"] ==");
                System.out.println("==  MENU  ==");
                System.out.println("2 - Dar de alta un usuario.");
                System.out.println();
                System.out.println("0 - Salir.");
                System.out.print(">> ");
            }

            // Leo la opción ingresada
            seleccion = input.nextInt();
            input.nextLine();

            if(user == null) {
                switch (seleccion) {
                    case 1: // 1 - Iniciar sesión.
                        System.out.println("= Iniciar sesión =");
                        System.out.print("Email: ");
                        email =  input.nextLine();
                        System.out.print("Contraseña: ");
                        password =  input.nextLine();
                        user = User.iniciarSesion(email, password);
                        if(user == null){
                            System.out.println("ERROR AL INICIAR SESIÓN!!");
                        } else {
                            System.out.println("SESIÓN INICIADA");
                        }
                        break;
                    case 2: // 2 -
                        break;
                }
            }
        }

        //UserController.createUser("lau_mb94@hotmail.com", "HoLa","Lautaro Barba", User.ROLE_ADMINISTRADOR);
        database.closeConnection();
    }
}
