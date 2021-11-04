package Random;

import java.io.File;
import java.util.Scanner;
import java.util.regex.Pattern;

public class main {
    public static void main(String[] args) throws Exception{
        Scanner s = new Scanner(new File("/Users/despicablemonkey/Desktop/CS3/src/Random/emails.txt"));
        int count = 0;
        while(s.hasNext()) {
            String str = s.nextLine();
            if(Pattern.compile("[a-zA-Z]+\\.[a-zA-Z]+\\.[0-9]{3}@k12.friscoisd.org").matcher(str).find()) {
                if(count++ > 0) System.out.println(",");
                System.out.print(str.substring(str.indexOf("(") + 1, str.length() - 1));
            }
        }
        System.out.println();
    }
}
