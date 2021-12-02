package main.presentationLogic;

import main.dataLogic.people.Administrator;
import main.dataLogic.people.Manager;
import main.dataLogic.people.User;
import main.dbManagement.DataExtraction;
import main.presentationLogic.administrator.LoadMatches;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/** This JFrame allows the user to Log in.
 * @author Iker Villena Ona
 */

public class Login extends JFrame {

    private ArrayList<User> usersList;
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
        usersList = getAllUsers();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 800, 500);
        setLocationRelativeTo(null);
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
                User user = getUser(txtUsername.getText(),pswPassword.getText());
                if(user != null){
                    JOptionPane.showMessageDialog(null, user.getLoginText());
                    if(user instanceof Manager){
                        goToView(new Menu((Manager) user));
                    }else{
                        goToView(new LoadMatches());
                    }
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

    /**Provides a list with all the users registered in the Database (including managers and administrators).
     * @return ArrayList<User> with the list of users.
     */

    private ArrayList<User> getAllUsers(){
        ArrayList<User> usersList = new ArrayList<>();
        for(Manager m : DataExtraction.getAllManagers()){
            usersList.add(m);
        }
        for(Administrator a : DataExtraction.getAllAdministrators()){
            usersList.add(a);
        }
        return usersList;
    }

    /**Provides a User whose username and password are the same as the ones provided as a parameter.
     * @param username String with the username.
     * @param password String with the password.
     * @return A User who has the same username and password as the ones provided as a parameter, or the value null if
     * none of the users have this username and password.
     */

    private User getUser(String username, String password){
        User user = null;
        for(User u : usersList){
            if(u.getUsername().equals(username) && u.getPassword().equals(password)){
                user = u;
                break;
            }
        }
        return user;
    }

    /**Dispose this frame and sets a new frame visible.
     * @param frame JFrame that must be set visible.
     */

    private void goToView(JFrame frame){
        frame.setVisible(true);
        Login.this.dispose();
    }

}
