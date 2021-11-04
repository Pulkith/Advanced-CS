package ShoppingCart;

public class ItemOrder {
    private Item item;
    private int qty;

    public ItemOrder(Item item, int qty) {
        this.item = item;
        this.qty = qty;
    }
    double getPrice() {
        return item.priceFor(qty);
    }
    public Item getItem() {
        return item;
    }
    public boolean equals(Object obj) {
        return ( ((ItemOrder)obj)  .getItem().equals(item));
    }
}