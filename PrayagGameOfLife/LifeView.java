package PrayagGameOfLife;

import java.awt.*;
import java.util.HashMap;
import javax.swing.*;

/** The view (graphical) component */
public class LifeView extends JPanel
{
    private static final long serialVersionUID = 1L;
    private static int SIZE = 60;

    /** store a reference to the current state of the grid */
    private LifeCell[][] grid;
    HashMap<String, Color> colors = new HashMap<>();
    private Color selectedColor;
    private boolean randcol = false;
    private int num = 0;
    public LifeView()
    {
        grid = new LifeCell[SIZE][SIZE];
        colors.put("Default", Color.BLUE);
        colors.put("Random", new Color(randomNumber(255), randomNumber(255), randomNumber(255)));
        colors.put("White", new Color(235,235,255));
        selectedColor = colors.get("Default");
    }

    /** entry point from the model, requests grid be redisplayed */
    public void updateView( LifeCell[][] mg )
    {
        grid = mg;
        repaint();
    }

    public void RandomColor() {
        randcol = !randcol;
        if(randcol) {
            colors.put("Random", new Color(randomNumber(255), randomNumber(255), randomNumber(255)));
            this.selectedColor = this.colors.get("Random");
        }
        else
            this.selectedColor = colors.get("Default");

    }

    public void randomColor(String color) {
        try {
            this.selectedColor = this.colors.get("Random");
            //this.repaint();
        } catch (Exception ex) {};
    }

    public int randomNumber(int max) {
        return (int)(Math.random() * (max+1));
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        int testWidth = getWidth() / (SIZE+1);
        int testHeight = getHeight() / (SIZE+1);
        num++;
        // keep each life cell square
        int boxSize = Math.min(testHeight, testWidth);

        for ( int r = 0; r < SIZE; r++ )
        {
            for (int c = 0; c < SIZE; c++ )
            {
                if (grid[r][c] != null)
                {
                    if(grid[r][c].isAliveNow())
                        g.setColor(this.selectedColor);
                    else
                        g.setColor( colors.get("White") );



                    g.fillRect( (c+1)*boxSize, (r+1)* boxSize, boxSize-2, boxSize-2);
                }
            }
        }
    }
}