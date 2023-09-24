import org.binaracademy.service.OrderService;
import org.binaracademy.service.OrderServiceImplement;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;

public class OrderServiceImplementTest {
    OrderService orderService;

    @BeforeEach
    void setUp() {
        orderService = new OrderServiceImplement();
        System.out.println("Unit Testing Order Service Implement");
    }

    @Test
    void menuMakananPemesanan_Test(){
        String userInput = "0\n";
        ByteArrayInputStream in = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(in);
        orderService = new OrderServiceImplement();
        Assertions.assertDoesNotThrow(() -> orderService.menuMakananPemesanan());
    }

    @Test
    void menuPembayaran_Test(){
        String userInput = "99\n";
        ByteArrayInputStream in = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(in);
        orderService = new OrderServiceImplement();
        Assertions.assertDoesNotThrow(() -> orderService.menuPembayaran());
    }

    @Test
    void cetakStruk_Test_Success() {
        String hasilFile = orderService.cetakStruk("C:/BEJ/Challenge3/src/main/resources/struk.txt");
        Assertions.assertEquals("C:/BEJ/Challenge3/src/main/resources/struk.txt", hasilFile);
        Assertions.assertNotNull(hasilFile);
    }

    @Test
    void cetakStruk_Test_Failed(){
        Assertions.assertThrows(NullPointerException.class, () -> orderService.cetakStruk(null));
    }
}