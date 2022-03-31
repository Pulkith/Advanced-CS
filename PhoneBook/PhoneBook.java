package PhoneBook;

import javax.sound.sampled.Line;
import java.util.LinkedList;
import java.util.*;

public class PhoneBook implements IMap{
    LinkedList<Map.Entry<Person, PhoneNumber>>[] data = new LinkedList[500000];
    public PhoneBook() {
        for(int i = 0; i < this.data.length; ++i)
            this.data[i] = new LinkedList<Map.Entry<Person, PhoneNumber>>();
    }
    int size = 0;
    public PhoneNumber put(Person person, PhoneNumber phone) {
        int hashcode = person.hashCode() % data.length;
        for(int i = 0; i < data[hashcode].toArray().length; ++i) {
            if(data[hashcode].get(i).getKey().name.equals(person.name)) {
                PhoneNumber p = data[hashcode].get(i).getValue();
                remove(person);
                data[hashcode].add( Map.entry(person, phone));
                ++size;
                return p;
            }
        }
        data[hashcode].add( Map.entry(person, phone));
        size++;
        return null;
    }

    public PhoneNumber get(Person person) {
        int hashcode = person.hashCode() % data.length;
        for(int i = 0; i < this.data[hashcode].toArray().length; ++i) {
            if(this.data[hashcode].get(i).getKey().name.equals(person.name))
                return this.data[hashcode].get(i).getValue();
        }
        return null;
    }

    @Override
    public int size() {
        return this.size;
    }

    public PhoneNumber remove(Person person) {
        int hashcode = person.hashCode() % data.length;
        for(int i = 0; i < data[hashcode].toArray().length; ++i) {
            if(data[hashcode].get(i).getKey().name.equals(person.name)) {
                --size;
                return data[hashcode].remove(i).getValue();
            }
        }
        return null;
    }
}
