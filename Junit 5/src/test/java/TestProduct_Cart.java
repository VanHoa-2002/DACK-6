import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

public class TestProduct_Cart {
    //kiểm tra sản phẩm có giảm giá
    @Test
    public void testSale(){
        Product isSale = new Product(1, "Laptop Lenovo Thinkpad T14S GEN 2 20XF006NVN (Ryzen 7 PRO 5850U/ 16Gb/ 512Gb SSD/ 14\"FHD PS 300nits Anti-glare/ VGA ON/ Win 10 Pro/ Black/ 3Y)\n", 15000000, 5000000, 1);
        assertEquals("Price must be discounted",10000000,isSale.getSale());
    }
    //kiểm tra sản phẩm có tăng lên khi thêm cùng 1 sản phẩm
    @Test
    public void testQuantity(){
        Cart product_cart =new Cart();
        ArrayList<Product> cart = new ArrayList<>();
        cart.add(new Product(1, "Laptop Lenovo Thinkpad T14S GEN 2 20XF006NVN", 38290000,  2000000, 1));
        cart.add(new Product(2, "Laptop HP Envy 13-ba1536TU 4U6M5PA", 19000000,  900000, 1));
        cart.add(new Product(3, "Laptop Dell Latitude 3420", 10500000 ,  500000, 1));
        Product isQuantity = new Product(1, "Laptop HP Envy 13-ba1536TU 4U6M5PA", 19000000, 900000, 1);
        assertEquals("Price must be increase quantity",2,product_cart.increaseQuantity(cart,isQuantity));
    }
    //kiểm tra khi giỏ hàng trống
    @Test
    public void testCartIsEmpty () {
        Cart empty_cart = new Cart();

        ArrayList<Product> products = new ArrayList();

        assertEquals( "Message must be shown when cart is empty", "Empty cart!",empty_cart.emptyCart(products));
    }
    //kiểm tra tổng tiền hàng trong giỏ hàng
    @Test
    public void testTotalCost() {
        ArrayList<Product> products = new ArrayList<Product>();

        products.add(new Product(1, "Laptop Lenovo Thinkpad T14S GEN 2 20XF006NVN", 38290000,  2000000, 1));
        products.add(new Product(2, "Laptop HP Envy 13-ba1536TU 4U6M5PA", 19000000,  900000, 1));
        products.add(new Product(3, "Laptop Dell Latitude 3420", 10500000 ,  500000, 1));

        Cart subtotal = new Cart();

        long result = subtotal.totalCost(products);

        assertEquals("Total",64390000, result);
    }
}
