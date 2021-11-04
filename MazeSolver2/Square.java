package MazeSolver2;

enum Type {
    WALL('#'),
    EMPTY('-'),
    START('S'),
    END('E');

    private Type(final char rep) {
        this.rep = rep;
    }
    private char rep;

    public String  toString() {return "" + this.rep; }
}
enum Status {
    VISITED,
    UNVISITED,
    WORKING,
    CURRENT

}
public class Square {
    private int row, col;

    public int getRow() {
        return row;
    }
    public int getCol() {
        return col;
    }
    public Type getType() {
        return type;
    }
    public Status getStatus() {
        return status;
    }
    public void setStatus(Status s) { this.status = s; }

    private Type type;
    private Status status;
    public Square(int row, int col, Type type) {
        this.row = row;
        this.col = col;
        this.type = type;
        status =  Status.UNVISITED;
    }
    public boolean equals(Object other) {
        Square o = (Square) other;
        return o.col == this.col && o.row == this.row;
    }
    public String toString() {
        return this.type.toString();
    }
    public void reset() {
        this.setStatus(Status.UNVISITED);
    }


}
