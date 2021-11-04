package ShoppingCart;

public class Item {
    private String name;
    private double price, bulkPrice;
    private int bulkQty;

    public Item(String name, double price) {
        this(name, price, 100000007, price);
    }
    public String getName() {
        return this.name;
    }

    public Item(String name, double price, int bulkQty, double bulkPrice) {
//
//        if(price < 0 || bulkQty < 0 || bulkPrice < 0) {
//             throw new IllegalAccessException("error");
//        }

        this.name = name;
        this.price = price;
        this.bulkPrice = bulkPrice;
        this.bulkQty = bulkQty;
    }
    public  double priceFor(int qty){
        // if(qty < 0) throw new IllegalAccessException("error");
        return (qty >= bulkQty) ?  qty * bulkPrice : qty * price;
    }
    public boolean equals(Object obj) {
        return ((Item)obj)  .getName().equals(this.name);
    }
    public String toString() {
        String res = this.name + ", $" + this.price;
        if(this.bulkQty != 100000007) {
            res += " (For " + bulkQty + " and above, $" + bulkPrice + " each.)";
        }
        return res;
    }
}