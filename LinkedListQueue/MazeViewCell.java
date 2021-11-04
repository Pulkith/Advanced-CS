package LinkedListQueue;

import javax.swing.*;
import java.awt.*;

public class MazeViewCell extends JPanel {
    private static final long serialVersionUID = 1L;
    Square cell;
    private boolean won = false;
    public MazeViewCell(Square cell) {
        this.cell = cell;
        setVisible(true);
    }
    public void updateView(Square cell) {
        this.cell = cell;
        this.repaint();
    }

    public void setWinSquare() {
        this.won = true;
        this.repaint();
    }
    public void reset() { this.won = false; }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(cell.getType() == Type.WALL) this.setBackground(Color.BLACK);
        else if(cell.getType() == Type.START) this.setBackground(Color.GREEN);
        else if(cell.getType() == Type.END && cell.getStatus() != Status.CURRENT) {
            if(won) this.setBackground(Color.GREEN);
            else this.setBackground(Color.RED);
        }
        else {
            if(cell.getStatus() == Status.UNVISITED) this.setBackground(Color.white);
            else if(cell.getStatus() == Status.CURRENT) this.setBackground(Color.blue);
            else if(cell.getStatus() == Status.WORKING) this.setBackground(Color.CYAN);
            else if(cell.getStatus() == Status.VISITED) this.setBackground(Color.darkGray);
        }
        g.setColor(Color.black);
        drawCenteredString(g, cell.getType().toString(), new Rectangle(0, 0, 30, 30), new JLabel().getFont());
    }

    public void drawCenteredString(Graphics g, String text, Rectangle rect, Font font) {
        FontMetrics metrics = g.getFontMetrics(font);
        int x = rect.x + (rect.width - metrics.stringWidth(text)) / 2;
        int y = rect.y + ((rect.height - metrics.getHeight()) / 2) + metrics.getAscent();
        g.setFont(font);
        g.drawString(text, x, y);
    }
}
