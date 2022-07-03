public class Product {
    private int id;
    private String name;
    private long price;
    private long sale;
    private int quantity;

    public Product(int id, String name, long price, long sale, int quantity) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.sale = sale;
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public long getSale(){
        return this.price - this.sale;
    }
}