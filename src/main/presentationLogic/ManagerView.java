package main.presentationLogic;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.Font;
import javax.swing.UIManager;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ManagerView extends JFrame {

    private JPanel contentPane;
    private JMenuBar menuBar;
    private JMenu mnOptions;
    private JMenuItem mntmLogOut;
    private JMenuItem mntmExit;
    private JMenuItem mntmMainMenu;


    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ManagerView frame = new ManagerView();
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
    public ManagerView() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 800, 500);

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
        mntmMainMenu.setHorizontalAlignment(SwingConstants.CENTER);
        mntmMainMenu.setBackground(UIManager.getColor("MenuItem.background"));
        mntmMainMenu.setFont(new Font("Trebuchet MS", Font.PLAIN, 18));
        menuBar.add(mntmMainMenu);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
    }

    public void goToView(JFrame newView){
        newView.setVisible(true);
        ManagerView.this.dispose();
    }

}