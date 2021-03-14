import javax.swing.*;

public class GUI extends JFrame {
    private JPanel mainPanel;
    private JPanel loginScreen;

     public GUI(String title) {
         // SuperClass Constructor
         super(title);

         // Settings
         this.setContentPane(mainPanel);
         this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         this.pack();
         this.setSize(300, 100);
         this.setResizable(false);
         this.setLocationRelativeTo(null);
         this.setVisible(true);
     }

    // Main
    public static void main(String[] args) {
        JFrame frame = new GUI("Multiple Windows");
    }
}
