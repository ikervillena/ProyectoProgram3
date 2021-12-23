package main.presentationLogic.managerViews;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
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
    private JButton btnGoback;

    /**
     * Create the frame.
     */

    public CreateLeague(Manager manager) {
        this.setTitle("Crear liga");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 800, 500);
        setLocationRelativeTo(null);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        lblCreateLeague = new JLabel("Crear liga");
        lblCreateLeague.setHorizontalAlignment(SwingConstants.CENTER);
        lblCreateLeague.setFont(new Font("Tahoma", Font.BOLD, 40));
        lblCreateLeague.setBounds(259, 61, 259, 49);
        contentPane.add(lblCreateLeague);

        lblLeagueName = new JLabel("Nombre de liga");
        lblLeagueName.setHorizontalAlignment(SwingConstants.LEFT);
        lblLeagueName.setFont(new Font("Trebuchet MS", Font.BOLD, 18));
        lblLeagueName.setBounds(195, 170, 180, 49);
        contentPane.add(lblLeagueName);

        lblEntryCode = new JLabel("C\u00F3digo de entrada");
        lblEntryCode.setHorizontalAlignment(SwingConstants.LEFT);
        lblEntryCode.setFont(new Font("Trebuchet MS", Font.BOLD, 18));
        lblEntryCode.setBounds(195, 250, 180, 49);
        contentPane.add(lblEntryCode);

        txtLeagueName = new JTextField();
        txtLeagueName.setHorizontalAlignment(SwingConstants.CENTER);
        txtLeagueName.setFont(new Font("Trebuchet MS", Font.PLAIN, 18));
        txtLeagueName.setBounds(394, 170, 226, 49);
        contentPane.add(txtLeagueName);
        txtLeagueName.setColumns(10);

        txtEntryCode = new JTextField();
        txtEntryCode.setHorizontalAlignment(SwingConstants.CENTER);
        txtEntryCode.setFont(new Font("Trebuchet MS", Font.PLAIN, 18));
        txtEntryCode.setColumns(10);
        txtEntryCode.setBounds(394, 250, 226, 49);
        contentPane.add(txtEntryCode);

        btnCrearLiga = new JButton("Crear liga");
        btnCrearLiga.addActionListener(e -> {
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
        });
        btnCrearLiga.setBorder(new BevelBorder(BevelBorder.RAISED, SystemColor.infoText, SystemColor.controlShadow, null, null));
        btnCrearLiga.setBackground(SystemColor.scrollbar);
        btnCrearLiga.setFont(new Font("Tahoma", Font.PLAIN, 18));
        btnCrearLiga.setBounds(302, 355, 174, 49);
        contentPane.add(btnCrearLiga);

        btnGoback = new JButton("Volver");
        btnGoback.addActionListener(e -> {
            new Menu(manager).setVisible(true);
            CreateLeague.this.dispose();
        });
        btnGoback.setBorder(new BevelBorder(BevelBorder.RAISED, SystemColor.infoText, SystemColor.controlShadow, null, null));
        btnGoback.setBackground(SystemColor.scrollbar);
        btnGoback.setFont(new Font("Tahoma", Font.PLAIN, 18));
        btnGoback.setBounds(619, 16, 144, 42);
        contentPane.add(btnGoback);
    }
}