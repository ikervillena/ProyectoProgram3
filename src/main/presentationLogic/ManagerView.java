package main.presentationLogic;

import main.dataLogic.league.League;
import main.dataLogic.people.Manager;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**This class sets the template of all the frames that are aimed at managers (not administrators).
 * It sets the size, characteristics and the menu that is going to be needed in other frames.
 * @author Iker Villena Ona.
 */

public abstract class ManagerView extends JFrame {

    private JPanel contentPane;
    private JMenuBar menuBar;
    private JMenu mnOptions;
    private JMenuItem mntmLogOut;
    private JMenuItem mntmExit;
    private JMenuItem mntmMainMenu;
    private JMenuItem mntmSearchPlayer;
    private JMenuItem mntmSeeBids;
    private JMenuItem mntmNewMenuItem;

    public ManagerView(League league, Manager manager) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 850, 600);
        setLocationRelativeTo(null);

        menuBar = new JMenuBar();
        menuBar.setBackground(UIManager.getColor("MenuBar.background"));
        menuBar.setFont(new Font("Trebuchet MS", Font.PLAIN, 18));
        setJMenuBar(menuBar);

        mnOptions = new JMenu("Opciones");
        mnOptions.setBackground(UIManager.getColor("MenuItem.background"));
        mnOptions.setFont(new Font("Trebuchet MS", Font.PLAIN, 18));
        menuBar.add(mnOptions);

        mntmLogOut = new JMenuItem("Cerrar sesi\u00F3n");
        mntmLogOut.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                goToView(new Login());
            }
        });

        mntmSearchPlayer = new JMenuItem("Buscar jugador");
        mntmSearchPlayer.setFont(new Font("Trebuchet MS", Font.PLAIN, 18));
        mntmSearchPlayer.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                goToView(new PlayerInfo(league,manager));
            }
        });
        mnOptions.add(mntmSearchPlayer);

        mntmSeeBids = new JMenuItem("Ver pujas");
        mntmSeeBids.setFont(new Font("Trebuchet MS", Font.PLAIN, 18));
        mntmSeeBids.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                goToView(new SeeBids(manager,league));
            }
        });
        mnOptions.add(mntmSeeBids);

        mntmNewMenuItem = new JMenuItem("Alineaci\u00F3n");
        mntmNewMenuItem.setFont(new Font("Trebuchet MS", Font.PLAIN, 18));
        mntmNewMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                goToView(new LineUp(league,manager));
            }
        });
        mnOptions.add(mntmNewMenuItem);
        mntmLogOut.setBackground(UIManager.getColor("MenuItem.background"));
        mntmLogOut.setFont(new Font("Trebuchet MS", Font.PLAIN, 18));
        mnOptions.add(mntmLogOut);

        mntmExit = new JMenuItem("Salir");
        mntmExit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ManagerView.this.dispose();
            }
        });
        mntmExit.setBackground(UIManager.getColor("MenuItem.background"));
        mntmExit.setFont(new Font("Trebuchet MS", Font.PLAIN, 18));
        mnOptions.add(mntmExit);

        mntmMainMenu = new JMenuItem("Men\u00FA principal");
        mntmMainMenu.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                goToView(new Menu(manager));
            }
        });
        mntmMainMenu.setHorizontalAlignment(SwingConstants.CENTER);
        mntmMainMenu.setBackground(UIManager.getColor("MenuItem.background"));
        mntmMainMenu.setFont(new Font("Trebuchet MS", Font.PLAIN, 18));
        menuBar.add(mntmMainMenu);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
    }

    /**This method disposes this frame and sets visible the one provided as a parameter.
     * @param newView JFrame that needs to be set visible.
     */

    public void goToView(JFrame newView){
        newView.setVisible(true);
        ManagerView.this.dispose();
    }

    /**Applies a specific format for a component provided.
     */

    private void setComponentFormat(Component component){
        if(component instanceof JTable){
            setFormat((JTable) component);
        } else{
            if(component instanceof JTextField){
                setFormat((JTextField) component);
            } else{
                if(component instanceof JButton){
                    setFormat((JButton) component);
                } else{
                    if(component instanceof JList){
                        setFormat((JList) component);
                    }
                }
            }
        }

    }

    /**Establishes a format for each of the components of the Container using Recursion.
     * @param container Container whose Components need to be formatted.
     */

    public void setAllFormats(Container container){
        if(container.getComponents().length>0){
            for(Component c : container.getComponents()){
                if(c instanceof Container){
                    setAllFormats((Container) c);
                }
                setComponentFormat(c);
            }
        }
    }

    /**This method sets a table's row's height and centres the text.
     * @param table A JTable whose format needs to be changed.
     */

    private void setFormat(JTable table){
        for(int i = 0; i < table.getModel().getRowCount(); i ++){
            table.setRowHeight(i , 30);
        }
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.CENTER);
        for(int i = 0; i < table.getModel().getColumnCount(); i++){
            table.getColumnModel().getColumn(i).setCellRenderer(tcr);
        }
    }

    /**Sets the format to a JTextField.
     * @param textField JTextField that needs to be formatted.
     */

    private void setFormat(@NotNull JTextField textField){
        textField.setFont(new Font("Trebuchet MS", Font.PLAIN,18));
    }

    /**Sets the format to a JButton.
     * @param button JButton that needs to be formatted.
     */

    private void setFormat(@NotNull JButton button){
        button.setBorder(new BevelBorder(BevelBorder.RAISED, SystemColor.infoText, SystemColor.controlShadow, null, null));
        button.setBackground(SystemColor.scrollbar);
        button.setFont(new Font("Tahoma", Font.PLAIN, 18));
    }

    /**Sets the format to a JList.
     * @param list JList that needs to be formatted.
     */

    private void setFormat(@NotNull JList list){
        DefaultListCellRenderer cellRenderer = (DefaultListCellRenderer) list.getCellRenderer();
        cellRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        list.setFont(new Font("Tahoma", Font.PLAIN, 14));
    }
}