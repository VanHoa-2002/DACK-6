import java.util.ArrayList;
import java.util.Date;

public class Cart {
    public long totalCost (ArrayList<Product> products) {
        long total = 0;
        for (Product product : products) {
            if(product.getSale() < product.getPrice()){
                total += product.getSale();
            }else {
                total += product.getPrice();
            }
        }

        return total;
    }


    public int increaseQuantity (ArrayList<Product> cart, Product newProduct) {
        for (Product product: cart) {
            if (product.getId() == newProduct.getId()) {
                product.setQuantity(product.getQuantity() + newProduct.getQuantity());
                return product.getQuantity();
            }
        }
        return 0;
    }

    public String  emptyCart (ArrayList<Product> products) {
        if (products.size() < 1) return "Empty cart!";
        return null;
    }

}
