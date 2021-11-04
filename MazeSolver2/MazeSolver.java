//package MazeSolver2;
//
//import java.awt.*;
//import java.util.ArrayList;
//import java.util.List;
//
//abstract public class MazeSolver {
//    private Maze maze;
//    private MazeViewCell pg[][];
//    private Square CurrentCell;
//    private int cellsVisited = 1;
//    private int cellsAdded = 1;
//    public MazeSolver(Maze maze, MazeViewCell[][] pg) {
//        this.maze = maze;
//        this.pg = pg;
//        this.CurrentCell = null;
//    }
//    abstract void makeEmpty();
//    abstract void add(Square arg);
//    abstract boolean isEmpty();
//    abstract Square next();
//    abstract Square peekNext();
//    abstract int size();
//    boolean isSolved() {
//        return this.maze.solved;
//    }
//    void step() {
//        if(isEmpty()) {
//            this.maze.solved = true;
//            this.maze.isSolvable = false;
//        }
//        else {
//            ++cellsVisited;
//            Square next = next();
//            if(next.equals(this.maze.getEnd())) {
//                this.maze.solved = true;
//                this.maze.isSolvable = true;
//                maze.getEnd().setStatus(Status.VISITED);
//                pg[maze.getEnd().getRow()][maze.getEnd().getCol()].setWinSquare();
//            } else {
//                List<Square> neighbors = maze.getNeighbors(next);
//                for(Square n : neighbors) {
//                    if((n.getType() == Type.EMPTY && n.getStatus() == Status.UNVISITED) || n.getType() == Type.END) {
//                        ++cellsAdded;
//                        add(n);
//                        pg[n.getRow()][n.getCol()].updateView(n);
//                    }
//                }
//                if(!isEmpty()) {
//                    Square s = peekNext();
//                    s.setStatus(Status.CURRENT);
//                    pg[s.getRow()][s.getCol()].updateView(s);
//                }
//                next.setStatus(Status.VISITED);
//                pg[next.getRow()][next.getCol()].updateView(next);
//            }
//        }
//
//    }
//    String getPath() {
//        if(!maze.solved && !maze.isSolvable) return "Press Start to Solve";
//        if(!maze.solved) {
//            if(!isEmpty()) {
//                return "Solving @ [" + peekNext().getRow() + " " + peekNext().getCol() + "]" + "\n" + size() + " Cells in Stack.\n" + cellsVisited + " Cells Visited.\n" + cellsAdded + " Cells Added to Stack.";
//            } else {
//                return "Solving & [-, -]" + "\n0 Cells in Stack.\n" + cellsVisited + " Cells Visited.\n" + cellsAdded + " Cells Added to Stack.";
//            }
//        }
//         if(maze.isSolvable) return "Solved" + "\n0 Cells in Stack.\n" + cellsVisited + " Cells Visited.\n" + cellsAdded + " Cells Added to Stack.";
//         return "Unsolvable" + "\n0 Cells in Stack.\n" + cellsVisited + " Cells Visited.\n" + cellsAdded + " Cells Added to Stack.";
//    }
//
//    abstract void solve();
//    void setInitialSolveState() {
//        this.maze.isSolvable = true;
//    }
//    Maze getMaze() { return this.maze; }
//
//
//
//}
