package main.presentationLogic;

import main.dataLogic.people.Manager;
import main.dbManagement.DataExtraction;
import main.dbManagement.DataValidation;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/** This JFrame allows the user to Log in.
 * @author Iker Villena Ona
 */

public class Login extends JFrame {

    private JPanel contentPane;
    private JTextField txtUsername;
    private JPasswordField pswPassword;
    private JButton btnLogIn;
    private JLabel lblLogIn;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Login frame = new Login();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public Login() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 800, 500);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        txtUsername = new JTextField();
        txtUsername.setFont(new Font("Tahoma", Font.PLAIN, 18));
        txtUsername.setHorizontalAlignment(SwingConstants.CENTER);
        txtUsername.setToolTipText("Nombre de usuario");
        txtUsername.setBounds(259, 173, 259, 49);
        contentPane.add(txtUsername);
        txtUsername.setColumns(10);

        pswPassword = new JPasswordField();
        pswPassword.setFont(new Font("Tahoma", Font.PLAIN, 18));
        pswPassword.setHorizontalAlignment(SwingConstants.CENTER);
        pswPassword.setBounds(259, 260, 259, 49);
        contentPane.add(pswPassword);

        btnLogIn = new JButton("Iniciar sesión");
        btnLogIn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(DataValidation.checkPassword(txtUsername.getText(),pswPassword.getText())){
                    Manager manager = DataExtraction.getManager(txtUsername.getText());
                    JOptionPane.showMessageDialog(null,"Contraseña correcta. Bienvenido "+manager.getName()+"!");
                    JFrame nextView = new Menu(manager);
                    nextView.setVisible(true);
                    Login.this.dispose();
                } else{
                    JOptionPane.showMessageDialog(null,"Contraseña incorrecta.");
                }
            }
        });
        btnLogIn.setBackground(SystemColor.activeCaptionBorder);
        btnLogIn.setFont(new Font("Tahoma", Font.PLAIN, 20));
        btnLogIn.setBounds(310, 361, 157, 39);
        contentPane.add(btnLogIn);

        lblLogIn = new JLabel("Log in");
        lblLogIn.setFont(new Font("Tahoma", Font.BOLD, 40));
        lblLogIn.setHorizontalAlignment(SwingConstants.CENTER);
        lblLogIn.setBounds(259, 74, 259, 49);
        contentPane.add(lblLogIn);
    }
}
