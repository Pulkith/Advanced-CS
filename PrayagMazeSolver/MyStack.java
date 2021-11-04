package PrayagMazeSolver;
import java.util.EmptyStackException;

public class MyStack{

    Square[]stack= new Square[0];
    int size = 0;
    public MyStack()
    {

    }
    public MyStack(int initCap)
    {
        stack = new Square[initCap];
        size = initCap;


    }
    boolean isEmpty()
    {
        if(size == 0)
            return true;
        else
            return false;
    }
    Square peek(){
        if(isEmpty())
            throw new EmptyStackException();
        else
            return stack[size-1];
    }
    Square pop(){
        Square s;
        if(isEmpty())
            throw new EmptyStackException();
        else{
            s = stack[size-1];
            size--;
            return s;
        }

    }
    void push(Square item){

        size++;

        doubleCapacity();

        stack[size-1]=item;
    }
    private void doubleCapacity(){

        Square[]stack1 = new Square[size];
        for(int i=0;i<size-1;i++){
            stack1[i]=stack[i];
        }
        stack=new Square[size];
        stack=stack1;


    }
    @Override
    public String toString(){
        String str = "";

        for(int s = size-1;s>=0;s--){
            str+=stack[s];
            if(s==size-1)
                str += "      <------ Top\n";
            else
                str+="\n";

        }


        str+="\n"+"-------";
        return str;
    }


}