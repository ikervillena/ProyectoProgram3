package presentationLogic;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dataLogic.people.Manager;
import dbManagement.DataExtraction;
import dbManagement.DataValidation;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**This JFrame allows the user to create a new league and save it to the Database.
 * @author Iker Villena Ona
 */

public class CreateLeague extends JFrame {

    private JPanel contentPane;
    private JTextField txtLeagueName;
    private JTextField txtEntryCode;
    private JLabel lblCreateLeague;
    private JLabel lblLeagueName;
    private JLabel lblEntryCode;
    private JButton btnCrearLiga;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    CreateLeague frame = new CreateLeague(DataExtraction.getManager("ikervillena"));
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
    public CreateLeague(Manager manager) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 400, 500);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        lblCreateLeague = new JLabel("Crear liga");
        lblCreateLeague.setHorizontalAlignment(SwingConstants.CENTER);
        lblCreateLeague.setFont(new Font("Tahoma", Font.BOLD, 40));
        lblCreateLeague.setBounds(56, 61, 259, 49);
        contentPane.add(lblCreateLeague);

        lblLeagueName = new JLabel("Nombre de liga");
        lblLeagueName.setHorizontalAlignment(SwingConstants.CENTER);
        lblLeagueName.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblLeagueName.setBounds(19, 170, 146, 49);
        contentPane.add(lblLeagueName);

        lblEntryCode = new JLabel("C\u00F3digo de entrada");
        lblEntryCode.setHorizontalAlignment(SwingConstants.CENTER);
        lblEntryCode.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblEntryCode.setBounds(19, 250, 146, 49);
        contentPane.add(lblEntryCode);

        txtLeagueName = new JTextField();
        txtLeagueName.setFont(new Font("Tahoma", Font.PLAIN, 18));
        txtLeagueName.setBounds(184, 170, 174, 49);
        contentPane.add(txtLeagueName);
        txtLeagueName.setColumns(10);

        txtEntryCode = new JTextField();
        txtEntryCode.setFont(new Font("Tahoma", Font.PLAIN, 18));
        txtEntryCode.setColumns(10);
        txtEntryCode.setBounds(184, 250, 174, 49);
        contentPane.add(txtEntryCode);

        btnCrearLiga = new JButton("Crear liga");
        btnCrearLiga.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String leagueName = txtLeagueName.getText();
                String entryCode = txtEntryCode.getText();
                if(!DataValidation.checkEntryCode(entryCode)){
                    manager.createLeague(leagueName, entryCode);
                    JFrame nextView = new Menu(manager);
                    nextView.setVisible(true);
                    CreateLeague.this.dispose();
                } else{
                    JOptionPane.showMessageDialog(null, "El código de entrada ya está en uso.");
                }
            }
        });
        btnCrearLiga.setFont(new Font("Tahoma", Font.PLAIN, 18));
        btnCrearLiga.setBackground(SystemColor.activeCaptionBorder);
        btnCrearLiga.setBounds(102, 355, 174, 49);
        contentPane.add(btnCrearLiga);
    }

}