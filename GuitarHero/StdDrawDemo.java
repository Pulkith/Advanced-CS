package GuitarHero;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.util.Random;

public class StdDrawDemo 
{
	/** draw a single blue square in the middle of the screen */
	public static void drawSquare()
	{
		StdDraw.setPenColor(Color.BLUE);
		StdDraw.filledRectangle(0.5, 0.5, 0.1, 0.1); //default scale is <0, 0> to <1, 1>
	}
	
	/** draw a randomly colored circle at the location of the mouse click */
	public static void drawCircleOnClick()
	{
		Random rng = new Random(); //use Random to avoid having to cast Math.random() a bunch
		
		while (true)
		{
			if (StdDraw.isMousePressed()) {
				StdDraw.setPenColor(new Color(rng.nextInt(256), rng.nextInt(256), rng.nextInt(256)));
				StdDraw.filledCircle(StdDraw.mouseX(), StdDraw.mouseY(), rng.nextDouble() / 4);
				while (StdDraw.isMousePressed()); //prevent making multiple circles per click
			}
		}
	}
	
	/** draw two red circles, spinning around in a circle.  direction controlled by left and right arrows */
	public static void spinningCircles()
	{
		StdDraw.setScale(-2, +2);        //set scale of drawing window
		StdDraw.enableDoubleBuffering(); //enable drawing to offscreen buffer for smooth animation
		StdDraw.setPenColor(Color.RED);  //set drawing (pen) color
		
		boolean right = true;
		
		double t = 0.0;

		while (true)
		{
			//get  direction of next rotation with left and right arrows
			if (StdDraw.isKeyPressed(KeyEvent.VK_LEFT))
				right = false;
			else if (StdDraw.isKeyPressed(KeyEvent.VK_RIGHT))
				right = true;
			
			double x = Math.sin(t); //calculate current x and y locations
			double y = Math.cos(t);
			
			StdDraw.clear();
			StdDraw.filledCircle(x, y, 0.2);
			StdDraw.filledCircle(-x, -y, 0.2);
			StdDraw.show();    //show offscreen buffer on screen
			StdDraw.pause(10); //wait 20ms before going to next frame
			
			if (right)     //
				t += 0.02;
			else
				t -= 0.02;
		}
	}
	
	public static void main(String[] args) 
	{
//		drawSquare();
//		drawCircleOnClick();
//		spinningCircles();
	}
}