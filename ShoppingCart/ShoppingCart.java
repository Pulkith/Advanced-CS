package ShoppingCart;

import java.util.ArrayList;

public class ShoppingCart {
    ArrayList<ItemOrder> cart;
    public ShoppingCart() {
        cart = new ArrayList<>();
    }
    void add(ItemOrder newOrder) {
        for(int i = 0; i < cart.size(); ++i) {
            if(cart.get(i).equals( newOrder )) {
                cart.set(i, newOrder);
                return;
            }
        }
        cart.add(newOrder);
    }
    double getTotal() {
        double cost = 0;
        for(ItemOrder o : cart)
            cost += o.getPrice();
        return cost;
    }

}