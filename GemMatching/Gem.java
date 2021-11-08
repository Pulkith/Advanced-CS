package GemMatching;

import java.awt.Font;

public class Gem 
{	
	GemType color;
	int gemValue;
	Gem() {
		int color = (int)(Math.random() * 3);
		switch(color){
			case 0: this.color = GemType.GREEN; break;
			case 1: this.color = GemType.BLUE; break;
			case 2: this.color = GemType.ORANGE;
		}
		this.gemValue = (int)(Math.random() * 11) * 5;
	}
	Gem(GemType g, int pts) {
		this.color = g;
		this.gemValue = pts;

	}
	public String toString() {
		return  this.color + ",  " + this.gemValue;
	}
	GemType getType() { return this.color; }
	int getPoints() { return this.gemValue; }
	void draw(double x, double y) {
		String c = this.color.toString();
		StdDraw.picture(x, y, "gem_"+c.toLowerCase()+".png");
		StdDraw.text(x, y, ""+gemValue);
	}
	/** Tester main method */
	public static void main(String [] args)
	{
		final int maxGems = 16;
		
		// Create a gem of each type
		Gem green  = new Gem(GemType.GREEN, 10);
		Gem blue   = new Gem(GemType.BLUE, 20);
		Gem orange = new Gem(GemType.ORANGE, 30);
		System.out.println(green  + ", " + green.getType()  + ", " + green.getPoints());		
		System.out.println(blue   + ", " + blue.getType()   + ", " + blue.getPoints());
		System.out.println(orange + ", " + orange.getType() + ", " + orange.getPoints());
		green.draw(0.3, 0.7);
		blue.draw(0.5, 0.7);
		orange.draw(0.7, 0.7);
		
		// A row of random gems
		for (int i = 0; i < maxGems; i++)
		{
			Gem g = new Gem();
			g.draw(1.0 / maxGems * (i + 0.5), 0.5);
		}
	}
}
