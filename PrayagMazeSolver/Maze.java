package PrayagMazeSolver;

import java.util.*;
import java.io.*;

public class Maze{
    private Square[][]maze;
    private int row;
    private int col;
    private Square start;
    private Square stop;
    public Maze()
    {

    }
    public boolean loadMaze(String fileName){
        try{
            Scanner scan = new Scanner(new File(fileName));
            this.row = Integer.parseInt(scan.next());
            this.col = Integer.parseInt(scan.next());
            maze = new Square[row][col];
            while (scan.hasNext()) {
                for(int y = 0; y<row; y++){
                    for(int x = 0; x<col;x++){
                        int data = Integer.parseInt(scan.next());
                        maze[y][x] = new Square(y,x,data);
                    }
                }
            }
            return true;
        }
        catch(Exception e){
            return false;
        }
    }
    public List<Square> getNeighbors(Square s){
        ArrayList<Square> neighbors = new ArrayList <Square>();
        if(s.getRow()+1<row){
            neighbors.add(maze[s.getRow()+1][s.getCol()]);

        }
        if(s.getRow()-1>=0){
            neighbors.add(maze[s.getRow()-1][s.getCol()]);
        }
        if(s.getCol()+1<col){
            neighbors.add( maze[s.getRow()][s.getCol()+1]);
        }
        if(s.getCol()-1>=0){
            neighbors.add(maze[s.getRow()][s.getCol()-1]);
        }
        return neighbors;
    }
    public Square getStart(){

        for(int y = 0; y< this.row; y++){
            for(int x = 0; x< this.col;x++)
            {
                if(maze[y][x].getType()==2){
                    start = maze[y][x];
                    break;
                }
            }
        }

        return start;
    }
    public Square getExit(){
        Square exit = null;
        for(int y = 0; y< this.row; y++){
            for(int x = 0; x< this.col;x++)
            {
                if(maze[y][x].getType()==3){
                    return maze[y][x];
                }
            }
        }
        return null;
    }
    public void reset(){

        for(int y = 0; y< this.row; y++){
            for(int x = 0; x< this.col;x++)
            {
                if(maze[y][x].getType()==0){
                    maze[y][x]=new Square(y,x,0);
                }
            }
        }
    }
    public String toString(){
        String str = "";
        for(int y = 0; y< this.row; y++){
            for(int x = 0; x< this.col;x++)
            {
                str += maze[y][x].toString() + " ";
            }
            str += "\n";
        }
        return str;
    }
}