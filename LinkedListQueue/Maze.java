package LinkedListQueue;


import java.io.File;
import java.util.*;

public class Maze {

    public Maze() { }
    private Square[][] maze;
    private int rowLen, colLen;
    private Square start, end;
    boolean solved = false, isSolvable = false;

    public int getRowLen() {
        return rowLen;
    }

    public int getColLen() {
        return colLen;
    }

    public boolean loadMaze(String filename) {
        Scanner s;
        try {
            s = new Scanner(new File(filename));
        } catch(Exception ex) {
            System.out.println("Invalid File Path");
            return false;
        }
        this.rowLen = s.nextInt(); this.colLen = s.nextInt();

        maze = new Square[rowLen][colLen];

        for(int i = 0; i < rowLen; ++i)
            for(int j = 0; j < colLen; ++j) {
                int type = s.nextInt();
                Type tVal = switch (type) {
                    case 0 -> Type.EMPTY;
                    case 1 -> Type.WALL;
                    case 2 -> Type.START;
                    case 3 -> Type.END;
                    default -> throw new IllegalStateException("Unexpected Type Value: " + type);
                };

                maze[i][j] = new Square(i, j, tVal);
                if(type == 2) this.start = maze[i][j];
                if(type == 3) this.end = maze[i][j];
            }
        return true;
    }
    List<Square> getNeighbors(Square s) {
        int[][] d = new int[][]{
                {-1, 0, 1, 0},
                {0, 1, 0, -1}
        };
        ArrayList<Square> neighbors = new ArrayList<>();
        for(int i = 0; i < 4; ++i) {
            int r = s.getRow() + d[0][i];
            int c = s.getCol() + d[1][i];
            if(r >= 0 && c >= 0 && r < rowLen && c < colLen)
                neighbors.add(maze[r][c]);
        }
        return neighbors;
    }
    public Square getStart() {
        return this.start;
    }
    public Square getEnd() { return this.end; }
    public String toString() {
        String res = "";
        for(int i = 0; i < rowLen; ++i) {
            for (int j = 0; j < colLen; ++j)
                res += maze[i][j] + (j == colLen - 1 ? "" : " ");
            res += "\n";
        }
        return res;
    }
    public void reset(MazeViewCell[][] pg) {
        for(int i = 0; i < rowLen; ++i)
            for(int j = 0; j < colLen; ++j){
                maze[i][j].reset();
                pg[i][j].updateView(maze[i][j]);
                pg[i][j].reset();
            }

        this.isSolvable = false;
        this.solved = false;
    }

    public Square getCell(int row, int col) { return this.maze[row][col]; }
}
