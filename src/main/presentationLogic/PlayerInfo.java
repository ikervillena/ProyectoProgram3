package main.presentationLogic;

import java.awt.EventQueue;

import javax.swing.JPanel;

import main.dataLogic.people.Player;
import main.dbManagement.DataExtraction;

import javax.swing.JTextField;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;

/**This frame allows the user to search for information about the players.
 * @author Iker Villena Ona.
 */

public class PlayerInfo extends ManagerView {

    private JPanel contentPane;
    private JPanel pnlSearch;
    private JButton btnBtnsearch;
    private JTextField txtSearch;
    private JPanel pnlResults;
    private JScrollPane scrollPane;
    private JList listPlayers;
    private JButton btnShowplayer;
    private JLabel lblName1;
    private JLabel lblSurname1;
    private JLabel lblPosition1;
    private JLabel lblShirtNumber1;
    private JLabel lblName;
    private JLabel lblSurname;
    private JLabel lblPosition;
    private JLabel lblShirtnumber;



    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    PlayerInfo frame = new PlayerInfo();
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
    public PlayerInfo() {

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
        scrollPane.setBounds(44, 37, 304, 243);
        pnlResults.add(scrollPane);

        listPlayers = new JList();
        listPlayers.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                showPlayer();
            }
        });
        scrollPane.setViewportView(listPlayers);

        btnShowplayer = new JButton("Ver ficha");
        btnShowplayer.setBounds(233, 298, 115, 29);
        pnlResults.add(btnShowplayer);

        lblName1 = new JLabel("Nombre:");
        lblName1.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblName1.setBounds(427, 37, 117, 20);
        pnlResults.add(lblName1);

        lblSurname1 = new JLabel("Apellido");
        lblSurname1.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblSurname1.setBounds(427, 73, 117, 20);
        pnlResults.add(lblSurname1);

        lblPosition1 = new JLabel("Posici\u00F3n:");
        lblPosition1.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblPosition1.setBounds(427, 109, 117, 20);
        pnlResults.add(lblPosition1);

        lblShirtNumber1 = new JLabel("Dorsal:");
        lblShirtNumber1.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblShirtNumber1.setBounds(427, 145, 117, 20);
        pnlResults.add(lblShirtNumber1);

        lblName = new JLabel("");
        lblName.setBounds(573, 37, 171, 20);
        pnlResults.add(lblName);

        lblSurname = new JLabel("");
        lblSurname.setBounds(573, 73, 171, 20);
        pnlResults.add(lblSurname);

        lblPosition = new JLabel("");
        lblPosition.setBounds(573, 109, 171, 20);
        pnlResults.add(lblPosition);

        lblShirtnumber = new JLabel("");
        lblShirtnumber.setBounds(573, 145, 171, 20);
        pnlResults.add(lblShirtnumber);
    }

    /**Fills the list with the players that match with the search.
     */

    private void setPlayers(){
        DefaultListModel<Player> model = new DefaultListModel<>();
        for(Player p : DataExtraction.searchPlayer(txtSearch.getText())){
            model.addElement(p);
        }
        listPlayers.setModel(model);
    }

    /**Show the selected player's attributes, setting the needed text in the proper labels.
     */

    private void showPlayer(){
        Player selectedPlayer = (Player) listPlayers.getSelectedValue();
        if(selectedPlayer != null){
            lblName.setText(selectedPlayer.getName());
            lblSurname.setText(selectedPlayer.getSurname());
            lblPosition.setText(selectedPlayer.getPosition().getName());
            lblShirtnumber.setText(String.valueOf(selectedPlayer.getShirtNumber()));
        }
    }

}