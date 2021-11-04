package GameOfLife;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.Timer;

public class LifeModel implements ActionListener
{

	/*
	 *  This is the Model component.
	 */

	private static int SIZE = 60;
	private LifeCell[][] grid;
	
	LifeView myView;
	Timer timer;

	/** Construct a new model using a particular file */
	public LifeModel(LifeView view, String fileName) throws IOException
	{       
		this.reset(view, fileName);

	}
	/** Constructor a randomized model */
	public LifeModel(LifeView view) throws IOException
	{
		this(view, null);
	}
	/** pause the simulation (the pause button in the GUI */
	public void pause()
	{
		timer.stop();
	}
	/** resume the simulation (the pause button in the GUI */
	public void resume()
	{
		timer.restart();
	}
	public void reset(LifeView view, String fileName) throws IOException {
		int r, c;
		grid = new LifeCell[SIZE][SIZE];
		for ( r = 0; r < SIZE; r++ )
			for ( c = 0; c < SIZE; c++ )
				grid[r][c] = new LifeCell();

		if ( fileName == null ) //use random population
		{
			for ( r = 0; r < SIZE; r++ )
			{
				for ( c = 0; c < SIZE; c++ )
				{
					if ( Math.random() > 0.85) //15% chance of a cell starting alive
						grid[r][c].setAliveNow(true);
				}
			}
		}
		else
		{
			Scanner input = new Scanner(new File(fileName));
			int numInitialCells = input.nextInt();
			for (int count=0; count<numInitialCells; count++)
			{
				r = input.nextInt();
				c = input.nextInt();
				grid[r][c].setAliveNow(true);
			}
			input.close();
		}

		myView = view;
		myView.updateView(grid);
	}
	/** run the simulation (the pause button in the GUI */
	public void run() {
		timer = new Timer(50, this);
		timer.setCoalesce(true);
		timer.start();
	}

	/** called each time timer fires */
	public void actionPerformed(ActionEvent e)
	{
		oneGeneration();
		myView.updateView(grid);
	}

	/** main logic method for updating the state of the grid / simulation */
	private void oneGeneration(){
		LifeCell[][] nextgrid = new LifeCell[SIZE][SIZE];
		
		for(int i = 0; i < SIZE; ++i) {
			for(int j = 0; j < SIZE; ++j) {
				nextgrid[i][j] = new LifeCell();
				nextgrid[i][j].setAliveNow(false);
				int neighbors = 0;
				int[] dx = new int[]{-1, -1, -1, 0, 1, 1, 1, 0};
				int[] dy = new int[]{-1, 0, 1, 1, 1, 0, -1, -1};
				for(int k = 0; k < dx.length; ++k) {
					int x = i + dx[k], y = j + dy[k];
					neighbors += (x >= 0 && x < SIZE && y >= 0 && y < SIZE && grid[x][y].isAliveNow()) ? 1 : 0;
				}
				if((!grid[i][j].isAliveNow() && neighbors == 3) || grid[i][j].isAliveNow() && neighbors >= 2 && neighbors <= 3)
					nextgrid[i][j].setAliveNow(true);
			}
		}

		grid = nextgrid;
	}
}
