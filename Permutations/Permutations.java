import java.util.*;

public class Permutations {
    
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter a string to permute: ");
        
        char [] source = scan.nextLine().toCharArray();
        
        printPerm(source, 0);        
    }
    
    public static void printPerm(char[] array, int swapIndex){
       System.out.println(array);
       if(swapIndex == array.length) return;
       for(int i = swapIndex + 1; i < array.length; ++i) {
          swap(array, swapIndex, i);
          printPerm(array, swapIndex + 1);
          swap(array, swapIndex, i);
       }
    }

    private static void swap(char[] array, int swapIndex, int i) {
        char temp = array[swapIndex];
        array[swapIndex] = array[i];
        array[i] = temp;
    }
}