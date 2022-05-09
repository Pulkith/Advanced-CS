import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Runner {
    public static void main(String[] args) {

        f o = new f(5);
        f t = new g(5);
        System.out.println(o.equals(t));
        System.out.println(2>>33);
    }
}

 class f{
    int n;
    public f(int k) {
        this.n = k;
    }
    public boolean equals(f obj) {
        return n == obj.n;
    }
}

class g extends f {

    public g(int k) {
        super(k);
    }

    public boolean equals(f obj) {
        if((obj instanceof g))
            return obj.equals(this);
        return false;
    }
}

/*

Interfaces make everything constant
you can implement multiple interfaces
an abstract class can extend a class and vise versa
a superclass s = new subclass() uses the superclass variables, but subclass methods()
subclass = new subclass uses all subclasess
cant make an object out of abstract class/interfac
you can only implement Comparabale
interfaces can extend interfac




 */