package homeworks.homework14;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PersonTest {

    @Test
    void testBySuccessful(){
        Person person = new Person("Иван=100");
        Product product = new Product("Хлеб=50");
        assertTrue(person.buyProduct(product)); // Покупка успешна
        assertEquals(50.0, person.getMoney()); // Деньги уменьшились
    }

    @Test
    void testToString(){
        Person person = new Person("Петя=100");
        Product product = new Product("Молоко=30");
        person.buyProduct(product);
        assertEquals("Петя - [Молоко]", person.toString());
    }


    @Test
    void testNullArguments() {
        assertThrows(IllegalArgumentException.class, () -> new Person(null));}
}
