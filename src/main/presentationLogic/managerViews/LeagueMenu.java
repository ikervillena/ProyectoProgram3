package main.presentationLogic.managerViews;

import main.businessLogic.MergeSort;
import main.dataLogic.league.League;
import main.dataLogic.league.Team;
import main.dataLogic.people.Manager;
import main.dbManagement.DataExtraction;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.SystemColor;
import java.awt.Font;
import java.util.ArrayList;
import javax.swing.border.BevelBorder;

/**This frame is the menu of the league and allows the user to see the classification and get to other views..
 * @author Iker Villena Ona.
 */

public class LeagueMenu extends LeagueView {

    private League league;
    private Manager manager;
    private JPanel contentPane;
    private JTable tblClassification;
    private JPanel pnl1;
    private JPanel pnl2;
    private JButton btnBids;
    private JButton btnMenu;
    private JButton btnSearchPlayer;
    private JButton btnLineUp;
    private JButton btnAbandon;
    private JLabel lblFantasyGame;
    private JProgressBar progressBar;

    /**
     * Create the frame.
     */

    public LeagueMenu(League league, Manager manager) {
        super(league, manager);
        this.setTitle("Menú de liga");
        this.league = league;
        this.manager = manager;
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(223, 114, 381, 294);
        getContentPane().add(scrollPane);

        tblClassification = new JTable();
        tblClassification.setModel(new DefaultTableModel(
                new Object[][] {}, new String[] {"Ranking", "Equipo", "Points"}) {
            Class[] columnTypes = new Class[] {Integer.class, String.class, Integer.class};
            public Class getColumnClass(int columnIndex) {
                return columnTypes[columnIndex];
            }
            boolean[] columnEditables = new boolean[] {false, false, false};
            public boolean isCellEditable(int row, int column) {
                return columnEditables[column];
            }
        });
        tblClassification.getColumnModel().getColumn(0).setResizable(false);
        tblClassification.getColumnModel().getColumn(1).setResizable(false);
        tblClassification.getColumnModel().getColumn(2).setResizable(false);
        scrollPane.setViewportView(tblClassification);

        pnl1 = new JPanel();
        pnl1.setBounds(15, 114, 193, 340);
        getContentPane().add(pnl1);
        pnl1.setLayout(null);

        btnBids = new JButton("Pujas");
        btnBids.addActionListener(e -> {
            goToView(new SeeBids(manager, league));
        });
        btnBids.setBounds(0, 0, 193, 170);
        pnl1.add(btnBids);

        btnMenu = new JButton("Men\u00FA");
        btnMenu.addActionListener(e -> {
            goToView(new Menu(manager));
        });
        btnMenu.setBounds(0, 170, 193, 170);
        pnl1.add(btnMenu);

        pnl2 = new JPanel();
        pnl2.setLayout(null);
        pnl2.setBounds(619, 114, 193, 340);
        getContentPane().add(pnl2);

        btnSearchPlayer = new JButton("Buscar jugador");
        btnSearchPlayer.addActionListener(e -> {
            goToView(new SearchPlayer(league, manager));
        });
        btnSearchPlayer.setBounds(0, 0, 193, 170);
        pnl2.add(btnSearchPlayer);

        btnLineUp = new JButton("Alineaci\u00F3n");
        btnLineUp.addActionListener(e -> {
            goToView(new LineUp(league,manager));
        });
        btnLineUp.setBounds(0, 170, 193, 170);
        pnl2.add(btnLineUp);

        lblFantasyGame = new JLabel("Fantasy Game");
        lblFantasyGame.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
        lblFantasyGame.setOpaque(true);
        lblFantasyGame.setHorizontalAlignment(SwingConstants.CENTER);
        lblFantasyGame.setFont(new Font("Trebuchet MS", Font.BOLD, 25));
        lblFantasyGame.setBackground(SystemColor.activeCaption);
        lblFantasyGame.setBounds(15, 55, 797, 51);
        getContentPane().add(lblFantasyGame);

        btnAbandon = new JButton("Abandonar");
        btnAbandon.addActionListener(e -> {
            if(JOptionPane.showConfirmDialog(null,
                    "¿Estás seguro de que quieres abandonar la liga?") == 0){
                league.getTeam(manager).delete();
                if(league.getTeamsList().size() < 2){
                    league.delete();
                }
                goToView(new Menu(manager));
            }
        });
        btnAbandon.setBounds(467, 466, 137, 29);
        getContentPane().add(btnAbandon);

        progressBar = new JProgressBar();
        progressBar.setMaximum(8);
        progressBar.setValue(DataExtraction.getNextRound()-1);
        progressBar.setBounds(223, 424, 381, 30);
        getContentPane().add(progressBar);

        setTable();
        setAllFormats(getContentPane());
    }

    /**Fills the table with the classification of the team, ordering the teams taking into accounts their points.
     */

    private void setTable(){
        ((DefaultTableModel) tblClassification.getModel()).setRowCount(0);
        DefaultTableModel model = (DefaultTableModel) tblClassification.getModel();
        ArrayList<Team> teamsList = league.getTeamsList();
        MergeSort myMergeSort = new MergeSort();
        myMergeSort.mergeSort(teamsList);
        for(int i = 0; i < league.getTeamsList().size(); i++){
            Team team = league.getTeamsList().get(i);
            model.addRow(new Object[]{i+1,team.getManager().getUsername(),team.getTotalPoints()});
        }
        setTableHeightAndCentreText(tblClassification);
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
}