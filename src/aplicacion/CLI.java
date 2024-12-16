package aplicacion;

import java.util.List;
import java.util.Scanner;

import config.Database;
import clases.Usuario;
import clases.Carrera;


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

        // Campos para iniciar sesión
        String correo = null;
        String contrasena = null;

        while(seleccion != 0){
            if(usuario == null){
                // Menu sin usuario
                System.out.println("== Ejecutando CLI [SIN USUARIO] ==");
                System.out.println("==  MENU  ==");
                System.out.println("1 - Iniciar sesión");
                System.out.println();
                System.out.println("0 - Salir");
                System.out.print(">> ");
            } else if (usuario.getRol().equals(Usuario.ROL_ADMINISTRADOR)){
                // Menu de usuario ADMINISTRADOR
                System.out.println("== Ejecutando CLI ["+ usuario.getRol() + ": " + usuario.getNombre() +"] ==");
                System.out.println("==  MENU  ==");
                System.out.println("** Usuarios **");
                System.out.println("1 - Registrar usuario");
                System.out.println("2 - Listar usuarios");
                System.out.println("3 - Cerrar sesión");
                System.out.println("** Carreras **");
                System.out.println("4 - Crear");
                System.out.println("5 - Listar");
                System.out.println("6 - Eliminar");
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
                    case 1: // 1 - Iniciar sesión
                        System.out.println("= Iniciar sesión =");
                        System.out.print("Correo: ");
                        correo =  input.nextLine();
                        System.out.print("Contraseña: ");
                        contrasena =  input.nextLine();
                        usuario = Usuario.iniciarSesion(correo, contrasena);
                        if(usuario == null){
                            System.out.println("¡¡ERROR AL INICIAR SESIÓN!!");
                        } else {
                            System.out.println("SESIÓN INICIADA CORRECTAMENTE");
                        }
                        break;
                    default:
                        break;
                }
            } else if (usuario.getRol().equals(Usuario.ROL_ADMINISTRADOR)){
                switch (seleccion) {
                    case 1: // 1 - Registrar usuario
                        System.out.println("= Registrar usuario =");
                        System.out.print("Rol: ");
                        String rolUsuario =  input.nextLine();
                        System.out.print("Correo: ");
                        String correoUsuario =  input.nextLine();
                        System.out.print("Contraseña: ");
                        String contrasenaUsuario =  input.nextLine();
                        System.out.print("Nombre: ");
                        String nombreUsuario =  input.nextLine();
                        Usuario nuevoUsuario = Usuario.crearUsuario(correoUsuario, contrasenaUsuario, nombreUsuario, rolUsuario);
                        System.out.println("Usuario registrado correctamente");
                        break;
                    case 2: // 2 - Listar usuarios
                        System.out.println("= Lista de usuarios =");
                        List<Usuario> usuarios = Usuario.buscarTodos();
                        if(usuarios != null ){
                            for(int i=0; i<usuarios.toArray().length; i++){
                                System.out.println( (i+1) + ". " + usuarios.get(i).getNombre() + " (" + usuarios.get(i).getCorreo() + ")");
                            }
                        }
                        break;
                    case 3: // 3 - Cerrar sesión
                        System.out.println("= Cerrar sesión =");
                        System.out.println("Cerrando sesión...");
                        usuario = null;
                        break;
                    case 4: // 4 - Crear carrera
                        System.out.println("= Crear carrera =");
                        System.out.println("Nombre: ");
                        String nombreCarrera =  input.nextLine();
                        Carrera nuevaCarrera = Carrera.crearCarrera(nombreCarrera);
                        System.out.println("Carrera creada correctamente");
                        break;
                    case 5: // 5 - Listar carreras
                        System.out.println("= Lista de carreras =");
                        List<Carrera> carreras = Carrera.buscarTodos();
                        if(carreras != null ){
                            for(int i=0; i<carreras.toArray().length; i++){
                                System.out.println( (i+1) + ". " + carreras.get(i).getNombre());
                            }
                        }
                        break;
                    case 6: // 6 - Eliminar carrera
                        System.out.println("= Eliminar carrera =");
                        System.out.println("ID: ");
                        Integer idCarrera =  input.nextInt();
                        Carrera.eliminarCarrera(idCarrera);
                        System.out.println("Carrera eliminada correctamente");
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
