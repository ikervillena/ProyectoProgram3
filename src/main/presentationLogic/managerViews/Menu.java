package main.presentationLogic.managerViews;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import main.dataLogic.league.League;
import main.dataLogic.people.Manager;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**This JFrame shows a list of leagues in which the manager provided competes.
 * It also provides access to the "JoinLeague.java" and "CreateLeague.java" JFrames.
 * @author Iker Villena Ona
 */

public class Menu extends JFrame {

    private Manager manager;
    private JPanel contentPane;
    private JComboBox cmbxLeagues;
    private JLabel lblLeagues;
    private JButton btnCreateLeague;
    private JButton btnJoinLeague;

    /**
     * Create the frame.
     */

    public Menu(Manager manager) {
        this.setTitle("Menú");
        this.manager = manager;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 800, 500);
        setLocationRelativeTo(null);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        cmbxLeagues = new JComboBox();
        cmbxLeagues.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                League chosenLeague = (League) cmbxLeagues.getModel().getSelectedItem();
                new LeagueMenu(chosenLeague, manager).setVisible(true);
                Menu.this.dispose();
            }
        });
        cmbxLeagues.setBounds(203, 217, 372, 40);
        cmbxLeagues.setModel(getLeaguesModel(manager));
        contentPane.add(cmbxLeagues);


        lblLeagues = new JLabel("Ligas");
        lblLeagues.setHorizontalAlignment(SwingConstants.CENTER);
        lblLeagues.setFont(new Font("Tahoma", Font.BOLD, 40));
        lblLeagues.setBounds(203, 75, 372, 57);
        contentPane.add(lblLeagues);

        btnCreateLeague = new JButton("Crear liga");
        btnCreateLeague.setBackground(SystemColor.activeCaptionBorder);
        btnCreateLeague.setFont(new Font("Tahoma", Font.PLAIN, 18));
        btnCreateLeague.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFrame nextView = new CreateLeague(manager);
                nextView.setVisible(true);
                Menu.this.dispose();
            }
        });
        btnCreateLeague.setBounds(203, 322, 155, 52);
        contentPane.add(btnCreateLeague);
        btnJoinLeague = new JButton("Unirse a liga");
        btnJoinLeague.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFrame nextView = new JoinLeague(manager);
                nextView.setVisible(true);
                Menu.this.dispose();
            }
        });
        btnJoinLeague.setBackground(SystemColor.activeCaptionBorder);
        btnJoinLeague.setFont(new Font("Tahoma", Font.PLAIN, 18));
        btnJoinLeague.setBounds(420, 322, 155, 52);
        contentPane.add(btnJoinLeague);
    }

    private DefaultComboBoxModel<League> getLeaguesModel(Manager manager){
        DefaultComboBoxModel<League> model = new DefaultComboBoxModel<League>();
        for(League l : manager.getLeagues()){
            model.addElement(l);
        }
        return model;
    }
}