package main.presentationLogic;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import main.dataLogic.people.Manager;
import main.dbManagement.DataExtraction;
import main.dbManagement.DataValidation;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**This JFrame allows the user to join a league that is already registered in the Database.
 * @author Iker Villena Ona
 */

public class JoinLeague extends JFrame {

    private JPanel contentPane;
    private JTextField txtEntryCode;
    private JLabel lblJoinLeague;
    private JLabel lblEntryCode;
    private JButton btnJoinLeague;


    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    JoinLeague frame = new JoinLeague(DataExtraction.getManager("ikervillena"));
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
    public JoinLeague(Manager manager) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 400, 500);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        lblJoinLeague = new JLabel("Unirse a liga");
        lblJoinLeague.setHorizontalAlignment(SwingConstants.CENTER);
        lblJoinLeague.setFont(new Font("Tahoma", Font.BOLD, 40));
        lblJoinLeague.setBounds(59, 77, 259, 49);
        contentPane.add(lblJoinLeague);

        lblEntryCode = new JLabel("C\u00F3digo de entrada");
        lblEntryCode.setHorizontalAlignment(SwingConstants.CENTER);
        lblEntryCode.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblEntryCode.setBounds(19, 189, 146, 49);
        contentPane.add(lblEntryCode);

        txtEntryCode = new JTextField();
        txtEntryCode.setFont(new Font("Tahoma", Font.PLAIN, 18));
        txtEntryCode.setColumns(10);
        txtEntryCode.setBounds(184, 189, 174, 49);
        contentPane.add(txtEntryCode);

        btnJoinLeague = new JButton("Unirse a liga");
        btnJoinLeague.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(DataValidation.checkEntryCode(txtEntryCode.getText())){
                    if(!manager.alreadyInLeague(txtEntryCode.getText())){
                        manager.joinLeague(txtEntryCode.getText());
                        JFrame nextView = new Menu(manager);
                        nextView.setVisible(true);
                        JoinLeague.this.dispose();
                    } else{
                        JOptionPane.showMessageDialog(null, "No es posible unirse, ya estás participando en esta liga.");
                    }
                } else{
                    JOptionPane.showMessageDialog(null, "El código de entrada no pertenece a ninguna liga.");
                }
            }
        });
        btnJoinLeague.setFont(new Font("Tahoma", Font.PLAIN, 18));
        btnJoinLeague.setBackground(SystemColor.activeCaptionBorder);
        btnJoinLeague.setBounds(93, 316, 174, 49);
        contentPane.add(btnJoinLeague);
    }

}
