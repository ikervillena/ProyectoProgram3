package main.presentationLogic.managerViews;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.SystemColor;
import javax.swing.border.LineBorder;
import main.dataLogic.league.Bid;
import main.dataLogic.league.League;
import main.dataLogic.league.Team;
import main.dataLogic.people.Manager;
import java.awt.Color;
import javax.swing.JOptionPane;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;

/**This frame allows the user to see the bids that the team has made and received.
 * @author Iker Villena Ona.
 */

public class SeeBids extends LeagueView {

    private League league1;
    private Manager manager;
    private Team team;
    private JPanel contentPane;
    private JPanel pnlReceivedBids;
    private JPanel pnlBidsMade;
    private JLabel lblBidsmade;
    private JLabel lblReceivedBids;
    private JButton btnAccept;
    private JButton btnReject;
    private JTable tblBidsMade;
    private JScrollPane scp1;
    private JButton btnDelete;
    private JTable tblReceivedBids;
    private JScrollPane scp2;
    private JLabel lblAvailablemoney1;
    private JLabel lblAvailablemoney;

    /**
     * Create the frame.
     */

    public SeeBids(Manager manager, League league) {
        super(league, manager);
        this.setTitle("Ver pujas");
        this.team = league.getTeam(manager);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        pnlBidsMade = new JPanel();
        pnlBidsMade.setBorder(new LineBorder(new Color(0, 0, 0)));
        pnlBidsMade.setBackground(SystemColor.inactiveCaptionBorder);
        pnlBidsMade.setBounds(35, 42, 361, 385);
        contentPane.add(pnlBidsMade);
        pnlBidsMade.setLayout(null);

        lblBidsmade = new JLabel("Ofertas realizadas");
        lblBidsmade.setBorder(new LineBorder(new Color(0, 0, 0)));
        lblBidsmade.setOpaque(true);
        lblBidsmade.setBackground(SystemColor.activeCaption);
        lblBidsmade.setHorizontalAlignment(SwingConstants.CENTER);
        lblBidsmade.setFont(new Font("Tahoma", Font.BOLD, 18));
        lblBidsmade.setBounds(0, 0, 361, 62);
        pnlBidsMade.add(lblBidsmade);

        scp1 = new JScrollPane();
        scp1.setBounds(33, 96, 294, 219);
        pnlBidsMade.add(scp1);

        tblBidsMade = new JTable();
        scp1.setViewportView(tblBidsMade);
        tblBidsMade.setModel(new DefaultTableModel(
                new Object[][] {}, new String[] {"Equipo", "Jugador", "Valor", "Oferta"}){
            Class[] columnTypes = new Class[] {String.class, String.class, Float.class, Float.class};
            public Class getColumnClass(int columnIndex) {
                return columnTypes[columnIndex];
            }
            boolean[] columnEditables = new boolean[] {false, false, false, false};
            public boolean isCellEditable(int row, int column) {
                return columnEditables[column];
            }});

        btnDelete = new JButton("Borrar");
        btnDelete.addActionListener(e -> {
            if(tblBidsMade.getSelectedRow()>=0){
                team.getBidsMade().get(tblBidsMade.getSelectedRow()).delete();
                setUp();
            } else{
                JOptionPane.showMessageDialog(null, "No has seleccionado ninguna puja");
            }
        });
        btnDelete.setBounds(195, 330, 132, 39);
        pnlBidsMade.add(btnDelete);
        tblBidsMade.getColumnModel().getColumn(0).setResizable(false);
        tblBidsMade.getColumnModel().getColumn(1).setResizable(false);
        tblBidsMade.getColumnModel().getColumn(2).setResizable(false);
        tblBidsMade.getColumnModel().getColumn(3).setResizable(false);

        pnlReceivedBids = new JPanel();
        pnlReceivedBids.setBorder(new LineBorder(new Color(0, 0, 0)));
        pnlReceivedBids.setBackground(SystemColor.inactiveCaptionBorder);
        pnlReceivedBids.setBounds(431, 42, 361, 385);
        contentPane.add(pnlReceivedBids);
        pnlReceivedBids.setLayout(null);

        lblReceivedBids = new JLabel("Ofertas recibidas");
        lblReceivedBids.setBorder(new LineBorder(new Color(0, 0, 0)));
        lblReceivedBids.setOpaque(true);
        lblReceivedBids.setBackground(SystemColor.activeCaption);
        lblReceivedBids.setHorizontalAlignment(SwingConstants.CENTER);
        lblReceivedBids.setFont(new Font("Tahoma", Font.BOLD, 18));
        lblReceivedBids.setBounds(0, 0, 361, 62);
        pnlReceivedBids.add(lblReceivedBids);

        btnAccept = new JButton("Aceptar");
        btnAccept.addActionListener(e -> {
            if(tblReceivedBids.getSelectedRow()>=0){
                Bid bid = team.getReceivedBids().get(tblReceivedBids.getSelectedRow());
                bid.accept();
                setUp();
            }else{
                JOptionPane.showMessageDialog(null, "No has seleccionado ninguna puja");
            }
        });
        btnAccept.setBounds(194, 329, 132, 40);
        pnlReceivedBids.add(btnAccept);

        btnReject = new JButton("Rechazar");
        btnReject.addActionListener(e -> {
            if(tblReceivedBids.getSelectedRow()>=0){
                Bid bid = team.getReceivedBids().get(tblReceivedBids.getSelectedRow());
                bid.delete();
                setUp();
            }else{
                JOptionPane.showMessageDialog(null, "No has seleccionado ninguna puja");
            }
        });
        btnReject.setBounds(35, 329, 132, 40);
        pnlReceivedBids.add(btnReject);

        scp2 = new JScrollPane();
        scp2.setBounds(35, 101, 291, 212);
        pnlReceivedBids.add(scp2);

        tblReceivedBids = new JTable();
        scp2.setViewportView(tblReceivedBids);
        tblReceivedBids.setModel(new DefaultTableModel(
                new Object[][] {}, new String[] {"Equipo", "Jugador", "Valor", "Oferta"}){
            Class[] columnTypes = new Class[] {String.class, String.class, Float.class, Float.class};
            public Class getColumnClass(int columnIndex) {return columnTypes[columnIndex];}
            boolean[] columnEditables = new boolean[] {false, false, false, false};
            public boolean isCellEditable(int row, int column) {return columnEditables[column];}});

        lblAvailablemoney1 = new JLabel("Dinero disponible");
        lblAvailablemoney1.setBorder(new LineBorder(new Color(0, 0, 0)));
        lblAvailablemoney1.setHorizontalAlignment(SwingConstants.CENTER);
        lblAvailablemoney1.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblAvailablemoney1.setOpaque(true);
        lblAvailablemoney1.setBackground(SystemColor.activeCaption);
        lblAvailablemoney1.setBounds(35, 455, 162, 40);
        contentPane.add(lblAvailablemoney1);

        lblAvailablemoney = new JLabel("");
        lblAvailablemoney.setBorder(new LineBorder(new Color(0, 0, 0)));
        lblAvailablemoney.setOpaque(true);
        lblAvailablemoney.setHorizontalAlignment(SwingConstants.CENTER);
        lblAvailablemoney.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblAvailablemoney.setBackground(SystemColor.inactiveCaptionBorder);
        lblAvailablemoney.setBounds(196, 455, 162, 40);
        contentPane.add(lblAvailablemoney);
        setUp();
        setAllFormats(contentPane);
    }

    /**Fills the list of received bids.
     */

    private void setList(){
        DefaultListModel<Bid> model = new DefaultListModel<>();
        team.getReceivedBids()
                .stream()
                .forEach(bid -> model.addElement(bid));
    }

    /**Fills the tables with the bids made and received and shows the available money that the team has.
     */

    private void setUp(){
        ((DefaultTableModel) tblBidsMade.getModel()).setRowCount(0);
        DefaultTableModel model = (DefaultTableModel) tblBidsMade.getModel();
        for(Bid b : team.getBidsMade()){
            String teamName = "Sin equipo";
            if(b.getCurrentTeam() != null){
                teamName = b.getCurrentTeam().getManager().getUsername();
            }
            model.addRow(new Object[]{teamName,b.getPlayer().getShirtName(),
                    b.getPlayer().getLastValue(),b.getFee()});
        }
        setFormat(tblBidsMade);
        ((DefaultTableModel) tblReceivedBids.getModel()).setRowCount(0);
        DefaultTableModel model1 = (DefaultTableModel) tblReceivedBids.getModel();
        for(Bid b : team.getReceivedBids()){
            String teamName = "Sin equipo";
            if(b.getInterestedTeam() != null){
                teamName = b.getInterestedTeam().getManager().getUsername();
            }
            model1.addRow(new Object[]{teamName,b.getPlayer().getShirtName(),
                    b.getPlayer().getLastValue(),b.getFee()});
        }
        lblAvailablemoney.setText(Float.toString(Math.round(team.getAvailableMoney())));
        setFormat(tblReceivedBids);
    }
}