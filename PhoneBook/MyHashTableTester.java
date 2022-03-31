package PhoneBook;

/**
 * Run some basic tests on your MyHashTable class
 *
 * The setUp() method runs one time prior to any @Test
 *
 * Tests run in numerical order
 *
 * Feel free to add your own or add print statements to check values (or use the debugger)
 */

import java.util.ArrayList;
import java.util.List;

public class MyHashTableTester {
    static final String PATH = ""; //change if your data files are stored in another directory
    static PhoneBook table;


    public static void main(String[] args) {
        table = new PhoneBook();
        test00_putNoPreviousValue();
        test01_putWithPreviousValue();
        test02_getNoCollision();
        test03_getDoesntExist();
        test04_getWithCollision();
        test05_removeExistsNoCollision();
        test06_removeDoesntExistNoCollision();
        test07_removeExistsWithCollisionFirstElementFirst();
        test08_removeExistsWithCollisionSecondElementFirst();
        test09_removeBothCollidingElements();
        test10_removeDoesntExistWithCollision();
        test11_testSizeEmpty();
        test12_testSizeOnePut();
        test13_testSizeOnePutOneRemove();
        test14_testSizeMultiplePuts();

    }

    public static void assertTrue(String s, boolean b) {
        if (!b)
            System.out.println(s);
        else
            System.out.println("Test Passed");
    }

    public static void assertEquals(String s, Integer o, PhoneNumber t) {
        if(t == null && o == null || t != null &&  o != null && t.number == o) System.out.println("Test Passed");
        else System.out.println(s);
    }

    public static void test00_putNoPreviousValue() {
        assertTrue("Adding Mary should return null", table.put(new Person("Mary"), new PhoneNumber(80)) == null);
    }

    public static void test01_putWithPreviousValue() {
        assertTrue("Replacing Mary should return previously mapped value (80)", table.put(new Person("Mary"), new PhoneNumber(50)).number == 80); //return prev. value
        assertTrue("Should return Mary's new age (50)", table.get(new Person("Mary")).number == 50);                             //return new value
    }

    public static void test02_getNoCollision() {
        Person jim = new Person("Jim");
        table.put(jim, new PhoneNumber(20));
        assertTrue("Jim should be mapped to 20", 20 == table.get(jim).number);
    }

    public static void test03_getDoesntExist() {
        assertEquals("Bob doesn't exist in map (return null)", null, table.get(new Person("Bob")));
    }

    public static void test04_getWithCollision() {
        //this should generate a collision if you're using String's hashCode method to
        //  hash the Person.  Uncomment the print statement if you're skeptical
//		System.out.println("Siblings".hashCode() + ", " + "Teheran".hashCode());
        assertTrue("Your Person.java needs to use the String class' hashCode method", new Person("Siblings").hashCode() == new Person("Siblings").hashCode());
        table.put(new Person("Siblings"), new PhoneNumber(60));
        table.put(new Person("Teheran"), new PhoneNumber(70));
        assertTrue("Should return value of second item in linked list (70)", 70 == table.get(new Person("Teheran")).number);
    }

    public static void test05_removeExistsNoCollision() {
        Person charlie = new Person("Charlie");
        table.put(charlie, new PhoneNumber(30));
        assertTrue("Removing Charlie returns mapped value (30)", table.remove(charlie).number == 30);
        assertTrue("Getting Charlie should now return null", table.get(charlie) == null);
    }

    public static void test06_removeDoesntExistNoCollision() {
        assertTrue("Attempting to remove Bob should return null", table.remove(new Person("Bob")) == null);
    }

    public static void test07_removeExistsWithCollisionFirstElementFirst() {
        //first element remove
        assertTrue("Removing first item in collision list should return 60", table.remove(new Person("Siblings")).number == 60);
        assertTrue("Now Siblings should not exist in table", table.remove(new Person("Siblings")) == null);
        assertTrue("Teheran should still be in table", table.get(new Person("Teheran")) != null);
    }

    public static void test08_removeExistsWithCollisionSecondElementFirst() {
        table = new PhoneBook();

        //this should generate a collision if you're using String's hashCode method to
        //  hash the Person.  Uncomment the print statement if you're skeptical
        //    (need to put them back in since they were previously removed)
        table.put(new Person("Siblings"), new PhoneNumber(60));
        table.put(new Person("Teheran"), new PhoneNumber(70));

        //second element remove
        assertTrue("Removing second item in collision list should return 70", table.remove(new Person("Teheran")).number == 70);
        assertTrue("Now Teheran should not exist in table", table.remove(new Person("Teheran")) == null);
        assertTrue("Siblings should still be in table", table.get(new Person("Siblings")) != null);
    }

    public static void test09_removeBothCollidingElements() {
        //this should generate a collision if you're using String's hashCode method to
        //  hash the Person.  Uncomment the print statement if you're skeptical
        //    (need to put them back in since they were previously removed)
        table.put(new Person("Siblings"), new PhoneNumber(60));
        table.put(new Person("Teheran"), new PhoneNumber(70));

        //second element remove
        assertTrue("Removing second item in collision list should return 70", table.remove(new Person("Teheran")).number == 70);
        assertTrue("Siblings should not exist in table", table.remove(new Person("Teheran")) == null);

        //first element remove
        assertTrue("Removing first item in collision list should return 60", table.remove(new Person("Siblings")).number == 60);
        assertTrue("Siblings should not exist in table", table.remove(new Person("Siblings")) == null);
    }

    public static void test10_removeDoesntExistWithCollision() {
        table = new PhoneBook();

        table.put(new Person("Siblings"), new PhoneNumber(60));
        //create a collision but without putting the colliding element in the list
        //  to check if your table walks the entire list
        assertTrue("Should not find the Teheran person", table.remove(new Person("Teheran")) == null);
    }

    public static void test11_testSizeEmpty() {
        table = new PhoneBook();
        assertTrue("Size should be 0", table.size() == 0);
    }

    public static void test12_testSizeOnePut() {
        table.put(new Person("Jill"), new PhoneNumber(90));
        assertTrue("Size should be 1", table.size() == 1);

        table.put(new Person("Jill"), new PhoneNumber(100));
        assertTrue("Size should STILL be 1", table.size() == 1);
    }

    public static void test13_testSizeOnePutOneRemove() {
        table.put(new Person("Jill"), new PhoneNumber(90));

        table.remove(new Person("Jill"));

        assertTrue("Size should be 0", table.size() == 0);
    }

    public static void test14_testSizeMultiplePuts() {
        table.put(new Person("Jill"), new PhoneNumber(90));
        table.put(new Person("Ronnie"), new PhoneNumber(100));
        table.put(new Person("Westyn"), new PhoneNumber(110));

        assertTrue("Size should be 3", table.size() == 3);
    }
}

