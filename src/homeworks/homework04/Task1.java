package homeworks.homework04;

import java.util.Scanner;

public class Task1 {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        char input = scanner.next().charAt(0);
        String script = "qwertyuiopasdfghjklzxcvbnm" ;
        int index = script.indexOf(input);

        if (index == -1) {
            System.out.println("Введите английскую букву.");
            return;
        }

        // Выводим левую букву с учетом замкнутости
        int leftIndex = (index - 1 + script.length()) % script.length();
        System.out.println("Левая буква: " + script.charAt(leftIndex));
    }
}
