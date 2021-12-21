package main.presentationLogic.managerViews;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;

import main.dataLogic.league.Team;
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
    private JButton btnGoBack;

    /**
     * Create the frame.
     */

    public JoinLeague(Manager manager) {
        this.setTitle("Unirse a liga");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 800, 500);
        setLocationRelativeTo(null);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        lblJoinLeague = new JLabel("Unirse a liga");
        lblJoinLeague.setHorizontalAlignment(SwingConstants.CENTER);
        lblJoinLeague.setFont(new Font("Tahoma", Font.BOLD, 40));
        lblJoinLeague.setBounds(259, 77, 259, 49);
        contentPane.add(lblJoinLeague);

        lblEntryCode = new JLabel("C\u00F3digo de entrada");
        lblEntryCode.setHorizontalAlignment(SwingConstants.CENTER);
        lblEntryCode.setFont(new Font("Trebuchet MS", Font.BOLD, 18));
        lblEntryCode.setBounds(188, 195, 185, 49);
        contentPane.add(lblEntryCode);

        txtEntryCode = new JTextField();
        txtEntryCode.setHorizontalAlignment(SwingConstants.CENTER);
        txtEntryCode.setFont(new Font("Trebuchet MS", Font.PLAIN, 18));
        txtEntryCode.setColumns(10);
        txtEntryCode.setBounds(392, 195, 201, 49);
        contentPane.add(txtEntryCode);

        btnJoinLeague = new JButton("Unirse a liga");
        btnJoinLeague.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(DataValidation.checkEntryCode(txtEntryCode.getText())){
                    if(!manager.alreadyInLeague(txtEntryCode.getText())){
                        if(Team.canGenerateTeam(DataExtraction.getLeague(txtEntryCode.getText()).getFreePlayers())){
                            manager.joinLeague(txtEntryCode.getText());
                            JFrame nextView = new Menu(manager);
                            nextView.setVisible(true);
                            JoinLeague.this.dispose();
                        } else{
                            JOptionPane.showMessageDialog(null,"No es posible, la liga ha alcanzado el número máximo de participantes.");
                        }
                    } else{
                        JOptionPane.showMessageDialog(null, "No es posible unirse, ya estás participando en esta liga.");
                    }
                } else{
                    JOptionPane.showMessageDialog(null, "El código de entrada no pertenece a ninguna liga.");
                }
            }
        });
        btnJoinLeague.setBorder(new BevelBorder(BevelBorder.RAISED, SystemColor.infoText, SystemColor.controlShadow, null, null));
        btnJoinLeague.setBackground(SystemColor.scrollbar);
        btnJoinLeague.setFont(new Font("Tahoma", Font.PLAIN, 18));
        btnJoinLeague.setBounds(302, 316, 174, 49);
        contentPane.add(btnJoinLeague);

        btnGoBack = new JButton("Volver");
        btnGoBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new Menu(manager).setVisible(true);
                JoinLeague.this.dispose();
            }
        });
        btnGoBack.setBorder(new BevelBorder(BevelBorder.RAISED, SystemColor.infoText, SystemColor.controlShadow, null, null));
        btnGoBack.setBackground(SystemColor.scrollbar);
        btnGoBack.setFont(new Font("Tahoma", Font.PLAIN, 18));
        btnGoBack.setBounds(619, 16, 144, 42);
        contentPane.add(btnGoBack);
    }

}
