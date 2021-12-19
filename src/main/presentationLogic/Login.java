package main.presentationLogic;

import main.businessLogic.interfaces.INewData;
import main.dataLogic.people.Administrator;
import main.dataLogic.people.Manager;
import main.dataLogic.people.User;
import main.dbManagement.DataExtraction;
import main.presentationLogic.administrator.LoadMatches;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/** This JFrame allows the user to Log in.
 * @author Iker Villena Ona
 */

public class Login extends JFrame implements INewData {

    private ArrayList<User> usersList;
    private JPanel contentPane;
    private JPasswordField pswPassword;
    private JButton btnAccept;
    private JLabel lblLogIn;
    private JToggleButton tglbtnMode;
    private JLabel lblPassword;
    private JPanel pnlLogin;
    private JPasswordField pswPassword1;
    private JTextField txtUsername1;
    private JTextField txtSurname;
    private JTextField txtName;
    private JPanel pnlSignUp;
    private JTextField txtUsername;
    private JLayeredPane layeredPane;

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

        btnAccept = new JButton("Iniciar sesión");
        btnAccept.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(tglbtnMode.isSelected()){
                    if(checkFields()){
                        Manager newManager = new Manager(txtUsername1.getText(),pswPassword1.getText(),
                                txtName.getText(),txtSurname.getText());
                        newManager.save();
                        JOptionPane.showMessageDialog(null, "La cuenta ha sido creada con éxito.");
                        goToView(new Menu(newManager));
                    }
                } else{
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

            }
        });
        btnAccept.setBorder(new BevelBorder(BevelBorder.RAISED, SystemColor.infoText, SystemColor.controlShadow, null, null));
        btnAccept.setBackground(SystemColor.scrollbar);
        btnAccept.setFont(new Font("Tahoma", Font.PLAIN, 18));
        btnAccept.setBounds(310, 372, 157, 39);
        contentPane.add(btnAccept);

        lblLogIn = new JLabel("Iniciar sesi\u00F3n");
        lblLogIn.setFont(new Font("Tahoma", Font.BOLD, 40));
        lblLogIn.setHorizontalAlignment(SwingConstants.CENTER);
        lblLogIn.setBounds(10, 71, 748, 49);
        contentPane.add(lblLogIn);

        tglbtnMode = new JToggleButton("Crear cuenta");
        tglbtnMode.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(tglbtnMode.isSelected()){
                    tglbtnMode.setText("Iniciar sesión");
                    lblLogIn.setText("Crear cuenta");
                    btnAccept.setText("Crear cuenta");
                    setPanel(pnlSignUp);

                } else{
                    tglbtnMode.setText("Crear cuenta");
                    lblLogIn.setText("Iniciar sesión");
                    btnAccept.setText("Iniciar sesión");
                    setPanel(pnlLogin);
                }
            }
        });
        tglbtnMode.setBorder(new BevelBorder(BevelBorder.RAISED, SystemColor.infoText, SystemColor.controlShadow, null, null));
        tglbtnMode.setBackground(SystemColor.scrollbar);
        tglbtnMode.setFont(new Font("Tahoma", Font.PLAIN, 18));
        tglbtnMode.setBounds(600, 16, 157, 39);
        contentPane.add(tglbtnMode);

        layeredPane = new JLayeredPane();
        layeredPane.setBounds(197, 147, 384, 198);
        contentPane.add(layeredPane);
        layeredPane.setLayout(new CardLayout(0, 0));

        pnlSignUp = new JPanel();
        layeredPane.add(pnlSignUp);
        pnlSignUp.setVisible(false);
        pnlSignUp.setLayout(null);

        pswPassword1 = new JPasswordField();
        pswPassword1.setBounds(125, 159, 259, 39);
        pnlSignUp.add(pswPassword1);
        pswPassword1.setHorizontalAlignment(SwingConstants.CENTER);
        pswPassword1.setFont(new Font("Tahoma", Font.PLAIN, 18));

        JLabel lblPassword1 = new JLabel("Contrase\u00F1a");
        lblPassword1.setBounds(0, 159, 110, 39);
        pnlSignUp.add(lblPassword1);
        lblPassword1.setFont(new Font("Tahoma", Font.BOLD, 16));

        JLabel lblUser1 = new JLabel("Usuario");
        lblUser1.setBounds(0, 104, 110, 39);
        pnlSignUp.add(lblUser1);
        lblUser1.setFont(new Font("Tahoma", Font.BOLD, 16));

        txtUsername1 = new JTextField();
        txtUsername1.setHorizontalAlignment(SwingConstants.CENTER);
        txtUsername1.setBounds(125, 104, 259, 39);
        pnlSignUp.add(txtUsername1);
        txtUsername1.setColumns(10);

        JLabel lblSurname = new JLabel("Apellido");
        lblSurname.setBounds(0, 49, 110, 39);
        pnlSignUp.add(lblSurname);
        lblSurname.setFont(new Font("Tahoma", Font.BOLD, 16));

        txtSurname = new JTextField();
        txtSurname.setHorizontalAlignment(SwingConstants.CENTER);
        txtSurname.setBounds(125, 49, 259, 39);
        pnlSignUp.add(txtSurname);
        txtSurname.setColumns(10);

        JLabel lblName = new JLabel("Nombre");
        lblName.setBounds(0, 0, 110, 39);
        pnlSignUp.add(lblName);
        lblName.setFont(new Font("Tahoma", Font.BOLD, 16));

        txtName = new JTextField();
        txtName.setHorizontalAlignment(SwingConstants.CENTER);
        txtName.setBounds(125, 0, 259, 39);
        pnlSignUp.add(txtName);
        txtName.setColumns(10);

        pnlLogin = new JPanel();
        layeredPane.add(pnlLogin, "name_16335078502100");
        pnlLogin.setLayout(null);

        pswPassword = new JPasswordField();
        pswPassword.setBounds(125, 117, 259, 39);
        pnlLogin.add(pswPassword);
        pswPassword.setFont(new Font("Tahoma", Font.PLAIN, 18));
        pswPassword.setHorizontalAlignment(SwingConstants.CENTER);

        lblPassword = new JLabel("Contrase\u00F1a");
        lblPassword.setBounds(0, 118, 110, 39);
        pnlLogin.add(lblPassword);
        lblPassword.setFont(new Font("Tahoma", Font.BOLD, 16));

        JLabel lblUser = new JLabel("Usuario");
        lblUser.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblUser.setBounds(0, 40, 110, 39);
        pnlLogin.add(lblUser);

        txtUsername = new JTextField();
        txtUsername.setHorizontalAlignment(SwingConstants.CENTER);
        txtUsername.setColumns(10);
        txtUsername.setBounds(125, 40, 259, 39);
        pnlLogin.add(txtUsername);
        setPanel(pnlLogin);
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

    /**
     * @param panel
     */

    private void setPanel(JPanel panel){
        if(panel == pnlLogin || panel == pnlSignUp){
            layeredPane.removeAll();
            layeredPane.add(panel);
            layeredPane.repaint();
            layeredPane.revalidate();
        }
    }

    @Override
    public boolean checkFields() {
        boolean correctFields = true;
        if(usersList
                .stream()
                .anyMatch(user -> user.getUsername().equals(txtUsername1.getText()))){
            JOptionPane.showMessageDialog(null, "El nombre de usuario ya está registrado.");
            correctFields = false;
        }else{
            if(txtName.getText().equals("") || txtSurname.getText().equals("") ||
                    txtUsername1.getText().equals("") || pswPassword1.getText().equals("") ){
                JOptionPane.showMessageDialog(null, "Tienes que rellenar todos los campos.");
                correctFields = false;
            }
        }
        return correctFields;
    }
}
