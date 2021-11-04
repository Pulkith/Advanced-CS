package LinkedListQueue;

public enum Type {
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