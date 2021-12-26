package main.presentationLogic.managerViews;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import main.dataLogic.league.League;
import main.dataLogic.people.Manager;
import main.presentationLogic.Login;

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
    private JButton btnLogOut;

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
        cmbxLeagues.addActionListener(e -> {
            League chosenLeague = (League) cmbxLeagues.getModel().getSelectedItem();
            new LeagueMenu(chosenLeague, manager).setVisible(true);
            Menu.this.dispose();
        });
        cmbxLeagues.setFont(new Font("Trebuchet MS", Font.PLAIN, 18));
        DefaultListCellRenderer listRenderer = new DefaultListCellRenderer();
        listRenderer.setHorizontalAlignment(DefaultListCellRenderer.CENTER);
        cmbxLeagues.setRenderer(listRenderer);
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
        btnCreateLeague.setFont(new Font("Trebuchet MS", Font.PLAIN, 18));
        btnCreateLeague.addActionListener(e -> {
            new CreateLeague(manager).setVisible(true);
            Menu.this.dispose();
        });
        btnCreateLeague.setBounds(203, 322, 155, 52);
        contentPane.add(btnCreateLeague);
        btnJoinLeague = new JButton("Unirse a liga");
        btnJoinLeague.addActionListener(e -> {
            JFrame nextView = new JoinLeague(manager);
            nextView.setVisible(true);
            Menu.this.dispose();
        });
        btnJoinLeague.setBackground(SystemColor.activeCaptionBorder);
        btnJoinLeague.setFont(new Font("Tahoma", Font.PLAIN, 18));
        btnJoinLeague.setBounds(420, 322, 155, 52);
        contentPane.add(btnJoinLeague);

        btnLogOut = new JButton("Cerrar sesi\u00F3n");
        btnLogOut.addActionListener(e -> {
            Menu.this.dispose();
            new Login().setVisible(true);
        });
        btnLogOut.setFont(new Font("Trebuchet MS", Font.PLAIN, 18));
        btnLogOut.setBackground(SystemColor.activeCaptionBorder);
        btnLogOut.setBounds(608, 16, 155, 52);
        contentPane.add(btnLogOut);
    }

    /**Provides a model with the leagues in which the Manager competes.
      * @param manager Manager who competes in the leagues.
     * @return DefaultComboBoxModel<League> with all the leagues in which the Manager competes.
     */

    private DefaultComboBoxModel<League> getLeaguesModel(Manager manager){
        DefaultComboBoxModel<League> model = new DefaultComboBoxModel<League>();
        for(League l : manager.getLeagues()){
            model.addElement(l);
        }
        return model;
    }
}