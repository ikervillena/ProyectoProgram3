package main.presentationLogic;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.CardLayout;
import java.awt.GridLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.border.LineBorder;

import main.businessLogic.TacticalFormation;
import main.dataLogic.league.Squad;
import main.dataLogic.league.Team;
import main.dataLogic.people.Player;
import main.dbManagement.DataExtraction;
import main.dbManagement.DataInsertion;

import java.awt.SystemColor;
import javax.swing.JComboBox;
import java.awt.Font;
import javax.swing.JList;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;

public class LineUp extends ManagerView {

    private Team team;
    private TacticalFormation chosenFormation;
    private ArrayList<Player> availablePlayers;
    private ArrayList<Player> alignedPlayers;
    private Player selectedPlayer;
    private JPanel contentPane;
    private JPanel pnlForward;
    private JPanel pnlForwards;
    private JPanel pnlMidfielders;
    private JPanel pnlDefenders;
    private JPanel pnlGoalkeeper;
    private JButton btnGoalkeeper;
    private JLabel lblSoccerPitch;
    private JComboBox cmbxFormations;
    private JButton btnAddPlayer;
    private JPanel panel;
    private JComboBox cmbxPlayers;
    private JLabel lblStarters;
    private JLabel lblSubstitutes;
    private JList listStarters;
    private JList listSubtitutes;
    private JScrollPane scrAlignedPlayers;
    private JScrollPane scrUnalignedPlayers;
    private JButton btnSavesquad;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    LineUp frame = new LineUp(DataExtraction.getTeams(0).get(0));
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
    public LineUp(Team team) {
        this.team = team;
        availablePlayers = team.getPlayersList();
        alignedPlayers = new ArrayList<>();
        System.out.println(team.getSquadRecord().size());

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        pnlForward = new JPanel();
        pnlForward.setBounds(622, 16, 61, 13);
        contentPane.add(pnlForward);
        pnlForward.setLayout(new CardLayout(0, 0));

        pnlForwards = new JPanel();
        pnlForwards.setOpaque(false);
        pnlForwards.setBounds(15, 16, 445, 120);
        contentPane.add(pnlForwards);
        pnlForwards.setLayout(new GridLayout(1, 0, 0, 0));

        pnlMidfielders = new JPanel();
        pnlMidfielders.setOpaque(false);
        pnlMidfielders.setBounds(15, 136, 445, 120);
        contentPane.add(pnlMidfielders);
        pnlMidfielders.setLayout(new GridLayout(1, 0, 0, 0));

        pnlDefenders = new JPanel();
        pnlDefenders.setOpaque(false);
        pnlDefenders.setBounds(15, 256, 445, 120);
        contentPane.add(pnlDefenders);
        pnlDefenders.setLayout(new GridLayout(1, 0, 0, 0));

        pnlGoalkeeper = new JPanel();
        pnlGoalkeeper.setOpaque(false);
        pnlGoalkeeper.setBounds(15, 376, 445, 119);
        contentPane.add(pnlGoalkeeper);
        pnlGoalkeeper.setLayout(new GridLayout(1, 0, 0, 0));

        btnGoalkeeper = new JButton("");
        btnGoalkeeper.setOpaque(false);
        btnGoalkeeper.setContentAreaFilled(false);
        btnGoalkeeper.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setCmbxPlayers(btnGoalkeeper);
                selectedPlayer = getPlayer(btnGoalkeeper.getText());
            }
        });
        btnGoalkeeper.setSize(316, 88);
        btnGoalkeeper.setLocation(45, 0);
        pnlGoalkeeper.add(btnGoalkeeper);

        lblSoccerPitch = new JLabel("");
        lblSoccerPitch.setIcon(new ImageIcon("C:\\Users\\Alumno\\Downloads\\soccerpich.jpg"));
        lblSoccerPitch.setBounds(15, 16, 445, 479);
        contentPane.add(lblSoccerPitch);

        cmbxFormations = new JComboBox();
        cmbxFormations.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                chosenFormation = (TacticalFormation) cmbxFormations.getSelectedItem();
                fulFillPanels();
            }
        });
        setCmbxFormations();
        cmbxFormations.setBounds(475, 29, 338, 26);
        contentPane.add(cmbxFormations);

        panel = new JPanel();
        panel.setVisible(false);
        panel.setBackground(SystemColor.activeCaption);
        panel.setBounds(475, 71, 338, 140);
        contentPane.add(panel);
        panel.setLayout(null);

        cmbxPlayers = new JComboBox();
        cmbxPlayers.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btnAddPlayer.setEnabled(true);
            }
        });
        cmbxPlayers.setBounds(55, 31, 227, 34);
        panel.add(cmbxPlayers);

        btnAddPlayer = new JButton("A\u00F1adir jugador");
        btnAddPlayer.setEnabled(false);
        btnAddPlayer.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(selectedPlayer != null){
                    alignedPlayers.remove(selectedPlayer);
                    selectedPlayer = null;
                }
                Player alignedPlayer = (Player) cmbxPlayers.getSelectedItem();
                alignedPlayers.add(alignedPlayer);
                availablePlayers.remove(alignedPlayer);
                fulFillPanels();
                btnAddPlayer.setEnabled(false);
                panel.setVisible(false);
                setLists();
            }
        });
        btnAddPlayer.setBounds(99, 90, 139, 34);
        panel.add(btnAddPlayer);

        scrAlignedPlayers = new JScrollPane();
        scrAlignedPlayers.setBounds(475, 256, 169, 198);
        contentPane.add(scrAlignedPlayers);

        listStarters = new JList();
        scrAlignedPlayers.setViewportView(listStarters);

        scrUnalignedPlayers = new JScrollPane();
        scrUnalignedPlayers.setBounds(644, 256, 169, 198);
        contentPane.add(scrUnalignedPlayers);

        listSubtitutes = new JList();
        scrUnalignedPlayers.setViewportView(listSubtitutes);

        lblSubstitutes = new JLabel("Suplentes");
        lblSubstitutes.setHorizontalAlignment(SwingConstants.CENTER);
        lblSubstitutes.setFont(new Font("Times New Roman", Font.BOLD, 16));
        lblSubstitutes.setBounds(644, 227, 169, 26);
        contentPane.add(lblSubstitutes);

        lblStarters = new JLabel("Titulares");
        lblStarters.setHorizontalAlignment(SwingConstants.CENTER);
        lblStarters.setFont(new Font("Times New Roman", Font.BOLD, 16));
        lblStarters.setBounds(475, 227, 169, 26);
        contentPane.add(lblStarters);

        btnSavesquad = new JButton("Guardar alineaci\u00F3n");
        btnSavesquad.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // INSERT SQUAD
                DataInsertion.insertSquad(team, new Squad(DataExtraction.getNextRound(),chosenFormation, alignedPlayers));
            }
        });
        btnSavesquad.setBounds(475, 466, 338, 29);
        contentPane.add(btnSavesquad);
    }

    private void setUp(){
        alignedPlayers = new ArrayList<>();
        availablePlayers = team.getPlayersList();
        chosenFormation = DataExtraction.getAllFormations().get(0);
        for(Squad s : team.getSquadRecord()){
            if(s.getRoundNum() == DataExtraction.getNextRound()){
                alignedPlayers = s.getPlayersList();
                chosenFormation = s.getFormation();
            }
        }
        fulFillPanels();
    }

    private void fulFillPanels(){
        addButtons(pnlDefenders, chosenFormation.getNumDefenders(), getPositionPlayers("Defense",alignedPlayers));
        addButtons(pnlMidfielders, chosenFormation.getNumMidfielders(),getPositionPlayers("Midfielder",alignedPlayers));
        addButtons(pnlForwards, chosenFormation.getNumForwards(), getPositionPlayers("Forward",alignedPlayers));
        for(Player p : alignedPlayers){
            if(p.getPosition().getName().equals("Goalkeeper")){
                btnGoalkeeper.setText(p.toString());
            }
        }
        // OTHER PANEL:
        addButtons(pnlForward, chosenFormation.getNumForwards(), getPositionPlayers("Defense",alignedPlayers));
    }

    /**This method adds JButtons to a panel, according to the number of buttons provided as a parameter, and sets the text based on a player's list.
     * @param panel JPanel in which buttons must be added.
     * @param numButtons Integer with the number of buttons that must be added.
     * @param playersList ArrayList<Player> containing the players that must be associated to the buttons.
     */

    private void addButtons(JPanel panel, int numButtons, ArrayList<Player> playersList){
        panel.removeAll();
        for(int i = 0; i < numButtons; i++){
            JButton newButton = new JButton();
            newButton.setOpaque(false);
            newButton.setContentAreaFilled(false);
            if(i<playersList.size()){
                newButton.setText(playersList.get(i).toString());
            }
            newButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    setCmbxPlayers(newButton);
                    selectedPlayer = getPlayer(newButton.getText());
                }
            });
            panel.add(newButton);
        }
        if(numButtons<playersList.size()){
            for(int i = numButtons; i < playersList.size(); i++){
                alignedPlayers.remove(playersList.get(i));
            }
        }
    }

    /**Provides a list with the players that play in a specific position from a specific list of players.
     * @param positionName String with the position's name.
     * @param playersList ArrayList<Player> with a list of players from which players are selected.
     * @return An ArrayList<Player> with the list of players from the list provided that play in the position provided as a parameter.
     */

    private ArrayList<Player> getPositionPlayers(String positionName, ArrayList<Player> playersList){
        ArrayList<Player> list = new ArrayList();
        for(Player p : playersList){
            if(p.getPosition().getName().equals(positionName)){
                list.add(p);
            }
        }
        return list;
    }

    /**Adds the list of TacticalFormation to cmbxFormations.
     */

    private void setCmbxFormations(){
        DefaultComboBoxModel<TacticalFormation> model = new DefaultComboBoxModel<>();
        for(TacticalFormation t : DataExtraction.getAllFormations()){
            model.addElement(t);
        }
        cmbxFormations.setModel(model);
    }

    /**Adds the list of Player to the cmbxPlayers.
     * @param playersList ArrayList<Player> with the list of players that must be added to the JComboBox.
     */

    private void setPlayersModel(ArrayList<Player> playersList){
        DefaultComboBoxModel<Player> model = new DefaultComboBoxModel<>();
        for(Player p : playersList){
            model.addElement(p);
        }
        cmbxPlayers.setModel(model);
    }

    /**Adds the list of Player to the cmbxPlayers based on the provided JButton's container.
     * @param button JButton that is contained in the position's panel from which the position of the players to add is taken.
     */

    private void setCmbxPlayers(JButton button){
        panel.setVisible(true);
        if(pnlGoalkeeper.isAncestorOf(button)){
            setPlayersModel(getPositionPlayers("Goalkeeper", availablePlayers));
        } else{
            if(pnlDefenders.isAncestorOf(button)){
                setPlayersModel(getPositionPlayers("Defense", availablePlayers));
            } else{
                if(pnlMidfielders.isAncestorOf(button)){
                    setPlayersModel(getPositionPlayers("Midfielder", availablePlayers));
                }else{
                    setPlayersModel(getPositionPlayers("Forward", availablePlayers));
                }
            }
        }
    }

    /**Provides a Player whose toString() method returns the String provided.
     * @param nameNumber String with a player's toString().
     * @return A Player whose toString() method returns the same String as the one provided as a parameter.
     */

    private Player getPlayer(String nameNumber){
        Player player = null;
        for(Player p : alignedPlayers){
            if(p.toString().equals(nameNumber)){
                player = p;
            }
        }
        return player;
    }

    private ArrayList<Player> getSubstitutes(){
        ArrayList<Player> unalignedPlayers = availablePlayers;
        for(Player p : alignedPlayers){
            unalignedPlayers.remove(p);
        }
        return unalignedPlayers;
    }


    private void setLists(){
        DefaultListModel<Player> model1 = new DefaultListModel<>();
        for (Player p : alignedPlayers){
            model1.addElement(p);
        }
        listStarters.setModel(model1);
        DefaultListModel<Player> model2 = new DefaultListModel<>();
        for (Player p : getSubstitutes()){
            model2.addElement(p);
        }
        listSubtitutes.setModel(model2);
    }
}