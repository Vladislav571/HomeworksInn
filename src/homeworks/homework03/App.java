package homeworks.homework03;

import java.time.LocalDate;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String name = scanner.next();
        int inches = scanner.nextInt();
        double price = scanner.nextDouble();

        Shop mainShop = new Shop("DNS", "Moscow");
        LocalDate hireDate = LocalDate.of(2024, 9, 25);
        TV myTV = new TV(name, inches, price, hireDate, mainShop);

        System.out.println(myTV);
    }
}
