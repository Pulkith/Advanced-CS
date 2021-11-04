package MazeSolver;

import java.util.ArrayList;

public class MazeSolverStack extends MazeSolver{
    MyStack worklist = new MyStack();
    public MazeSolverStack(Maze m) {
        super(m);
    }
    @Override
    void makeEmpty() {
        worklist.clear();
    }

    @Override
    void add(Square arg) {
        arg.setStatus(Status.WORKING);
        worklist.push(arg);
    }

    @Override
    boolean isEmpty() {
        return worklist.isEmpty();
    }

    @Override
    Square next() {
        return worklist.pop();
    }
    @Override
    Square peekNext() {
        return worklist.peek();
    }
    @Override
    int size() {
        return worklist.size();
    }
    void solve() {
        makeEmpty();
        worklist.push(getMaze().getStart());
    }
}
