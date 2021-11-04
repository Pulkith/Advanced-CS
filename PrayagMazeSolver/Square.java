package PrayagMazeSolver;
public class Square{
    private int row;
    private int col;
    private int type;
    private char status;

    public Square(int row,int col, int type){
        this.row = row;
        this.col = col;
        this.type = type;
        this.status = '_';
    }
    public int getRow(){
        return row;
    }
    public int getCol(){
        return col;
    }
    public int getType(){
        return type;
    }
    public void setStatus(char stat){
        this.status = stat;
    }
    public char getStatus(){
        return status;
    }
    public boolean equals(Square obj){
        if(obj.getRow() == this.row && obj.getCol() == this.col)
            return true;
        return false;
    }
    public String toString(){
        if(type==0){
            if(status == '_')
                return "_";
            if(status == 'o')
                return "o";
            else if(status == '.')
                return ".";
            else if(status == 'x')
                return "x";
        }
        else{
            switch(type){
                case(1): return "#";
                case(2): return "S";
                case(3): return"E";

            }
        }
        return null;
    }
}