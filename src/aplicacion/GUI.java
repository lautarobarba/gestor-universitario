package aplicacion;

import clases.Usuario;
import config.Database;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

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
