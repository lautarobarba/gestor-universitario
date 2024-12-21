package aplicacion;

import clases.Carrera;
import clases.Usuario;
import config.Database;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

public class GUI extends JFrame {
    // Usuario logueado
    Usuario usuario = null;

    private JPanel mainLayout;
    private JLabel appTitle;
    private JPanel screenLayout;
    private JPanel loginScreen;
    private JTextField emailField;
    private JButton ingresarButton;
    private JPasswordField passwordField;
    private JLabel errorLabel;
    private JPanel adminDashboard;
    private JPanel teacherDashboard;
    private JPanel studentDashboard;
    private JLabel userLogged;
    private JPanel userStatusBar;
    private JButton SALIRButton;
    private JButton crearUsuarioButton;
    private JButton crearCarreraButton;
    private JButton listarCarreraButton;
    private JPanel crearUsuarioPanel;
    private JComboBox<String> rolNuevoUsuarioField;
    private JTextField nombreNuevoUsuarioField;
    private JTextField correoNuevoUsuarioField;
    private JPasswordField contrasenaNuevoUsuarioField;
    private JButton crearUsuarioConfirmarButton;
    private JButton listarUsuariosButton;
    private JPanel listarUsuariosPanel;
    private JList listaUsuarios;
    private JButton volverListaUsuariosButton;
    private JLabel carrerasTitle;
    private JLabel usuariosTitle;
    private JButton crearUsuarioCancelarButton;
    private JLabel successLabel;
    private JPanel crearCarreraPanel;
    private JTextField nombreNuevaCarreraField;
    private JLabel nombreNuevaCarreraLabel;
    private JButton crearCarreraCancelarButton;
    private JButton crearCarreraConfirmarButton;

    public GUI(String title) {
        // SuperClass Constructor
        super(title);

        // Botón salir inicia oculto
        SALIRButton.setVisible(false);

        // Settings
        this.setContentPane(mainLayout);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setSize(1366, 768);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setVisible(true);

        ingresarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                errorLabel.setText("");
                String email = emailField.getText();
                String password = String.valueOf(passwordField.getPassword());
                System.out.println(email);
                System.out.println(password);
                if(!email.isEmpty() && !password.isEmpty()){
                    usuario = Usuario.iniciarSesion(email, password);
                    if (usuario == null) {
                        // Si el usuario no pudo iniciar sesión
                        errorLabel.setText("Error al iniciar sesión");
                    } else {
                        // Si el usuario pudo iniciar sesión correctamente
                        SALIRButton.setVisible(true);

                        // Cambio de panel(ventana)
                        loginScreen.setVisible(false);
                        switch (usuario.getRol()) {
                            case Usuario.ROL_ADMINISTRADOR:
                                adminDashboard.setVisible(true);
                                break;
                            case Usuario.ROL_PROFESOR:
                                teacherDashboard.setVisible(true);
                                break;
                            case Usuario.ROL_ALUMNO:
                                studentDashboard.setVisible(true);
                                break;
                        }
                        userLogged.setText(usuario.getRol() + ": " + usuario.getNombre());
                    }
                }
            }
        });
        SALIRButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                usuario = null;
                userLogged.setText("");
                SALIRButton.setVisible(false);
                loginScreen.setVisible(true);
            }
        });

        // Admin Dashboard - Usuarios
        crearUsuarioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adminDashboard.setVisible(false);
                crearUsuarioPanel.setVisible(true);
            }
        });
        crearUsuarioConfirmarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String rol = rolNuevoUsuarioField.getSelectedItem().toString();
                String correo = correoNuevoUsuarioField.getText();
                String nombre = nombreNuevoUsuarioField.getText();
                String contrasena = String.valueOf(contrasenaNuevoUsuarioField.getPassword());
                if(
                    rol.isEmpty() || rol.isBlank() ||
                    correo.isEmpty() || correo.isBlank() ||
                    nombre.isEmpty() || nombre.isBlank() ||
                    contrasena.isEmpty() || contrasena.isBlank()
                ){
                    successLabel.setText("");
                    errorLabel.setText("Falta ingresar alguno de los parámetros solicitados");
                } else {
                    Usuario.crearUsuario(correo, contrasena, nombre, rol);
                    correoNuevoUsuarioField.setText("");
                    nombreNuevoUsuarioField.setText("");
                    contrasenaNuevoUsuarioField.setText("");
                    crearUsuarioPanel.setVisible(false);
                    adminDashboard.setVisible(true);
                    successLabel.setText("Usuario registrado correctamente");
                    errorLabel.setText("");
                }
            }
        });
        crearUsuarioCancelarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                crearUsuarioPanel.setVisible(false);
                adminDashboard.setVisible(true);
            }
        });

        listarUsuariosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adminDashboard.setVisible(false);
                List<Usuario> usuarios = Usuario.buscarTodos();
                DefaultListModel<String> listUsuariosModel = new DefaultListModel<>();
                if(usuarios != null){
                    for(int i=0; i<usuarios.toArray().length; i++){
                        listUsuariosModel.addElement((i+1) + ". " + usuarios.get(i).getNombre() + "[" + usuarios.get(i).getRol() + "] (" + usuarios.get(i).getCorreo() + ")");
                    }
                    listaUsuarios.setModel(listUsuariosModel);
                }
                listarUsuariosPanel.setVisible(true);
            }
        });
        volverListaUsuariosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listarUsuariosPanel.setVisible(false);
                adminDashboard.setVisible(true);
            }
        });

        // Admin Dashboard - Carreras
        crearCarreraButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adminDashboard.setVisible(false);
                crearCarreraPanel.setVisible(true);
            }
        });
        crearCarreraConfirmarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombre = nombreNuevaCarreraField.getText();
                if( nombre.isEmpty() || nombre.isBlank() ){
                    successLabel.setText("");
                    errorLabel.setText("Falta ingresar alguno de los parámetros solicitados");
                } else {
                    Carrera.crearCarrera(nombre);
                    nombreNuevaCarreraField.setText("");
                    crearCarreraPanel.setVisible(false);
                    adminDashboard.setVisible(true);
                    successLabel.setText("Carrera creada correctamente");
                    errorLabel.setText("");
                }
            }
        });
        crearCarreraCancelarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                crearCarreraPanel.setVisible(false);
                adminDashboard.setVisible(true);
            }
        });



    }

    // Main
    public static void main(String[] args) throws SQLException {
        // Base de datos
        Database database = Database.getInstance();
        database.openConnection();
        database.checkTables();

        System.out.println("Ejecutando GUI...");
        JFrame frame = new GUI("Gestor Universitario - Lautaro Barba");
    }
}
