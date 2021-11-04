package MazeSolver;


import java.util.List;

abstract public class MazeSolver {
    private Maze maze;
    public MazeSolver(Maze maze) {
        this.maze = maze;
    }

    abstract void makeEmpty();
    abstract void add(Square arg);
    abstract boolean isEmpty();
    abstract Square next();
    abstract Square peekNext();
    abstract int size();
    boolean isSolved() {
        return this.maze.solved;
    }
    void step() {
        if(isEmpty()) {
            this.maze.solved = true;
            this.maze.isSolvable = false;
        }
        else {
            Square next = next();
            if(next.equals(this.maze.getEnd())) {
                this.maze.solved = true;
                this.maze.isSolvable = true;
                maze.getEnd().setStatus(Status.VISITED);
            } else {
                List<Square> neighbors = maze.getNeighbors(next);
                for(Square n : neighbors) {
                    if((n.getType() == Type.EMPTY && n.getStatus() == Status.UNVISITED) || n.getType() == Type.END) {
                        add(n);
                    }
                }
                if(!isEmpty()) {
                    Square s = peekNext();
                    s.setStatus(Status.CURRENT);
                }
                next.setStatus(Status.VISITED);
            }
        }

    }
    String getPath() {
        if(!maze.solved && !maze.isSolvable) return "Press Start to Solve";
        if(!maze.solved) {
            return "Solving";
        }
         if(maze.isSolvable) return "Solved";
         return "Unsolvable";
    }

    abstract void solve();
    public Maze getMaze() { return this.maze; }

}
