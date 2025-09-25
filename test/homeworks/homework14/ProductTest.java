package homeworks.homework14;


import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ProductTest {

    @Test
    void testConstructor() {
        Product product = new Product("Сыр=80");
        assertEquals("Сыр", product.getName());
        assertEquals(80.0, product.getPrice());
    }
    @Test
    void testEqualsAndHashcode() {
        Product p1 = new Product("Масло=60");
        Product p2 = new Product("Масло=60");
        assertEquals(p1, p2);
        assertEquals(p1.hashCode(), p2.hashCode());
    }



    @Disabled("Отключён временно")
    @Test
    void testNegativePrice() {
        assertThrows(IllegalArgumentException.class,
                () -> new Product("Яблоко=-10"));}
}
