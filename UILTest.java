import MazeSolver.Maze;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UILTest {
    static ArrayList<Integer> a = new ArrayList<>();
    static int count = 0;

    public static void dfs(int i) {
        if(i >= a.size()) return;
        ++count;
        dfs(i * 2 + 1);
        dfs(i * 2 + 2);
    }

    public static void main(String[] args) {

        for(int i = 0; i < 1000; ++i) a.add(i+1);
        for(int i = 0; i < 1000; ++i)
            dfs(i);
        System.out.println(count);


//        Pattern p =  Pattern.compile("\\b")     ;
//        String s = "aafafa";
//        System.out.println(Arrays.toString(s.split("a")));
//        System.out.println(String.format("%(+10d", -1));
    /*
    doing short = short + int, won't compile, but short += int will
    doing print on an object of type object, will still do the toString() of the actual object type

    */
//    int[] k = new int[5];
//    int[] y = k;
//    k[1] = 2;
//        System.out.println(Arrays.toString(k));
//
//        B o = new B();
//
//
//        System.out.println(o instanceof B);
//        System.out.println(o.x);

        /*
        subclass methods
        superclass vars (if subclass var doesnt exist -> compile error)
         */


    }
}



class A {
    int x = 5;
   public void A() {
       System.out.println("A");
   }
}

class B extends A{
    int x = 10;
    public void A() {
        System.out.println(super.x);
        super.A();
    }
}