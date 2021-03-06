package main.presentationLogic.administratorViews;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import main.businessLogic.Statistic;
import main.dataLogic.league.Club;
import main.dataLogic.league.League;
import main.dataLogic.people.Administrator;
import main.dataLogic.people.Player;
import main.dbManagement.DataDeletion;
import main.dbManagement.DataExtraction;
import main.dbManagement.DataInsertion;
import main.dbManagement.DataUpdate;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**This frame allows the administrators to save player's statistics to the DataBase.
 * @author Iker Villena Ona.
 */

public class LoadMatches extends JFrame {

    private JPanel contentPane;
    private Administrator administrator;
    private int nextRound;
    private String[][] matchesList;
    private Club homeClub;
    private Club awayClub;
    private JComboBox cmbxMatches;
    private JLabel lblRound;
    private JTable tblHomePlayers;
    private JTable tblAwayPlayers;
    private JPanel panelMatch;
    private JLabel lblHomeClub;
    private JLabel lblAwayClub;
    private JSpinner spnHomeClub;
    private JSpinner spnAwayClub;
    private JScrollPane scrollPane;
    private JScrollPane scrollPane2;
    private JButton btnSaveMatch;
    private JTabbedPane tbpHomeClub;
    private JTabbedPane tbpAwayClub;

    /**
     * Create the frame.
     */

    public LoadMatches(Administrator administrator) {
        this.setTitle("Cargar partidos");
        this.administrator = administrator;
        nextRound = DataExtraction.getNextRound();
        matchesList = DataExtraction.getMatches(nextRound);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 850, 600);
        setLocationRelativeTo(null);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        cmbxMatches = new JComboBox();
        cmbxMatches.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int matchIndex = cmbxMatches.getSelectedIndex();
                homeClub = new Club(matchesList[matchIndex][0],DataExtraction.getClubPlayers(matchesList[matchIndex][0]));
                awayClub = new Club(matchesList[matchIndex][1],DataExtraction.getClubPlayers(matchesList[matchIndex][1]));
                lblHomeClub.setText(homeClub.getName());
                lblAwayClub.setText(awayClub.getName());
                fillTable();
                panelMatch.setVisible(true);
                btnSaveMatch.setVisible(true);
            }
        });
        cmbxMatches.setFont(new Font("Trebuchet MS", Font.PLAIN, 18));
        cmbxMatches.setBounds(193, 45, 558, 26);
        contentPane.add(cmbxMatches);
        cmbxMatches.setModel(getMatchesModel());

        lblRound = new JLabel("Jornada "+DataExtraction.getNextRound());
        lblRound.setFont(new Font("Trebuchet MS", Font.PLAIN, 18));
        lblRound.setBounds(60, 45, 143, 26);
        contentPane.add(lblRound);

        panelMatch = new JPanel();
        panelMatch.setVisible(false);
        panelMatch.setBounds(60, 100, 696, 262);
        contentPane.add(panelMatch);
        panelMatch.setLayout(null);

        lblHomeClub = new JLabel("");
        lblHomeClub.setBounds(32, 0, 187, 32);
        panelMatch.add(lblHomeClub);
        lblHomeClub.setHorizontalAlignment(SwingConstants.CENTER);
        lblHomeClub.setFont(new Font("Trebuchet MS", Font.PLAIN, 18));

        lblAwayClub = new JLabel("");
        lblAwayClub.setBounds(429, 0, 187, 32);
        panelMatch.add(lblAwayClub);
        lblAwayClub.setHorizontalAlignment(SwingConstants.CENTER);
        lblAwayClub.setFont(new Font("Trebuchet MS", Font.PLAIN, 18));

        spnHomeClub = new JSpinner();
        centreJSpinner(spnHomeClub);
        spnHomeClub.setBounds(245, 0, 66, 32);
        panelMatch.add(spnHomeClub);
        spnHomeClub.setFont(new Font("Trebuchet MS", Font.PLAIN, 18));

        spnAwayClub = new JSpinner();
        centreJSpinner(spnAwayClub);
        spnAwayClub.setBounds(337, 0, 66, 32);
        panelMatch.add(spnAwayClub);
        spnAwayClub.setFont(new Font("Trebuchet MS", Font.PLAIN, 18));

        tbpHomeClub = new JTabbedPane(JTabbedPane.TOP);
        tbpHomeClub.setFont(new Font("Trebuchet MS", Font.PLAIN, 18));
        tbpHomeClub.setBounds(0, 35, 696, 227);
        panelMatch.add(tbpHomeClub);

        scrollPane = new JScrollPane();
        tbpHomeClub.addTab("Local", null, scrollPane, null);
        scrollPane.setFont(new Font("Trebuchet MS", Font.PLAIN, 18));

        tblHomePlayers = new JTable();
        scrollPane.setViewportView(tblHomePlayers);
        tblHomePlayers.setModel(new DefaultTableModel(
                new Object[][] {
                },
                new String[] {
                        "Jugador", "Ha jugado", "Goles", "Asistencias", "Tarjetas amarillas", "Tarjeta roja"
                }
        ) {
            Class[] columnTypes = new Class[] {
                    String.class, Boolean.class, Integer.class, Integer.class, Integer.class, Boolean.class
            };
            public Class getColumnClass(int columnIndex) {
                return columnTypes[columnIndex];
            }
        });

        tbpAwayClub = new JTabbedPane(JTabbedPane.TOP);
        tbpAwayClub.setFont(new Font("Trebuchet MS", Font.PLAIN, 18));
        tbpHomeClub.setBounds(0, 35, 696, 227);
        panelMatch.add(tbpAwayClub);

        scrollPane2 = new JScrollPane();
        tbpHomeClub.addTab("Visitante", null, scrollPane2, null);
        scrollPane2.setFont(new Font("Trebuchet MS", Font.PLAIN, 18));

        tblAwayPlayers = new JTable();
        scrollPane2.setViewportView(tblAwayPlayers);
        tblAwayPlayers.setModel(new DefaultTableModel(
                new Object[][] {
                },
                new String[] {
                        "Jugador", "Ha jugado", "Goles", "Asistencias", "Tarjetas amarillas", "Tarjeta roja"
                }
        ) {
            Class[] columnTypes = new Class[] {
                    String.class, Boolean.class, Integer.class, Integer.class, Integer.class, Boolean.class
            };
            public Class getColumnClass(int columnIndex) {
                return columnTypes[columnIndex];
            }
        });

        btnSaveMatch = new JButton("Guardar partido");
        btnSaveMatch.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            saveStatistics((DefaultTableModel) tblHomePlayers.getModel(),homeClub, (int) spnAwayClub.getValue());
            saveStatistics((DefaultTableModel) tblAwayPlayers.getModel(),awayClub, (int) spnHomeClub.getValue());
            DataUpdate.updateMatch(homeClub.getName(),awayClub.getName(),(int) spnHomeClub.getValue(),
                    (int) spnAwayClub.getValue());
            JOptionPane.showMessageDialog(null,"Las estad??sticas del partido han sido guardadas correctamente.");
            refresh();
            }
        });
        btnSaveMatch.setFont(new Font("Trebuchet MS", Font.PLAIN, 18));
        btnSaveMatch.setBackground(SystemColor.activeCaptionBorder);
        btnSaveMatch.setBounds(566, 390, 171, 40);
        contentPane.add(btnSaveMatch);
        btnSaveMatch.setVisible(false);

        //If the administrator has full access, he is allowed to restart the league.
        if(administrator.isFullAccess()){
            JButton btnRestart = new JButton("Reiniciar juego");
            btnRestart.setFont(new Font("Trebuchet MS", Font.PLAIN, 18));
            btnRestart.setBackground(SystemColor.activeCaptionBorder);
            btnRestart.setBounds(50, 425, 155, 52);
            btnRestart.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if(JOptionPane.showConfirmDialog(null,
                            "??Est??s seguro de que quieres reiniciar el juego?") == 0){
                        DataDeletion.cleanTable("league");
                        DataDeletion.cleanTable("statistic");
                    }
                }
            });
            contentPane.add(btnRestart);
        }

    }

    /**This method is used for initializing the Components after saving a match to the Database.
     * For that: establishes a new list of matches for cmbxMatches, updates the next round number and makes panelMatch and btnSaveMatch not visible.
     */

    private void refresh(){
        //When all the statistics of a round are saved, the higher bids for each player in each league are accepted.
        if(nextRound < DataExtraction.getNextRound()){
            for(League l : DataExtraction.getAllLeagues()){
                l.acceptLeagueBids(l.getBids());
            }
        }
        nextRound = DataExtraction.getNextRound();
        matchesList = DataExtraction.getMatches(nextRound);
        cmbxMatches.setModel(getMatchesModel());
        btnSaveMatch.setVisible(false);
        panelMatch.setVisible(false);
        lblRound.setText("Jornada "+DataExtraction.getNextRound());
    }

    /**Provides a list with the matches of the next round.
     * @return a DefaultComboBoxModel<String> containing a list of Strings with the names of each club that participates in a match.
     */

    private DefaultComboBoxModel<String> getMatchesModel(){
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>();
        for(int i = 0; i < matchesList.length; i++){
            model.addElement(matchesList[i][0]+" - "+matchesList[i][1]);
        }
        return model;
    }

    /**Extracts information from the table and saves the statistics of a club's players and its value evolution to the Database.
     * @param model DefaultTableModel containing the statistics for each player.
     * @param club Club where the players of the table play.
     * @param receivedGoals Integer with the goals received by the club.
     */

    public void saveStatistics(DefaultTableModel model,Club club, int receivedGoals){
        for(int i = 0; i < model.getRowCount(); i++){
            int roundNum = DataExtraction.getNextRound();
            Player player = club.getPlayersList().get(i);
            boolean played = (boolean) model.getValueAt(i,1);
            int numGoals = (int) model.getValueAt(i,2);
            int numAssists = (int) model.getValueAt(i,3);
            int yellowCards = (int) model.getValueAt(i,4);
            boolean redCard = (boolean) model.getValueAt(i,5);
            Statistic statistic = new Statistic(played,numGoals,numAssists,receivedGoals,yellowCards,redCard);
            DataInsertion.insertStatistic(roundNum, player, statistic);
            DataInsertion.insertValue(player, roundNum,player.getNewValue(statistic.getPoints(player.getPosition())));
        }
    }

    /**This method centres the text of a JSpinner.
     * @param spinner JSpinner whose horizontal alignment needs to be changed to "center".
     */

    private void centreJSpinner(JSpinner spinner){
        JComponent editor = spinner.getEditor();
        if (editor instanceof JSpinner.DefaultEditor) {
            JSpinner.DefaultEditor spinnerEditor = (JSpinner.DefaultEditor)editor;
            spinnerEditor.getTextField().setHorizontalAlignment(JTextField.CENTER);
        }
    }

    /**This method sets a table's row's height and centres the text.
     * @param table A JTable whose format needs to be changed.
     */

    private void setTableHeightAndCentreText(JTable table){
        for(int i = 0; i < table.getModel().getRowCount(); i ++){
            table.setRowHeight(i , 30);
        }
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.CENTER);
        for(int i = 0; i < table.getModel().getColumnCount(); i++){
            if(i != 1 && i != 5){
                table.getColumnModel().getColumn(i).setCellRenderer(tcr);
            }
        }
    }

    /**Fills both home club's and away club's tables with the list of players that play for each club.
     */

    private void fillTable(){
        ((DefaultTableModel) tblHomePlayers.getModel()).setRowCount(0);
        DefaultTableModel model = (DefaultTableModel) tblHomePlayers.getModel();
        for(Player p : homeClub.getPlayersList()){
            String playerName = p.getName()+" "+p.getSurname();
            model.addRow(new Object[]{playerName,true,0,0,0,false});
        }
        ((DefaultTableModel) tblAwayPlayers.getModel()).setRowCount(0);
        DefaultTableModel model2 = (DefaultTableModel) tblAwayPlayers.getModel();
        for(Player p : awayClub.getPlayersList()){
            String playerName = p.getName()+" "+p.getSurname();
            model2.addRow(new Object[]{playerName,true,0,0,0,false});
        }
        setTableHeightAndCentreText(tblHomePlayers);
        setTableHeightAndCentreText(tblAwayPlayers);
    }

}