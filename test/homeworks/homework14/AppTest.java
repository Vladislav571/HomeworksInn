package homeworks.homework14;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AppTest {

    @Test
    void testConstructors() {
        Person person = new Person("Игорь=200");
        assertEquals("Игорь", person.getName());
        assertEquals(200.0, person.getMoney());
    }

    @Test
    void testEqualsAndHashcode() {
        Person p1 = new Person("Анна=150");
        Person p2 = new Person("Анна=150");
        assertEquals(p1, p2);
        assertEquals(p1.hashCode(), p2.hashCode());
    }

    @Test
    void testInsufficientFunds() {
        Person person = new Person("Марина=50");
        Product product = new Product("Колбаса=100");
        assertFalse(person.buyProduct(product)); // Недостаточно средств
        assertEquals(50.0, person.getMoney()); // Средства остались прежними
    }

    @Test
    void negativeTest(){
        Person person = new Person("Петя=100");
        Product product = new Product("Молоко=120");
        assertFalse(person.buyProduct(product));
        assertEquals(100.0, person.getMoney());
    }

    @Test
    void testInvalidInput() {
        try {
            new Person("Некорректный ввод"); // ожидается исключение
            fail("Должно было вызвать исключение!");
        } catch (ArrayIndexOutOfBoundsException e) {      // ожидаемое поведение
        }
    }
}
