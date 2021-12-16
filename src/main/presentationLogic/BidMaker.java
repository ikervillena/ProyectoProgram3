package main.presentationLogic;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import main.businessLogic.Bid;
import main.businessLogic.interfaces.INewData;
import main.dataLogic.league.League;
import main.dataLogic.league.Team;
import main.dataLogic.people.Manager;
import main.dataLogic.people.Player;
import main.dbManagement.DataExtraction;
import main.dbManagement.DataInsertion;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class BidMaker extends JFrame implements INewData {

    private Player player;
    private League league;
    private Manager manager;
    private JPanel contentPane;
    private JLabel lblPlayer;
    private JLabel lblTeam1;
    private JLabel lblTeam;
    private JLabel lblValue1;
    private JLabel lblValue;
    private JLabel lblOffer;
    private JSpinner spnFee;
    private JButton btnBid;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    BidMaker frame = new BidMaker(DataExtraction.getPlayer(1),DataExtraction.getAllLeagues().get(0),
                            DataExtraction.getAllManagers().get(0));
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
    public BidMaker(Player player,League league, Manager manager) {
        this.player = player;
        this.league = league;
        this.manager = manager;
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 500, 500);
        setLocationRelativeTo(null);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        lblPlayer = new JLabel("<Name> <Surname>");
        lblPlayer.setHorizontalAlignment(SwingConstants.CENTER);
        lblPlayer.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblPlayer.setBounds(15, 57, 448, 50);
        contentPane.add(lblPlayer);

        lblTeam1 = new JLabel("Equipo:");
        lblTeam1.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblTeam1.setBounds(51, 139, 171, 36);
        contentPane.add(lblTeam1);

        lblTeam = new JLabel("");
        lblTeam.setHorizontalAlignment(SwingConstants.CENTER);
        lblTeam.setBounds(237, 139, 171, 36);
        contentPane.add(lblTeam);

        lblValue1 = new JLabel("Valor (millones):");
        lblValue1.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblValue1.setBounds(51, 191, 171, 36);
        contentPane.add(lblValue1);

        lblValue = new JLabel("");
        lblValue.setHorizontalAlignment(SwingConstants.CENTER);
        lblValue.setBounds(237, 191, 171, 36);
        contentPane.add(lblValue);

        lblOffer = new JLabel("Oferta (millones):");
        lblOffer.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblOffer.setBounds(51, 243, 171, 36);
        contentPane.add(lblOffer);

        spnFee = new JSpinner();
        spnFee.setModel(new SpinnerNumberModel(new Float(0), null, null, new Float(1)));
        spnFee.setBounds(237, 243, 171, 36);
        contentPane.add(spnFee);

        btnBid = new JButton("");
        btnBid.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(checkFields()){
                    Bid bid = new Bid(fromTeam(),player.getTeam(league), player,(float) spnFee.getValue());
                    bid.save();
                    JOptionPane.showMessageDialog(null,"La oferta ha sido guardada con éxito.");
                    BidMaker.this.dispose();
                }
            }
        });
        btnBid.setBounds(181, 339, 115, 29);
        contentPane.add(btnBid);
        setUp();
    }

    private void setUp(){
        lblPlayer.setText(player.getName()+" "+player.getSurname());
        Team team = player.getTeam(league);
        if(team == null){
            lblTeam.setText("Sin equipo");
            btnBid.setText("Pujar");
        } else{
            lblTeam.setText(team.getManager().getName());
            btnBid.setText("Hacer oferta");
        }
        lblValue.setText(Float.toString(player.getLastValue()));
        spnFee.setValue(player.getLastValue());
    }

    /**Provides tha manager's team, which is the one making the bid.
     * @return Team that is managed by the Manager.
     */

    private Team fromTeam(){
        return league.getTeam(manager);
    }



    @Override
    public boolean checkFields() {
        boolean correct = true;
        if (player.getLastValue() > (float) spnFee.getValue()) {
            JOptionPane.showMessageDialog(null, "La oferta debe ser igual o superior al valor del jugador.");
            correct = false;
        } else {
            if ((float) spnFee.getValue() > league.getTeam(manager).getAvailableMoney()) {
                JOptionPane.showMessageDialog(null, "No dispones del dinero necesario para realizar esa oferta.");
                correct = false;
            } else {
                if(league.getTeam(manager).madeOffer(player)){
                    JOptionPane.showMessageDialog(null, "Ya has pujado por este jugador.");
                    correct = false;
                }
            }
        }
        return correct;
    }

}