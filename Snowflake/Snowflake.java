package Snowflake;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

class SnowFlakePanel extends JPanel
{
	public SnowFlakePanel()
	{
		super.setPreferredSize(new Dimension(400, 400));
		super.setBackground(Color.WHITE);
	}

	public void paintComponent(Graphics g)
	{
		int width  = getWidth();
		int height = getHeight();

		super.paintComponent(g);

		blizzard(g, 20);
		g.setColor(Color.BLUE);
	}
	public int num(int limit) { return (int)(Math.random() * (limit + 1)); }
	public void blizzard(Graphics g, int num) {
		for(int i = 0; i < num; ++i) {
			Color c = new Color(num(255), num(255), num(255));
			g.setColor(c);

			int x = num(this.getWidth() - 1);
			int y = num(this.getHeight() - 1);

			int linelength = this.getHeight() / (this.num(10) + 3);
			drawSnowFlake(g, x, y, linelength);
		}
	}

	public void drawSnowFlake(Graphics g, int cX, int cY, int lineLength) {
		if(lineLength < getHeight() / 50) return;
		int height = (int)(lineLength / 2.0);
		g.drawLine(cX - height, cY, cX + height, cY);
		int dX = (int)(height * Math.cos(Math.toRadians(60)));
		int dY = (int)(height * Math.sin(Math.toRadians(60)));
		g.drawLine(cX - dX, cY + dY, cX + dX, cY - dY);
		g.drawLine(cX - dX, cY - dY, cX + dX, cY + dY);
		int[] dx = {cX - dX, cX - dX, cX - dX * 2, cX + dX * 2, cX + dX, cX + dX};
		int[] dy = {cY - dY, cY + dY, cY, cY, cY - dY, cY + dY};
		for(int i = 0; i < 6; ++i)
			drawSnowFlake(g, dx[i], dy[i], lineLength / 3);

	}
}

public class Snowflake
{
	public static void main ( String[] args )
	{
		/*
		 * A frame is a container for a panel
		 * The panel is where the drawing will take place
		 */
		JFrame frame = new JFrame("Snowflake");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(new SnowFlakePanel());
		frame.pack();
		frame.setVisible(true);
	}
}
