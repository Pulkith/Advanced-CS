package PhoneBook;

public class Person {
    String name;
    public Person(String n) {
        this.name = n;
    }
    public int hashCode() {
        double ans = 0;
        for(int i = 0; i < this.name.length(); ++i) {
            if(this.name.charAt(i) == ' ') continue;
            ans += Math.pow(1.07, (Character.isUpperCase(this.name.charAt(i))) ? (this.name.charAt(i) - 65) : this.name.charAt(i) - 97);
        }
        return (int) ans;
    }
}
