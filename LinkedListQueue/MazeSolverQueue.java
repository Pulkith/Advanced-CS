package LinkedListQueue;

public class MazeSolverQueue extends MazeSolver {
    MyQueue worklist = new MyQueue<Square>();
    public MazeSolverQueue(Maze m, MazeViewCell[][] pg) {
        super(m, pg);
        this.solve();
    }

    @Override
    void makeEmpty() {
        worklist.clear();
    }

    @Override
    void add(Square arg) {
        arg.setStatus(Status.WORKING);
        worklist.offer(arg);
    }

    @Override
    boolean isEmpty() {
        return worklist.isEmpty();
    }

    @Override
    Square next() {
        System.out.println("CALLED");
        return (Square) worklist.poll();
    }
    @Override
    Square peekNext() {
        return (Square) worklist.peek();
    }
    @Override
    int size() {
        return worklist.size();
    }

    @Override
    void solve() {
        makeEmpty();
        worklist.offer(getMaze().getStart());
        this.setInitialSolveState();
    }
}
