package main.presentationLogic.managerViews;

import main.dataLogic.people.Player;
import main.dbManagement.DataExtraction;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.util.Arrays;

/**This frame allows the user to see a graphical representation of a player's value evolution.
 * @author Iker Villena Ona.
 */

public class PlayerValue extends JFrame {

    private JPanel contentPane;
    private float[] valueEvolution;
    private JLabel lblPlayername;

    /**
     * Create the frame.
     */

    public PlayerValue(Player player) {
        this.setTitle("Evolución de valor");
        this.valueEvolution =Arrays.copyOfRange(player.getValueHistory(),0,player.getStatsRecord().size()+1);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 500, 500);
        setLocationRelativeTo(null);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        lblPlayername = new JLabel(player.getName()+" "+player.getSurname()+" -  Evoluci\u00F3n de valor");
        lblPlayername.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblPlayername.setHorizontalAlignment(SwingConstants.CENTER);
        lblPlayername.setBounds(15, 16, 448, 65);
        contentPane.add(lblPlayername);
    }

    /**Provides the maximum value that the player has ever had.
     * @return float with the maximum value.
     */

    private float getMaxValue(){
        float maxValue = 0;
        for(int i = 0; i < valueEvolution.length; i++){
            if(valueEvolution[i] > maxValue){
                maxValue = valueEvolution[i];
            }
        }
        return maxValue;
    }

    /**Draws a graphic with the player's value evolution.
     * @param g Graphics to draw the graphic.
     */

    @Override
    public void paint(Graphics g){
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;

        g2d.setColor(Color.WHITE);
        g2d.fillRect(50, 150, 400, 300);
        g2d.setStroke(new BasicStroke(4));
        g2d.setColor(Color.BLACK);
        g2d.drawRect(50, 150, 400, 300);
        g2d.setStroke(new BasicStroke(3));
        g2d.setFont(new Font("Tahoma", Font.PLAIN, 14));
        g2d.drawString(String.valueOf(0),47,475);
        if(valueEvolution.length == 1){
            g2d.drawString(String.valueOf(valueEvolution[0]),14,303);
            g2d.drawString(String.valueOf(0),447,475);
            g2d.setColor(Color.BLUE);
            g2d.drawLine(50, 300, 450, 300);
        } else{
            g2d.drawString(String.valueOf(valueEvolution[0]),14,453-valueEvolution[0]*270/getMaxValue());
            for(int i = 1; i < valueEvolution.length; i++){
                g2d.drawString(String.valueOf(valueEvolution[i]),14,453-valueEvolution[i]*270/getMaxValue());
                g2d.drawString(String.valueOf(i),47+i*400/(valueEvolution.length-1),475);
                g2d.setColor(Color.BLUE);
                int x1 = (int) ((i-1)*400/ (valueEvolution.length-1));
                int y1 = (int) (valueEvolution[i-1]*270/getMaxValue());
                int x2 = (int) (i*400/ (valueEvolution.length-1));
                int y2 = (int) (valueEvolution[i]*270/getMaxValue());
                g2d.drawLine(50+x1, (int) (150+300-y1), 50+x2, (int) (150+300-y2));
                g2d.setColor(Color.BLACK);
            }
        }
    }

}
