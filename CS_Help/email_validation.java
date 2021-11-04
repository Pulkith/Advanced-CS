package CS_Help;

public class email_validation {
    public static void main(String[] args) {
        System.out.println(validate("a.@c.co"));
    }
    public static boolean isAlphaNum(Character c) {
        return Character.isDigit(c) || Character.isAlphabetic(c);
    }
    public static boolean validate(String s) {
        if(s.length() < 5 || !isAlphaNum(s.charAt(0)) || !isAlphaNum(s.charAt(s.length() - 1)) || !isAlphaNum(s.charAt(s.length() - 2))) return false;
        boolean sawAt = false, sawPeriod = false;
        for(int i = 1; i < s.length(); ++i) {
            if(!sawAt && s.charAt(i) == '@') sawAt = true;
            if(sawAt && !sawPeriod && s.charAt(i) == '.') sawPeriod = true;
        }
        return sawAt && sawPeriod;
    }
}
