package aplicacion;

import java.util.List;
import java.util.Scanner;
import config.Database;
import clases.Usuario;

import java.sql.SQLException;

public class CLI {
    public static void main(String[] args) throws SQLException {

        // Base de datos
        Database database = Database.getInstance();
        database.openConnection();
        database.checkTables();

        // Usuario logueado
        Usuario usuario = null;

        // Menus de pruebas
        int seleccion = 1;
        Scanner input = new Scanner(System.in);

        // Campos para registrar un usuario
        String correo = null;
        String contrasena = null;
        String nombre = null;
        String rol = null;

        while(seleccion != 0){
            if(usuario == null){
                // Menu sin usuario
                System.out.println("== Ejecutando CLI [SIN USUARIO] ==");
                System.out.println("==  MENU  ==");
                System.out.println("1 - Iniciar sesión.");
                System.out.println();
                System.out.println("0 - Salir.");
                System.out.print(">> ");
            } else if (usuario.getRol().equals(Usuario.ROL_ADMINISTRADOR)){
                // Menu de usuario ADMINISTRADOR
                System.out.println("== Ejecutando CLI ["+ usuario.getRol() + ": " + usuario.getNombre() +"] ==");
                System.out.println("==  MENU  ==");
                System.out.println("1 - Cerrar sesión.");
                System.out.println("2 - Registrar usuario.");
                System.out.println("3 - Listar usuarios.");
                System.out.println();
                System.out.println("0 - Salir.");
                System.out.print(">> ");
            } else if (usuario.getRol().equals(Usuario.ROL_PROFESOR)){
                // Menu de usuario PROFESOR
                System.out.println("== Ejecutando CLI ["+ usuario.getRol() + ": " + usuario.getNombre() +"] ==");
                System.out.println("==  MENU  ==");
                System.out.println("1 - Cerrar sesión.");
                System.out.println();
                System.out.println("0 - Salir.");
                System.out.print(">> ");
            } else if (usuario.getRol().equals(Usuario.ROL_ALUMNO)){
                // Menu de usuario ALUMNO
                System.out.println("== Ejecutando CLI ["+ usuario.getRol() + ": " + usuario.getNombre() +"] ==");
                System.out.println("==  MENU  ==");
                System.out.println("1 - Cerrar sesión.");
                System.out.println();
                System.out.println("0 - Salir.");
                System.out.print(">> ");
            } else {
                // Menu de usuario SIN ROL
                System.out.println("== Ejecutando CLI ["+ usuario.getRol() + ": " + usuario.getNombre() +"] ==");
                System.out.println("==  ¡¡ERROR USUARIO SIN ROL!!  ==");
                System.out.println();
                System.out.println("0 - Salir.");
                System.out.print(">> ");
            }

            // Leo la opción ingresada
            seleccion = input.nextInt();
            input.nextLine();

            if(usuario == null) {
                switch (seleccion) {
                    case 1: // 1 - Iniciar sesión.
                        System.out.println("= Iniciar sesión =");
                        System.out.print("Correo: ");
                        correo =  input.nextLine();
                        System.out.print("Contraseña: ");
                        contrasena =  input.nextLine();
                        usuario = Usuario.iniciarSesion(correo, contrasena);
                        if(usuario == null){
                            System.out.println("ERROR AL INICIAR SESIÓN!!");
                        } else {
                            System.out.println("SESIÓN INICIADA");
                        }
                        break;
                    case 2: // 2 -
                        break;
                }
            } else if (usuario.getRol().equals(Usuario.ROL_ADMINISTRADOR)){
                switch (seleccion) {
                    case 1: // 1 - Cerrar sesión.
                        System.out.println("= Cerrar sesión =");
                        System.out.println("Cerrando sesión...");
                        usuario = null;
                        break;
                    case 2: // 2 - Registrar usuario.
                        System.out.println("= Registrar usuario =");
                        System.out.print("Rol: ");
                        rol =  input.nextLine();
                        System.out.print("Correo: ");
                        correo =  input.nextLine();
                        System.out.print("Contraseña: ");
                        contrasena =  input.nextLine();
                        System.out.print("Nombre: ");
                        nombre =  input.nextLine();
                        usuario = Usuario.crearUsuario(correo, contrasena, nombre, rol);
                        break;
                    case 3: // 3 - Listar usuarios.
                        System.out.println("= Lista de usuarios =");
                        List<Usuario> usuarios = Usuario.buscarTodos();
                        if(usuarios != null ){
                            usuarios.forEach((Usuario usuarioAux) -> {
                                System.out.println(usuarioAux.toString());
                            });
                        }
                        break;
                }
            } else if (usuario.getRol().equals(Usuario.ROL_PROFESOR)){
                switch (seleccion) {
                    case 1: // 1 - Cerrar sesión.
                        System.out.println("= Cerrar sesión =");
                        System.out.println("Cerrando sesión...");
                        usuario = null;
                        break;
                    case 2: // 2 -
                        break;
                }
            } else if (usuario.getRol().equals(Usuario.ROL_ALUMNO)){
                switch (seleccion) {
                    case 1: // 1 - Cerrar sesión.
                        System.out.println("= Cerrar sesión =");
                        System.out.println("Cerrando sesión...");
                        usuario = null;
                        break;
                    case 2: // 2 -
                        break;
                }
            }
        }

        // Usuario.crearUsuario("administrador@gmail.com", "administrador", "Administrador Prueba", Usuario.ROL_ADMINISTRADOR);
        database.closeConnection();
    }
}
