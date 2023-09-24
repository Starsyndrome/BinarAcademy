import org.binaracademy.model.ProductMenu;
import org.binaracademy.repository.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class ProductRepositoryTest {
    List<ProductMenu> menuList;

    @BeforeEach
    void setUp() {
        System.out.println("Unit Testing Product Repository");
    }

    @Test
    void DaftarMenu_Test_Success(){
        menuList = ProductRepository.menuList();
        Assertions.assertEquals(5, menuList.size());
        Assertions.assertEquals(1, menuList.get(0).getNomorMenu());
        Assertions.assertEquals("Es Kopi Susu", menuList.get(0).getNamaMenu());
        Assertions.assertEquals(15000, menuList.get(0).getHargaMenu());
        Assertions.assertNotNull(menuList);
    }

    @Test
    void DaftarMenu_Test_Failed(){
        Assertions.assertThrows(NullPointerException.class, () -> menuList.get(0).setNomorMenu(null));
    }
}
