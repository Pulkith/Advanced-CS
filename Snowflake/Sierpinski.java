package Snowflake;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

class SierpinskiPanel extends JPanel
{
    public SierpinskiPanel()
    {
        super.setPreferredSize(new Dimension(400, 400));
        super.setBackground(Color.WHITE);
    }

    public void paintComponent(Graphics g)
    {
        int width  = getWidth();
        int height = getHeight();

        super.paintComponent(g);
        g.setColor(Color.BLUE);
        drawSierpinski(g, width / 2, height / 2, width / 2);
    }

    public void drawSierpinski(Graphics g, int bX, int bY, int len) {
        if(len < 2) return;
        g.drawLine(bX, bY, bX - len, bY);
        g.drawLine(bX, bY, bX, bY - len);
        g.drawLine(bX - len, bY, bX, bY - len);

        drawSierpinski(g, bX - len / 2, bY + len / 2, len / 2);
        drawSierpinski(g, bX + len / 2, bY - len / 2, len / 2);
        drawSierpinski(g, bX - len / 2, bY - len / 2, len / 2);
    }
}

public class Sierpinski
{
    public static void main ( String[] args )
    {
        /*
         * A frame is a container for a panel
         * The panel is where the drawing will take place
         */
        JFrame frame = new JFrame("Snowflake");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new SierpinskiPanel());
        frame.pack();
        frame.setVisible(true);
    }
}

