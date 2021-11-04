package ShoppingCart;

import java.util.ArrayList;

public class Catalog {
    private String name;
    ArrayList<Item> catalog = new ArrayList<>();
    public Catalog(String name) {
        this.name = name;
    }
    void add(Item i) {
        catalog.add(i);

    }
    int size() {
        return catalog.size();
    }
    Item get(int index) {
        return catalog.get(index);
    }
    String getName() {
        return name;
    }
}