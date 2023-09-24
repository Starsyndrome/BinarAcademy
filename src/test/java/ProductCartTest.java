import org.binaracademy.model.ProductCart;
import org.binaracademy.model.ProductMenu;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ProductCartTest {

    ProductCart produkCart;
    ProductMenu productMenu;

    @BeforeEach
    void setUp() {
        productMenu = new ProductMenu();
        System.out.println("Unit Testing Product Cart");
    }

    @Test
    void ProdukKeranjang_Test_Success(){
        produkCart = new ProductCart(new ProductMenu
                (1, "Es Kopi Susu", 15000), 2);
        Assertions.assertEquals(2, produkCart.getQty());
        Assertions.assertEquals(15000, produkCart.getProduct().getHargaMenu());
        Assertions.assertEquals("Es Kopi Susu", produkCart.getProduct().getNamaMenu());
        Assertions.assertNotNull(produkCart);
    }

    @Test
    void ProdukKeranjang_Test_Failed(){
        Assertions.assertThrows(NullPointerException.class, () -> produkCart.setProductMenu(null));
    }
}