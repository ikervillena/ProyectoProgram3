package main.presentationLogic.managerViews;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;

import main.businessLogic.Bid;
import main.businessLogic.interfaces.INewData;
import main.dataLogic.league.League;
import main.dataLogic.league.Team;
import main.dataLogic.people.Manager;
import main.dataLogic.people.Player;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;
import javax.swing.border.LineBorder;
import java.awt.Color;

/**This frame allows the users to make bids for signing up new players.
 * @author Iker Villena Ona.
 */

public class BidMaker extends JFrame implements INewData {

    private Player player;
    private League league;
    private Manager manager;
    private JPanel contentPane;
    private JPanel panel;
    private JLabel lblPlayer;
    private JLabel lblTeam1;
    private JLabel lblTeam;
    private JLabel lblValue1;
    private JLabel lblValue;
    private JLabel lblOffer;
    private JSpinner spnFee;
    private JButton btnBid;

    /**
     * Create the frame.
     */

    public BidMaker(Player player,League league, Manager manager) {
        this.setTitle("Hacer puja");
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
        lblPlayer.setBorder(new LineBorder(new Color(0, 0, 0)));
        lblPlayer.setOpaque(true);
        lblPlayer.setBackground(SystemColor.activeCaption);
        lblPlayer.setHorizontalAlignment(SwingConstants.CENTER);
        lblPlayer.setFont(new Font("Trebuchet MS", Font.BOLD, 20));
        lblPlayer.setBounds(38, 47, 397, 50);
        contentPane.add(lblPlayer);

        lblTeam = new JLabel("");
        lblTeam.setFont(new Font("Trebuchet MS", Font.PLAIN, 18));
        lblTeam.setHorizontalAlignment(SwingConstants.CENTER);
        lblTeam.setBounds(252, 139, 157, 36);
        contentPane.add(lblTeam);

        lblValue = new JLabel("");
        lblValue.setFont(new Font("Trebuchet MS", Font.PLAIN, 18));
        lblValue.setHorizontalAlignment(SwingConstants.CENTER);
        lblValue.setBounds(252, 191, 157, 36);
        contentPane.add(lblValue);

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
        btnBid.setBorder(new BevelBorder(BevelBorder.RAISED, SystemColor.infoText, SystemColor.controlShadow, null, null));
        btnBid.setBackground(SystemColor.scrollbar);
        btnBid.setFont(new Font("Tahoma", Font.PLAIN, 18));
        btnBid.setBounds(181, 339, 115, 29);
        contentPane.add(btnBid);

        panel = new JPanel();
        panel.setBackground(SystemColor.inactiveCaption);
        panel.setBounds(38, 97, 397, 302);
        contentPane.add(panel);
        panel.setLayout(null);

        spnFee = new JSpinner();
        spnFee.setFont(new Font("Trebuchet MS", Font.PLAIN, 18));
        spnFee.setBounds(218, 136, 152, 36);
        panel.add(spnFee);
        spnFee.setModel(new SpinnerNumberModel(new Float(0), null, null, new Float(1)));

        lblTeam1 = new JLabel("Equipo:");
        lblTeam1.setBounds(32, 31, 171, 36);
        panel.add(lblTeam1);
        lblTeam1.setFont(new Font("Trebuchet MS", Font.BOLD, 18));

        lblOffer = new JLabel("Oferta (millones):");
        lblOffer.setBounds(32, 136, 171, 36);
        panel.add(lblOffer);
        lblOffer.setFont(new Font("Trebuchet MS", Font.BOLD, 18));

        lblValue1 = new JLabel("Valor (millones):");
        lblValue1.setBounds(32, 83, 171, 36);
        panel.add(lblValue1);
        lblValue1.setFont(new Font("Trebuchet MS", Font.BOLD, 18));
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