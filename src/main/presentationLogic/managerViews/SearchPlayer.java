package main.presentationLogic.managerViews;

import javax.swing.*;
import main.dataLogic.league.League;
import main.dataLogic.league.Team;
import main.dataLogic.people.Manager;
import main.dataLogic.people.Player;
import main.dbManagement.DataExtraction;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.util.ArrayList;
import java.util.stream.Collectors;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;

/**This frame allows the user to search for information about the players.
 * @author Iker Villena Ona.
 */

public class SearchPlayer extends LeagueView {

    private League league;
    private Manager manager;
    private JPanel contentPane;
    private ArrayList<Player> playersList;
    private JPanel pnlSearch;
    private JButton btnBtnsearch;
    private JTextField txtSearch;
    private JPanel pnlResults;
    private JScrollPane scrollPane;
    private JList listPlayers;
    private JButton btnSign;
    private JLabel lblName1;
    private JLabel lblSurname1;
    private JLabel lblPosition1;
    private JLabel lblShirtNumber1;
    private JLabel lblTeam1;
    private JLabel lblName;
    private JLabel lblSurname;
    private JLabel lblPosition;
    private JLabel lblShirtnumber;
    private JButton btnSeevalue;
    private JLabel lblTeam;
    private JComboBox cmbxPositions;
    private JComboBox cmbxClub;

    /**
     * Create the frame.
     */

    public SearchPlayer(League league, Manager manager) {
        super(league, manager);
        this.setTitle("Buscar jugador");
        this.league = league;
        pnlSearch = new JPanel();
        pnlSearch.setBounds(15, 16, 798, 120);
        getContentPane().add(pnlSearch);
        pnlSearch.setLayout(null);

        txtSearch = new JTextField();
        txtSearch.setBounds(50, 41, 545, 40);
        pnlSearch.add(txtSearch);
        txtSearch.setColumns(10);

        btnBtnsearch = new JButton("Buscar");
        btnBtnsearch.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                playersList = DataExtraction.searchPlayer(txtSearch.getText());
                pnlResults.setVisible(true);
                setPlayers();
            }
        });
        btnBtnsearch.setBounds(618, 41, 155, 40);
        pnlSearch.add(btnBtnsearch);

        pnlResults = new JPanel();
        pnlResults.setVisible(false);
        pnlResults.setBounds(15, 152, 798, 343);
        getContentPane().add(pnlResults);
        pnlResults.setLayout(null);

        scrollPane = new JScrollPane();
        scrollPane.setBounds(33, 73, 304, 243);
        pnlResults.add(scrollPane);

        listPlayers = new JList();
        listPlayers.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                showPlayer();
                btnSeevalue.setEnabled(true);
                btnSign.setEnabled(true);
            }
        });
        scrollPane.setViewportView(listPlayers);

        btnSign = new JButton("Fichar");
        btnSign.setEnabled(false);
        btnSign.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(((Player) listPlayers.getSelectedValue()).getTeam(league) != null){
                    if(!((Player) listPlayers.getSelectedValue()).getTeam(league).equals(league.getTeam(manager))){
                        new BidMaker((Player) listPlayers.getSelectedValue(), league, manager).setVisible(true);
                    } else{
                        JOptionPane.showMessageDialog(null,"Este jugador te pertenece.");
                    }
                } else{
                    new BidMaker((Player) listPlayers.getSelectedValue(), league, manager).setVisible(true);
                }

            }
        });
        btnSign.setBounds(427, 270, 356, 43);
        pnlResults.add(btnSign);

        lblName1 = new JLabel("Nombre:");
        lblName1.setFont(new Font("Trebuchet MS", Font.BOLD, 18));
        lblName1.setBounds(447, 31, 117, 20);
        pnlResults.add(lblName1);

        lblSurname1 = new JLabel("Apellido");
        lblSurname1.setFont(new Font("Trebuchet MS", Font.BOLD, 18));
        lblSurname1.setBounds(447, 67, 117, 20);
        pnlResults.add(lblSurname1);

        lblPosition1 = new JLabel("Posici\u00F3n:");
        lblPosition1.setFont(new Font("Trebuchet MS", Font.BOLD, 18));
        lblPosition1.setBounds(447, 103, 117, 20);
        pnlResults.add(lblPosition1);

        lblShirtNumber1 = new JLabel("Dorsal:");
        lblShirtNumber1.setFont(new Font("Trebuchet MS", Font.BOLD, 18));
        lblShirtNumber1.setBounds(447, 139, 117, 20);
        pnlResults.add(lblShirtNumber1);

        lblName = new JLabel("");
        lblName.setFont(new Font("Trebuchet MS", Font.PLAIN, 18));
        lblName.setBounds(593, 31, 171, 20);
        pnlResults.add(lblName);

        lblSurname = new JLabel("");
        lblSurname.setFont(new Font("Trebuchet MS", Font.PLAIN, 18));
        lblSurname.setBounds(593, 67, 171, 20);
        pnlResults.add(lblSurname);

        lblPosition = new JLabel("");
        lblPosition.setFont(new Font("Trebuchet MS", Font.PLAIN, 18));
        lblPosition.setBounds(593, 103, 171, 20);
        pnlResults.add(lblPosition);

        lblShirtnumber = new JLabel("");
        lblShirtnumber.setFont(new Font("Trebuchet MS", Font.PLAIN, 18));
        lblShirtnumber.setBounds(593, 139, 171, 20);
        pnlResults.add(lblShirtnumber);

        btnSeevalue = new JButton("Evoluci\u00F3n de valor");
        btnSeevalue.setEnabled(false);
        btnSeevalue.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new PlayerValue((Player) listPlayers.getSelectedValue()).setVisible(true);
            }
        });
        btnSeevalue.setBounds(427, 211, 356, 43);
        pnlResults.add(btnSeevalue);

        lblTeam1 = new JLabel("Equipo:");
        lblTeam1.setFont(new Font("Trebuchet MS", Font.BOLD, 18));
        lblTeam1.setBounds(447, 175, 117, 20);
        pnlResults.add(lblTeam1);

        lblTeam = new JLabel("");
        lblTeam.setFont(new Font("Trebuchet MS", Font.PLAIN, 18));
        lblTeam.setBounds(593, 175, 171, 20);
        pnlResults.add(lblTeam);

        cmbxPositions = new JComboBox();
        cmbxPositions.addActionListener(e -> {
            setPlayers();
        });
        cmbxPositions.setBounds(33, 31, 304, 26);
        cmbxPositions.setFont(new Font("Trebuchet MS", Font.PLAIN, 18));
        DefaultListCellRenderer listRenderer = new DefaultListCellRenderer();
        listRenderer.setHorizontalAlignment(DefaultListCellRenderer.CENTER);
        cmbxPositions.setRenderer(listRenderer);
        setCmbxPositions();
        pnlResults.add(cmbxPositions);
        setAllFormats(getContentPane());
    }

    /**Fills the JCombobox with Positions.
     */

    private void setCmbxPositions(){
        DefaultComboBoxModel<String> modelPosition = new DefaultComboBoxModel<String>();
        modelPosition.addElement("Todos");
        DataExtraction.getPositions()
                .stream()
                .forEach(p -> modelPosition.addElement(p.toSpanish()));
        cmbxPositions.setModel(modelPosition);

    }

    /**Filters Players taking into account their Position.
     * @param playersList ArrayList<Player> with the list of players where the filter is applied.
     * @return ArrayList<Player> players that fit with the Position specifications.
     */

    private ArrayList<Player> addPositionFilter(ArrayList<Player> playersList){
        return (ArrayList<Player>) playersList.stream()
                .filter(p -> cmbxPositions.getSelectedItem().equals("Todos") ||
                        p.getPosition().toSpanish().equals(cmbxPositions.getSelectedItem()))
                .collect(Collectors.toList());
    }

    /**Fills the list with the players that match with the search.
     */

    private void setPlayers(){
        DefaultListModel<Player> model = new DefaultListModel<>();
        for(Player p : addPositionFilter(playersList)){
            model.addElement(p);
        }
        listPlayers.setModel(model);
        btnSeevalue.setEnabled(false);
        btnSign.setEnabled(false);
    }

    /**Shows the selected player's attributes, setting the needed text in the proper labels.
     */

    private void showPlayer(){
        Player selectedPlayer = (Player) listPlayers.getSelectedValue();
        if(selectedPlayer != null){
            lblName.setText(selectedPlayer.getName());
            lblSurname.setText(selectedPlayer.getSurname());
            lblPosition.setText(selectedPlayer.getPosition().toSpanish());
            lblShirtnumber.setText(String.valueOf(selectedPlayer.getShirtNumber()));
            Team team = selectedPlayer.getTeam(league);
            if(team == null){
                lblTeam.setText("Sin equipo");
            } else{
                lblTeam.setText(team.getManager().getUsername());
            }
        }
    }
}
