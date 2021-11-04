package PrayagMazeSolver;
import java.util.*;
public abstract class MazeSolver{
    public Maze maze;
    public MyStack stack;
    boolean isSolved = false;
    boolean isSolveable = true;
    public MazeSolver(Maze maze){
        this.maze = maze;
        stack = new MyStack();
        add(maze.getStart());
    }
    public abstract void makeEmpty();
    public abstract boolean isEmpty();
    public abstract void add(Square s);
    public abstract Square next();
    public abstract int size();
    public boolean isSolved() {
        return isSolved;
    }
    public void step(){
        if(isEmpty()) {
            isSolveable = false;
            isSolved = true;
            return;
        }
        Square cur = stack.pop();
        List<Square> square = maze.getNeighbors(cur);
        cur.setStatus('.');
        if(cur.equals(maze.getExit())) {
            isSolved = true;
            isSolveable = true;
            return;
        }

        for(int i=0;i<square.size();i++){
            if(square.get(i).getType() == 0 || square.get(i).getType() == 3){
                if(square.get(i).getStatus() == '_') {
                    square.get(i).setStatus('o');
                    add(square.get(i));
                } else if(square.get(i).equals(maze.getExit())) {
                    add(square.get(i));
                }
            }

        }

    }
    public String getPath()
    {
        if(!isSolveable)
            return "unsolvable";
        else if (isSolved)
            return"solved";
        else
            return "not yet solved";
    }
    void solve() {
        makeEmpty();
        stack.push(maze.getStart());
        while(!isSolved) {
            step();
        }
    }

}